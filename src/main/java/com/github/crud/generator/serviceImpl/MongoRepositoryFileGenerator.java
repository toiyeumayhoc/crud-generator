package com.github.crud.generator.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.github.crud.generator.constant.ImportedAnnotation;
import com.github.crud.generator.constant.LibraryPackage;
import com.github.crud.generator.constant.SystemProperty;
import com.github.crud.generator.data.builder.AnnotationBuilder;
import com.github.crud.generator.data.builder.BuilderPipeline;
import com.github.crud.generator.data.builder.EntityImportBuilder;
import com.github.crud.generator.data.builder.InterfaceContentBuilder;
import com.github.crud.generator.data.builder.LibraryBuilder;
import com.github.crud.generator.data.builder.PackageBuilder;
import com.github.crud.generator.domain.AnnotatedClass;
import com.github.crud.generator.service.FileGeneratorAbstract;
import com.github.crud.generator.service.FileInforRetreiver;

public class MongoRepositoryFileGenerator extends FileGeneratorAbstract implements FileInforRetreiver {
	
	protected final String FOLDER = "repository";
	private final String FILE_NAME;
	protected final String CLASS_NAME;
	private final List<String> extendsClasses;
	private final List<String> importedLibraries;
	
	public MongoRepositoryFileGenerator(AnnotatedClass annotatedClass) {
		super(annotatedClass);
		this.CLASS_NAME = StringUtils.capitalize(annotatedClass.getEntityClass().getName()) + "Repository";
		this.FILE_NAME = CLASS_NAME + ".java";
		FileInforRetreiver extendsClass = new ExtendsRepositoryFileGenerator(annotatedClass);
		String mongoRepositoryInterface = String.format("MongoRepository<%s, %s>", annotatedClass.getEntityClass().getName(), "String");
		this.extendsClasses = Arrays.asList(mongoRepositoryInterface, extendsClass.getName());
		this.importedLibraries = Arrays.asList(LibraryPackage.MONGO_REPOSITORY.getValue(),
				LibraryPackage.REPOSITORY_ANNOTATION.getValue(), extendsClass.getPath());
 	}

	@Override
	protected String getFilePath() {
		return this.rootPath + this.FOLDER + SystemProperty.SEPATATOR + this.FILE_NAME;
	}
	
	@Override
	protected BuilderPipeline getBuilderPipeline() {
		return new BuilderPipeline().add(new PackageBuilder(annotatedClass.getBasePackage(), FOLDER))
				.add(new LibraryBuilder(importedLibraries))
				.add(new EntityImportBuilder(annotatedClass))
				.add(new AnnotationBuilder(ImportedAnnotation.REPOSITORY))
				.add(new InterfaceContentBuilder(CLASS_NAME, extendsClasses));
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
