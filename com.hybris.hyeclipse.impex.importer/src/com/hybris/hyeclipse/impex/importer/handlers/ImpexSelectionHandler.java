/**
 * Roy, Cameron
 */
package com.hybris.hyeclipse.impex.importer.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import com.hybris.hyeclipse.impex.importer.managers.ImportManager;

/**
 * 
 */
public class ImpexSelectionHandler extends AbstractHandler {
	private final String IMPORT_DIALOG_TITLE = "Impex Selection";

	private final ImportManager importManager = new ImportManager();

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof TextSelection) {
			// Do something
			TextSelection textSelection = (TextSelection) selection;
			System.out.println("textSelection=[" + textSelection.getText() + "]");
			
			// TODO: Can be removed when visibleWhen & enabledWhen are working 
			if (	textSelection.getText().isEmpty()) {
				System.out.println("textSelection.getText().isEmpty() -> true -- Can be removed when visibleWhen & enabledWhen are working");
			}
			
			final String message = importManager.performImport(textSelection.getText());
			
			final Shell shell = HandlerUtil.getActiveShell(event);
			MessageDialog.openInformation(shell, IMPORT_DIALOG_TITLE, message);
		} else {
			// Do nothing
			System.out.println("selection -> Do nothing");
		}
		
		return null;
	}

}
