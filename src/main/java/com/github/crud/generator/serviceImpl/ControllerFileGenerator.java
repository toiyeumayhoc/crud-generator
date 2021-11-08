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
import com.github.crud.generator.data.builder.ControllerContentBuilder;
import com.github.crud.generator.data.builder.LibraryBuilder;
import com.github.crud.generator.data.builder.PackageBuilder;
import com.github.crud.generator.domain.AnnotatedClass;
import com.github.crud.generator.service.FileGeneratorAbstract;
import com.github.crud.generator.service.FileInforRetreiver;

public class ControllerFileGenerator extends FileGeneratorAbstract {
	protected final String FOLDER = "controller";
	private final String FILE_NAME;
	protected final String CLASS_NAME;
	private final List<String> importedLibrary;
	
	public ControllerFileGenerator(AnnotatedClass annotatedClass) {
		super(annotatedClass);
		this.CLASS_NAME = StringUtils.capitalize(annotatedClass.getEntityClass().getName()) + "Controller";
		this.FILE_NAME = CLASS_NAME + ".java";
		FileInforRetreiver request = new RequestFileGenerator(annotatedClass);
		FileInforRetreiver response = new ResponseFileGenerator(annotatedClass);
		FileInforRetreiver service = new ServiceFileGenerator(annotatedClass);
		this.importedLibrary = Arrays.asList(annotatedClass.getIdType().getName(), LibraryPackage.LIST.getValue(), LibraryPackage.AUTOWIRED.getValue(), LibraryPackage.DELETE_MAPPING.getValue(),
				LibraryPackage.POST_MAPPING.getValue(), LibraryPackage.GET_MAPPING.getValue(), LibraryPackage.PUT_MAPPING.getValue(), LibraryPackage.PATH_VARIABLE.getValue(),
				LibraryPackage.REQUEST_BODY.getValue(), LibraryPackage.REQUEST_MAPPING.getValue(), LibraryPackage.REST_CONTROLLER.getValue(),
				request.getPath(), response.getPath(), service.getPath());
 	}

	@Override
	protected String getFilePath() {
		return this.rootPath + this.FOLDER + SystemProperty.SEPATATOR + this.FILE_NAME;
	}
	
	@Override
	protected BuilderPipeline getBuilderPipeline() {
		return new BuilderPipeline().add(new PackageBuilder(annotatedClass.getBasePackage(), FOLDER))
				.add(new LibraryBuilder(importedLibrary))
				.add(new AnnotationBuilder(ImportedAnnotation.REST_CONTROLLER))
				.add((content) -> 
					content.append(ImportedAnnotation.REQUEST_MAPPING.getValue())
						.append("(value = \"/")
						.append(StringUtils.uncapitalize(this.annotatedClass.getEntityClass().getName()))
						.append("\")").append(SystemProperty.BREAK_LINE))
				.add(new ClassContentBuilder(CLASS_NAME, new ControllerContentBuilder(annotatedClass)));
	}
	
}

