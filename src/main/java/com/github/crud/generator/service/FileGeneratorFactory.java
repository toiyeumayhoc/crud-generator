package com.github.crud.generator.service;

import java.util.ArrayList;
import java.util.List;

import com.github.crud.generator.domain.AnnotatedClass;
import com.github.crud.generator.serviceImpl.RequestFileGenerator;
import com.github.crud.generator.serviceImpl.RequestLombokFileGenerator;

public class FileGeneratorFactory {
	private static final boolean useLombok = true;
	
	public static List<FileGenerator> getAllGenerators(AnnotatedClass annotatedClass) {
		List<FileGenerator> fileGenerators = new ArrayList<>();
		if(useLombok) {
			fileGenerators.add(new RequestLombokFileGenerator(annotatedClass));
		} else {
			fileGenerators.add(new RequestFileGenerator(annotatedClass));
		}
		
		return fileGenerators;
	}

}
