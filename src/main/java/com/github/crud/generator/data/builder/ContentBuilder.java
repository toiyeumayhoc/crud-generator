package com.github.crud.generator.data.builder;

import com.github.crud.generator.constant.SystemProperty;

public class ContentBuilder implements DataBuilder {

	private final String className;
	private final DataBuilder[] mainContentBuilders;

	public ContentBuilder(String className, DataBuilder... mainContentBuilders) {
		this.mainContentBuilders = mainContentBuilders;
		this.className = className;
	}

	@Override
	public void addData(StringBuilder content) {
		content.append("public class ").append(className).append(" {").append(SystemProperty.BREAK_LINE);
		for (DataBuilder mainContentBuilder : mainContentBuilders) {
			mainContentBuilder.addData(content);
		}
		content.append("}");

	}

}
