package com.hybris.yps.hyeclipse.wizards.data;

import java.nio.file.Path;
import java.util.Optional;

public record ImportData(Path targetPath, Boolean removeExistingProjects, Boolean fixClasspathIssuesButton, Boolean removeHybrisItemsXmlGeneratorButton, Boolean createWorkingSetsButton, Boolean useMultiThreadButton, Boolean skipJarScanningButton) {
	
	public static ImportDataBuilder builder(ImportData input) {
		return new ImportDataBuilder(input);
	}
	
	public static class ImportDataBuilder {
		private ImportData input;
		private Path targetPath;
		
		ImportDataBuilder(ImportData inp) {
			this.input = inp;
		}
		
		public ImportDataBuilder withPath(Path newPath) {
			this.targetPath = newPath;
			return this;
		}
		
		public ImportData build() {
			var overridenPath = Optional.ofNullable(targetPath).orElse(input.targetPath);
			return new ImportData(overridenPath, input.removeExistingProjects, input.fixClasspathIssuesButton, input.removeHybrisItemsXmlGeneratorButton, input.createWorkingSetsButton, input.useMultiThreadButton, input.skipJarScanningButton);
		}
	}
}
