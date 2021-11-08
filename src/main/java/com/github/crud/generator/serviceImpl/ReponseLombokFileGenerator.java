package com.github.crud.generator.serviceImpl;

import com.github.crud.generator.data.builder.BuilderPipeline;
import com.github.crud.generator.data.builder.ClassContentBuilder;
import com.github.crud.generator.data.builder.EntityImportBuilder;
import com.github.crud.generator.data.builder.FieldLibraryBuilder;
import com.github.crud.generator.data.builder.LombokAnnotationBuilder;
import com.github.crud.generator.data.builder.LombokEntityToResponseBuilder;
import com.github.crud.generator.data.builder.LombokFieldContentBuilder;
import com.github.crud.generator.data.builder.LombokLibraryBuilder;
import com.github.crud.generator.data.builder.PackageBuilder;
import com.github.crud.generator.domain.AnnotatedClass;

public class ReponseLombokFileGenerator extends ResponseFileGenerator {
	
	public ReponseLombokFileGenerator(AnnotatedClass annotatedClass) {
		super(annotatedClass);
	}
	
	@Override
	protected BuilderPipeline getBuilderPipeline() {
		return new BuilderPipeline().add(new PackageBuilder(annotatedClass.getBasePackage(), FOLDER))
				.add(new EntityImportBuilder(annotatedClass))
				.add(new LombokLibraryBuilder())
				.add(new FieldLibraryBuilder(annotatedClass))
				.add(new LombokAnnotationBuilder())
				.add(new ClassContentBuilder(CLASS_NAME, new LombokFieldContentBuilder(annotatedClass), new LombokEntityToResponseBuilder(CLASS_NAME, annotatedClass)));
	}

}
