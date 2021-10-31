package com.github.crud.generator.service;

import java.util.List;

import com.github.crud.generator.domain.AnnotatedClass;

public class FileGeneratorService {
	
	private final List<AnnotatedClass> annotatedClasses;
	
	public FileGeneratorService(List<AnnotatedClass> annotatedClasses) {
		this.annotatedClasses = annotatedClasses;
	}
	
	public void generate() {
		for(AnnotatedClass annotatedClass : annotatedClasses) {
			List<FileGenerator> fileGenerators = FileGeneratorFactory.getAllGenerators(annotatedClass);
			for(FileGenerator fileGenerator : fileGenerators) {
				fileGenerator.excecute();
			}
		}
	}

}
