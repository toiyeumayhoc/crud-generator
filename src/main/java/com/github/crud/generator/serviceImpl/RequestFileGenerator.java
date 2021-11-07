package com.github.crud.generator.serviceImpl;

import org.apache.commons.lang3.StringUtils;

import com.github.crud.generator.constant.SystemProperty;
import com.github.crud.generator.data.builder.BuilderPipeline;
import com.github.crud.generator.data.builder.ContentBuilder;
import com.github.crud.generator.data.builder.FieldContentBuilder;
import com.github.crud.generator.data.builder.LibraryBuilder;
import com.github.crud.generator.data.builder.PackageBuilder;
import com.github.crud.generator.domain.AnnotatedClass;
import com.github.crud.generator.service.FileGeneratorAbstract;

public class RequestFileGenerator extends FileGeneratorAbstract {
	
	protected final String FOLDER = "request";
	private final String FILE_NAME;
	protected final String CLASS_NAME;
	
	public RequestFileGenerator(AnnotatedClass annotatedClass) {
		super(annotatedClass);
		this.CLASS_NAME = StringUtils.capitalize(annotatedClass.getEntityClass().getName()) + "Request";
		this.FILE_NAME = CLASS_NAME + ".java";
 	}

	@Override
	protected String getFilePath() {
		return this.rootPath + this.FOLDER + SystemProperty.SEPATATOR + this.FILE_NAME;
	}
	
	@Override
	protected BuilderPipeline getBuilderPipeline() {
		return new BuilderPipeline().add(new PackageBuilder(annotatedClass.getBasePackage(), FOLDER))
				.add(new LibraryBuilder(annotatedClass))
				.add(new ContentBuilder(new FieldContentBuilder(annotatedClass),CLASS_NAME));
	}

}
