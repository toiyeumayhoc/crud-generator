package com.github.crud.generator.data.builder;

import java.util.Arrays;
import java.util.List;

import com.github.crud.generator.constant.SystemProperty;

public class ClassContentBuilder implements DataBuilder {

	private final String className;
	private final DataBuilder[] mainContentBuilders;
	private final List<String> implementClasses;

	public ClassContentBuilder(String className, List<String> implementClasses,  DataBuilder... mainContentBuilders) {
		this.mainContentBuilders = mainContentBuilders;
		this.className = className;
		this.implementClasses = implementClasses;
	}
	
	public ClassContentBuilder(String className, DataBuilder... mainContentBuilders) {
		this.mainContentBuilders = mainContentBuilders;
		this.className = className;
		this.implementClasses = Arrays.asList();
	}

	@Override
	public void addData(StringBuilder content) {
		content.append("public class ").append(className);
		
		if(this.implementClasses != null && this.implementClasses.size() > 0) {
			content.append(" implements");
			for(String implementClass : implementClasses) {
				content.append(" ").append(implementClass).append(",");
			}
			content.setLength(content.length() - 1);
		}
		
		content.append(" {").append(SystemProperty.BREAK_LINE);
		for (DataBuilder mainContentBuilder : mainContentBuilders) {
			mainContentBuilder.addData(content);
		}
		content.append("}");

	}

}
