package com.github.crud.generator.serviceImpl;

import org.apache.commons.lang3.StringUtils;

import com.github.crud.generator.constant.SystemProperty;
import com.github.crud.generator.data.builder.BuilderPipeline;
import com.github.crud.generator.data.builder.ClassContentBuilder;
import com.github.crud.generator.data.builder.EntityImportBuilder;
import com.github.crud.generator.data.builder.EntityToResponseBuilder;
import com.github.crud.generator.data.builder.FieldContentBuilder;
import com.github.crud.generator.data.builder.FieldLibraryBuilder;
import com.github.crud.generator.data.builder.PackageBuilder;
import com.github.crud.generator.domain.AnnotatedClass;
import com.github.crud.generator.service.FileGeneratorAbstract;
import com.github.crud.generator.service.FileInforRetreiver;

public class ResponseFileGenerator extends FileGeneratorAbstract implements FileInforRetreiver {
	protected final String FOLDER = "response";
	private final String FILE_NAME;
	protected final String CLASS_NAME;
	
	public ResponseFileGenerator(AnnotatedClass annotatedClass) {
		super(annotatedClass);
		this.CLASS_NAME = StringUtils.capitalize(annotatedClass.getEntityClass().getName()) + "Response";
		this.FILE_NAME = CLASS_NAME + ".java";
 	}

	@Override
	protected String getFilePath() {
		return this.rootPath + this.FOLDER + SystemProperty.SEPATATOR + this.FILE_NAME;
	}
	
	@Override
	protected BuilderPipeline getBuilderPipeline() {
		return new BuilderPipeline().add(new PackageBuilder(annotatedClass.getBasePackage(), FOLDER))
				.add(new EntityImportBuilder(annotatedClass))
				.add(new FieldLibraryBuilder(annotatedClass))
				.add(new ClassContentBuilder(CLASS_NAME, new FieldContentBuilder(annotatedClass), 
						new EntityToResponseBuilder(CLASS_NAME, annotatedClass)));
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
