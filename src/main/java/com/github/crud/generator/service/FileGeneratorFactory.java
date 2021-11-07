package com.github.crud.generator.service;

import java.util.ArrayList;
import java.util.List;

import com.github.crud.generator.domain.AnnotatedClass;
import com.github.crud.generator.serviceImpl.ReponseLombokFileGenerator;
import com.github.crud.generator.serviceImpl.RequestFileGenerator;
import com.github.crud.generator.serviceImpl.RequestLombokFileGenerator;
import com.github.crud.generator.serviceImpl.ResponseFileGenerator;

public class FileGeneratorFactory {
	
	public static List<FileGenerator> getAllGenerators(AnnotatedClass annotatedClass) {
		List<FileGenerator> fileGenerators = new ArrayList<>();
		if(annotatedClass.isUseLombok()) {
			fileGenerators.add(new RequestLombokFileGenerator(annotatedClass));
			fileGenerators.add(new ReponseLombokFileGenerator(annotatedClass));
		} else {
			fileGenerators.add(new RequestFileGenerator(annotatedClass));
			fileGenerators.add(new ResponseFileGenerator(annotatedClass));
		}
		
		return fileGenerators;
	}

}
