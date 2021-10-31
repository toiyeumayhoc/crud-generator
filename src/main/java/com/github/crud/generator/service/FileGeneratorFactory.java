package com.github.crud.generator.service;

import java.util.ArrayList;
import java.util.List;

import com.github.crud.generator.domain.AnnotatedClass;
import com.github.crud.generator.serviceImpl.RequestFileGenerator;

public class FileGeneratorFactory {
	
	public static List<FileGenerator> getAllGenerators(AnnotatedClass annotatedClass) {
		List<FileGenerator> fileGenerators = new ArrayList<>();
		fileGenerators.add(new RequestFileGenerator(annotatedClass));
		return fileGenerators;
	}

}
