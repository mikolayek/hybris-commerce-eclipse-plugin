package com.hybris.yps.hyeclipse;

import java.util.List;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

public class ExtensionPropertyTester extends PropertyTester {
	
	private static final String PROPERTY_IS_EXTENSION = "isExtension";

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (property.equals(PROPERTY_IS_EXTENSION)) {
			return testExtension(receiver);
		}
		return false;
	}

	/**
	 * Tests if selected file is an eclipse project
	 * 
	 * @param receiver
	 *            the receiver of the property test
	 * @return true if file is impex file
	 * @throws CoreException can throw exception when 
	 */
	private boolean testExtension(Object receiver) {
		if (receiver instanceof List) {
			@SuppressWarnings("unchecked")
			List<Object> list = (List<Object>) receiver;
			if (!list.isEmpty() && list.size() < 2) {
				Object firstItem = list.stream().findFirst().get();
				IProject project = null;
				if (firstItem instanceof IProject) {
					IProject genericProject = (IProject) firstItem;
					project = genericProject;
				} else if (firstItem instanceof IJavaProject) {
					IJavaProject item = (IJavaProject) firstItem;
					project = (IProject) item.getResource();
				}
				
				// TODO add validation for Hybris project
				if (project.exists() && project.isOpen() && project.getFile(Activator.EXTENSIONINFO_XML).exists()) {
					return true;
				}
			}			
		}	

		return false;
	}

}
