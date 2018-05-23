package com.hybris.hyeclipse.impex.importer.testers;

import java.util.Collection;

import org.eclipse.jface.text.TextSelection;

import com.hybris.hyeclipse.hac.testers.AbstractFilePropertyTester;

/**
 * PropertyTester to check for a valid TextSelection in an Editor.
 */
public class SelectionPropertyTester extends AbstractFilePropertyTester {

	private static final String PROPERTY_NAME = "hasSelection";
	
	@Override
	public boolean test(final Object receiver, final String property, final Object[] args, final Object expectedValue) {
		if (PROPERTY_NAME.equals(property)) {
			if (receiver instanceof Collection) {
				@SuppressWarnings("unchecked")
				Collection<Object> receiverCollection = (Collection<Object>) receiver;
				TextSelection selection;
				
	            if (receiverCollection.toArray().length != 0 && receiverCollection.toArray()[0] instanceof TextSelection)  {
	                selection = (TextSelection) receiverCollection.toArray()[0];
	                if (!selection.getText().equals("")) {
	                    return true;
	                }
	            }

			}
		}
		return false;
	}
	
}
