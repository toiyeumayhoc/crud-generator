package com.github.crud.generator.data.builder;

import java.util.Arrays;
import java.util.List;

import com.github.crud.generator.constant.LibraryPackage;
import com.github.crud.generator.constant.SystemProperty;

public class LibraryBuilder implements DataBuilder {
	
	private final List<String> libraries;

	public LibraryBuilder(List<String> libraries) {
		this.libraries = libraries;
	}
	
	public LibraryBuilder(String library) {
		this.libraries = Arrays.asList(library);
	}
	
	public LibraryBuilder(LibraryPackage library) {
		this.libraries = Arrays.asList(library.getValue());
	}

	@Override
	public void addData(StringBuilder content) {
		for(String library : libraries) {
			content.append("import ").append(library).append(";").append(SystemProperty.BREAK_LINE);
		}
	}

}