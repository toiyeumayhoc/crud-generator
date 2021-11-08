package com.github.crud.generator.data.builder;

import java.util.Arrays;
import java.util.List;

import com.github.crud.generator.constant.SystemProperty;

public class InterfaceContentBuilder implements DataBuilder {

	private final String className;
	private final DataBuilder[] mainContentBuilders;
	private final List<String> extendsClasses;

	public InterfaceContentBuilder(String className,List<String> extendsClasses, DataBuilder... mainContentBuilders) {
		this.mainContentBuilders = mainContentBuilders;
		this.className = className;
		this.extendsClasses = extendsClasses;
	}
	
	public InterfaceContentBuilder(String className, DataBuilder... mainContentBuilders) {
		this.mainContentBuilders = mainContentBuilders;
		this.className = className;
		this.extendsClasses = Arrays.asList();
	}

	@Override
	public void addData(StringBuilder content) {
		content.append("public interface ").append(className);
		
		if(extendsClasses!=null && extendsClasses.size()>0) {
			content.append(" extends");
			for(String extendsClass : extendsClasses) {
				content.append(" ").append(extendsClass).append(",");
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