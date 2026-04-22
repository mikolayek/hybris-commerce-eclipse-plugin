package com.hybris.yps.hyeclipse.wizards.pages;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.hybris.yps.hyeclipse.wizards.data.ImportData;

/**
 * Page for handling Gradle Project Import.
 * @author pawel.wolanski
 */
public class ImportGradlePlatformPage extends ImportPlatformPage {
	private static final String MANIFEST_JSON = "manifest.json";
	private Text manifestPathText;

	
	@Override
	public ImportData buildConfiguration() {
		var inherited = super.buildConfiguration();
		return ImportData.builder(inherited).withPath(getManifestPath()).build();
	}
	
	public String showFileDialog(Shell shell, String defaultDirectory, String defaultFile,
			String[] filterExtensions) {
		FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
		if (defaultDirectory != null && defaultDirectory.length() != 0) {
			fileDialog.setFilterPath(defaultDirectory);
		}
		fileDialog.setFileName(defaultFile);
		fileDialog.setFilterExtensions(filterExtensions);
		return fileDialog.open();
	}

	/**
	 * Build FieldDialog for selecting manifest.json
	 */
	@Override
	protected Text buildInputBox(final Composite container) {
		Label manifestLabel = new Label(container, SWT.NONE);
		manifestLabel.setText("Core manifest.json:"); //$NON-NLS-1$

		manifestPathText = new Text(container, SWT.BORDER);
		manifestPathText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		manifestPathText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				setPageComplete(true);
				setErrorMessage(null);
			}
		});

		Button browseButton = new Button(container, SWT.PUSH);
		browseButton.setText("Browse..."); //$NON-NLS-1$
		browseButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String currentPath = manifestPathText.getText().trim();
				String currentDir = currentPath.isEmpty() ? null : new File(currentPath).getParent();
				String fileName = showFileDialog(getShell(), currentDir, MANIFEST_JSON, new String[] { "*.json" }); //$NON-NLS-1$
				if (fileName != null && !fileName.isEmpty()) {
					manifestPathText.setText(fileName);
				}
			}
		});
		
		return manifestPathText;
	}
	
	protected Path getManifestPath() {
		String currentPath = manifestPathText.getText().trim();
		return new File(currentPath).toPath();
	}
	
	@Override
	public boolean validatePage() {
		return !manifestPathText.getText().trim().isBlank() && Files.exists(getManifestPath());
		
	}
}
