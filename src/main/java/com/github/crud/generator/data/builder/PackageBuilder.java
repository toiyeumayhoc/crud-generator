package com.github.crud.generator.data.builder;

import com.github.crud.generator.constant.SystemProperty;

public class PackageBuilder implements DataBuilder {
	
	private final String basePackage;
	private final String folder;
	
	public PackageBuilder(String basePackage, String folder) {
		this.basePackage = basePackage;
		this.folder = folder;
	}

	@Override
	public void addData(StringBuilder content) {
		content.append("package ").append(this.basePackage).append(".").append(folder).append(";").append(SystemProperty.BREAK_LINE);
	}

}
