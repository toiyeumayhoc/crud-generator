package com.github.crud.generator.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.github.crud.generator.constant.LibraryPackage;
import com.github.crud.generator.constant.SystemProperty;
import com.github.crud.generator.data.builder.BuilderPipeline;
import com.github.crud.generator.data.builder.InterfaceContentBuilder;
import com.github.crud.generator.data.builder.LibraryBuilder;
import com.github.crud.generator.data.builder.PackageBuilder;
import com.github.crud.generator.data.builder.ServicePrototypeBuilder;
import com.github.crud.generator.domain.AnnotatedClass;
import com.github.crud.generator.service.FileGeneratorAbstract;
import com.github.crud.generator.service.FileInforRetreiver;

public class ServiceFileGenerator extends FileGeneratorAbstract implements FileInforRetreiver {
	protected final String FOLDER = "service";
	private final String FILE_NAME;
	protected final String CLASS_NAME;
	private final List<String> importedLibraries;
	
	public ServiceFileGenerator(AnnotatedClass annotatedClass) {
		super(annotatedClass);
		this.CLASS_NAME = StringUtils.capitalize(annotatedClass.getEntityClass().getName()) + "Service";
		this.FILE_NAME = CLASS_NAME + ".java";
		FileInforRetreiver request = new RequestFileGenerator(annotatedClass);
		FileInforRetreiver response = new ResponseFileGenerator(annotatedClass);
		this.importedLibraries = Arrays.asList(LibraryPackage.LIST.getValue(),
				request.getPath(), response.getPath());
 	}

	@Override
	protected String getFilePath() {
		return this.rootPath + this.FOLDER + SystemProperty.SEPATATOR + this.FILE_NAME;
	}
	
	@Override
	protected BuilderPipeline getBuilderPipeline() {
		return new BuilderPipeline().add(new PackageBuilder(annotatedClass.getBasePackage(), FOLDER))
				.add(new LibraryBuilder(importedLibraries))
				.add(new InterfaceContentBuilder(CLASS_NAME, new ServicePrototypeBuilder(annotatedClass)));
	}
	
	@Override
	public String getPath() {
		return new StringBuilder(annotatedClass.getBasePackage()).append(".").append(FOLDER).append(".").append(this.CLASS_NAME).toString();
	}

	@Override
	public String getName() {
		return this.CLASS_NAME;
	}
}
