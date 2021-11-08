package com.github.crud.generator.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.github.crud.generator.constant.ImportedAnnotation;
import com.github.crud.generator.constant.LibraryPackage;
import com.github.crud.generator.constant.SystemProperty;
import com.github.crud.generator.data.builder.AnnotationBuilder;
import com.github.crud.generator.data.builder.BuilderPipeline;
import com.github.crud.generator.data.builder.ClassContentBuilder;
import com.github.crud.generator.data.builder.EntityImportBuilder;
import com.github.crud.generator.data.builder.LibraryBuilder;
import com.github.crud.generator.data.builder.PackageBuilder;
import com.github.crud.generator.data.builder.ServiceImplementContentBuilder;
import com.github.crud.generator.domain.AnnotatedClass;
import com.github.crud.generator.service.FileGeneratorAbstract;
import com.github.crud.generator.service.FileInforRetreiver;

public class ServiceImplementFileGenerator extends FileGeneratorAbstract {

	private final String FOLDER = "service";
	private final String SUB_FOLDER = "impl";
	private final String FILE_NAME;
	protected final String CLASS_NAME;
	private final List<String> implementClasses;
	private final List<String> importedLibrary;
	
	public ServiceImplementFileGenerator(AnnotatedClass annotatedClass) {
		super(annotatedClass);
		this.CLASS_NAME = StringUtils.capitalize(annotatedClass.getEntityClass().getName()) + "ServiceImpl";
		this.FILE_NAME = CLASS_NAME + ".java";
		FileInforRetreiver implementClass = new ServiceFileGenerator(annotatedClass);
		FileInforRetreiver request = new RequestFileGenerator(annotatedClass);
		FileInforRetreiver response = new ResponseFileGenerator(annotatedClass);
		FileInforRetreiver repository = new MongoRepositoryFileGenerator(annotatedClass);
		this.implementClasses = Arrays.asList(implementClass.getName());
		this.importedLibrary = Arrays.asList(LibraryPackage.OVERRIDE.getValue(), LibraryPackage.AUTOWIRED.getValue(), LibraryPackage.COLLECTORS.getValue(), LibraryPackage.OPTIONAL.getValue(), 
				 LibraryPackage.LIST.getValue(), LibraryPackage.SERVICE_ANNOTATION.getValue(), implementClass.getPath(), request.getPath(), response.getPath(), repository.getPath());
 	}

	@Override
	protected String getFilePath() {
		return this.rootPath + this.FOLDER + SystemProperty.SEPATATOR + this.SUB_FOLDER + SystemProperty.SEPATATOR + this.FILE_NAME;
	}
	
	@Override
	protected BuilderPipeline getBuilderPipeline() {
		return new BuilderPipeline().add(new PackageBuilder(annotatedClass.getBasePackage(), FOLDER + "." + SUB_FOLDER))
				.add(new EntityImportBuilder(annotatedClass))
				.add(new LibraryBuilder(importedLibrary))
				.add(new AnnotationBuilder(ImportedAnnotation.SERVICE))
				.add(new ClassContentBuilder(CLASS_NAME, implementClasses, new ServiceImplementContentBuilder(annotatedClass)));
	}

}