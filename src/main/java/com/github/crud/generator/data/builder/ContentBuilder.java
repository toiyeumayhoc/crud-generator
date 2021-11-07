package com.github.crud.generator.data.builder;

import com.github.crud.generator.constant.SystemProperty;

public class ContentBuilder implements DataBuilder {

	private final String className;
	private final DataBuilder mainContentBuilder;

	public ContentBuilder(DataBuilder mainContentBuilder, String className) {
		this.mainContentBuilder = mainContentBuilder;
		this.className = className;
	}

	@Override
	public void addData(StringBuilder content) {
		content.append("public class ").append(className).append(" {").append(SystemProperty.BREAK_LINE);
		this.mainContentBuilder.addData(content);
		content.append("}");

	}
	

}
