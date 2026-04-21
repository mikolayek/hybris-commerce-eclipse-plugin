package com.hybris.yps.hyeclipse.handlers.help;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.hybris.yps.hyeclipse.Activator;

public abstract class AbstractOpenInExternalBrowserHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent arg0) throws ExecutionException {
		try {
			String url = getURL();
			PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL(new URI(url).toURL());
		} catch (PartInitException | MalformedURLException | URISyntaxException e) {
			String errMsg = String.format("Could not open link: %s ", getURL());
			Activator.logError(errMsg, e);
			throw new ExecutionException(errMsg, e);
		}
		return null;
	}
	
	protected abstract String getURL();
}
