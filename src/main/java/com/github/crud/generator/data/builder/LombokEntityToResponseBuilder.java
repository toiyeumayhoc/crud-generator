package com.github.crud.generator.data.builder;

import org.apache.commons.lang3.StringUtils;

import com.github.crud.generator.constant.SystemProperty;
import com.github.crud.generator.domain.AnnotatedClass;
import com.github.crud.generator.domain.ClassInfo;

public class LombokEntityToResponseBuilder extends EntityToResponseBuilder {

	public LombokEntityToResponseBuilder(String responseClassName, AnnotatedClass annotatedClass) {
		super(responseClassName, annotatedClass);
	}

	@Override
	public void addData(StringBuilder content) {
		content.append("	public ").append(responseClassName).append(" from")
				.append(StringUtils.capitalize(annotatedClass.getEntityClass().getName())).append("(")
				.append(annotatedClass.getEntityClass().getName()).append(" entity")
				.append(") {")
				.append(SystemProperty.BREAK_LINE)
				.append("		return ").append(responseClassName)
				.append(".builder()").append(SystemProperty.BREAK_LINE);

		for (ClassInfo field : annotatedClass.getFields()) {
			content.append("		       .").append(field.getName()).append("(");
			if("boolean".equals(field.getTypeName())) {
				content.append("entity.is");
			} else {
				content.append("entity.get");
			}
			content.append(StringUtils.capitalize(field.getName())).append("())").append(SystemProperty.BREAK_LINE);
		}

		content.append("		       .build();").append(SystemProperty.BREAK_LINE).append("	}")
				.append(SystemProperty.BREAK_LINE);

	}
}
