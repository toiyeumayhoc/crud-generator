package com.github.crud.generator.data.builder;

import com.github.crud.generator.constant.SystemProperty;
import com.github.crud.generator.domain.AnnotatedClass;

public class FieldContentBuilder implements DataBuilder {
	private final AnnotatedClass annotatedClass;
	private final DataBuilder getterSeterBuilder;

	public FieldContentBuilder(AnnotatedClass annotatedClass) {
		this.annotatedClass = annotatedClass;
		this.getterSeterBuilder = new GetterSetterBuilder(annotatedClass);
	}
	
	public FieldContentBuilder(AnnotatedClass annotatedClass, DataBuilder getterSetterBuilder) {
		this.annotatedClass = annotatedClass;
		this.getterSeterBuilder = getterSetterBuilder;
	}

	
	@Override
	public void addData(StringBuilder content) {
		this.annotatedClass.getFields().forEach(field -> {
			content.append("	private ").append(field.getTypeName()).append(" ").append(field.getName()).append(";")
					.append(SystemProperty.BREAK_LINE);
		});
		this.getterSeterBuilder.addData(content);
	}

}
