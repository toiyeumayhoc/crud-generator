package com.github.crud.generator.service;

import java.util.ArrayList;
import java.util.List;

import com.github.crud.generator.domain.AnnotatedClass;
import com.github.crud.generator.serviceImpl.ControllerFileGenerator;
import com.github.crud.generator.serviceImpl.ExtendsRepositoryFileGenerator;
import com.github.crud.generator.serviceImpl.MongoRepositoryFileGenerator;
import com.github.crud.generator.serviceImpl.ReponseLombokFileGenerator;
import com.github.crud.generator.serviceImpl.RepositoryImplementFileGenerator;
import com.github.crud.generator.serviceImpl.RequestFileGenerator;
import com.github.crud.generator.serviceImpl.RequestLombokFileGenerator;
import com.github.crud.generator.serviceImpl.ResponseFileGenerator;
import com.github.crud.generator.serviceImpl.ServiceFileGenerator;
import com.github.crud.generator.serviceImpl.ServiceImplementFileGenerator;

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
		fileGenerators.add(new MongoRepositoryFileGenerator(annotatedClass));
		fileGenerators.add(new ExtendsRepositoryFileGenerator(annotatedClass));
		fileGenerators.add(new RepositoryImplementFileGenerator(annotatedClass));
		fileGenerators.add(new ServiceFileGenerator(annotatedClass));
		fileGenerators.add(new ServiceImplementFileGenerator(annotatedClass));
		fileGenerators.add(new ControllerFileGenerator(annotatedClass));
		return fileGenerators;
	}

}
