package com.github.crud.generator.data.builder;

import org.apache.commons.lang3.StringUtils;

import com.github.crud.generator.constant.SystemProperty;
import com.github.crud.generator.domain.AnnotatedClass;
import com.github.crud.generator.domain.ClassInfo;

public class EntityToResponseBuilder implements DataBuilder {

	protected final AnnotatedClass annotatedClass;
	protected final String responseClassName;

	public EntityToResponseBuilder(String responseClassName, AnnotatedClass annotatedClass) {
		this.annotatedClass = annotatedClass;
		this.responseClassName = responseClassName;
	}

	@Override
	public void addData(StringBuilder content) {
		content.append("	public ").append(responseClassName).append(" from")
				.append(StringUtils.capitalize(annotatedClass.getEntityClass().getName())).append("(")
				.append(annotatedClass.getEntityClass().getName()).append(" entity")
				.append(") {")
				.append(SystemProperty.BREAK_LINE).append("		").append(responseClassName)
				.append(" response = new ").append(responseClassName).append("();")
				.append(SystemProperty.BREAK_LINE);

		for (ClassInfo field : annotatedClass.getFields()) {
			content.append("		response.set").append(StringUtils.capitalize(field.getName())).append("(");
			if("boolean".equals(field.getTypeName())) {
				content.append("entity.is");
			} else {
				content.append("entity.get");
			}
			
			content.append(StringUtils.capitalize(field.getName())).append("());").append(SystemProperty.BREAK_LINE);
		}

		content.append("		return response;").append(SystemProperty.BREAK_LINE).append("	}")
				.append(SystemProperty.BREAK_LINE);

	}

}