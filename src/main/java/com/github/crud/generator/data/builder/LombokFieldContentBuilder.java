package com.github.crud.generator.data.builder;

import com.github.crud.generator.domain.AnnotatedClass;

public class LombokFieldContentBuilder extends FieldContentBuilder {

	public LombokFieldContentBuilder(AnnotatedClass annotatedClass) {
		super(annotatedClass,  (content) -> {});
	}

}
