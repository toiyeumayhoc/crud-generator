package com.github.crud.generator.data.builder;

import org.apache.commons.lang3.StringUtils;

import com.github.crud.generator.constant.SystemProperty;
import com.github.crud.generator.domain.AnnotatedClass;
import com.github.crud.generator.domain.ClassInfo;

public class RequestToEntityBuilder implements DataBuilder {

	private final AnnotatedClass annotatedClass;

	public RequestToEntityBuilder(AnnotatedClass annotatedClass) {
		this.annotatedClass = annotatedClass;
	}

	@Override
	public void addData(StringBuilder content) {
		content.append(SystemProperty.BREAK_LINE).append("	public ").append(annotatedClass.getEntityClass().getName()).append(" to")
				.append(StringUtils.capitalize(annotatedClass.getEntityClass().getName())).append("() {").append(SystemProperty.BREAK_LINE)
				.append("		").append(annotatedClass.getEntityClass().getName()).append(" entity = new ").append(annotatedClass.getEntityClass().getName()).append("();").append(SystemProperty.BREAK_LINE);
				
		for(ClassInfo field : annotatedClass.getFields()) {
			content.append("		entity.set").append(StringUtils.capitalize(field.getName()))
			.append("(").append(field.getName()).append(");").append(SystemProperty.BREAK_LINE);
		}
		
		content.append("		return entity;").append(SystemProperty.BREAK_LINE)
				.append("	}").append(SystemProperty.BREAK_LINE);

	}

}
