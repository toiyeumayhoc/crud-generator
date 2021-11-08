package com.github.crud.generator.data.builder;

import java.util.Arrays;
import java.util.List;

import com.github.crud.generator.constant.ImportedAnnotation;
import com.github.crud.generator.constant.SystemProperty;

public class AnnotationBuilder implements DataBuilder {
	
	private final List<ImportedAnnotation> annotations;

	public AnnotationBuilder(List<ImportedAnnotation> annotations) {
		this.annotations = annotations;
	}
	
	public AnnotationBuilder(ImportedAnnotation annotation) {
		this.annotations = Arrays.asList(annotation);
	}

	@Override
	public void addData(StringBuilder content) {
		for(ImportedAnnotation annotation : annotations) {
			content.append(annotation.getValue()).append(SystemProperty.BREAK_LINE);
		}
	}

}
