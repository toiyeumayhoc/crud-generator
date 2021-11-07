package com.github.crud.generator.serviceImpl;

import com.github.crud.generator.data.builder.BuilderPipeline;
import com.github.crud.generator.data.builder.ContentBuilder;
import com.github.crud.generator.data.builder.LombokAnnotationBuilder;
import com.github.crud.generator.data.builder.LombokFieldContentBuilder;
import com.github.crud.generator.data.builder.LombokLibraryBuilder;
import com.github.crud.generator.data.builder.PackageBuilder;
import com.github.crud.generator.domain.AnnotatedClass;

public class RequestLombokFileGenerator extends RequestFileGenerator {

	
	public RequestLombokFileGenerator(AnnotatedClass annotatedClass) {
		super(annotatedClass);
	}
	
	@Override
	protected BuilderPipeline getBuilderPipeline() {
		return new BuilderPipeline().add(new PackageBuilder(annotatedClass.getBasePackage(), FOLDER))
				.add(new LombokLibraryBuilder(annotatedClass))
				.add(new LombokAnnotationBuilder())
				.add(new ContentBuilder(new LombokFieldContentBuilder(annotatedClass),CLASS_NAME));
	}
	
}
