package com.github.crud.generator.serviceImpl;

import org.apache.commons.lang3.StringUtils;

import com.github.crud.generator.domain.AnnotatedClass;
import com.github.crud.generator.service.FileGeneratorAbstract;

public class RequestFileGenerator extends FileGeneratorAbstract {
	
	private final String FOLDER = "request";
	private final String FILE_NAME;
	
	public RequestFileGenerator(AnnotatedClass annotatedClass) {
		super(annotatedClass);
		this.FILE_NAME = StringUtils.capitalize(annotatedClass.getEntityClass().getName()) + "Request.java";
 	}

	@Override
	protected String getFilePath() {
		return this.rootPath + this.FOLDER + SEPATATOR + this.FILE_NAME;
	}

	@Override
	protected String getfileData() {
		return "test data for "  + annotatedClass.toString();
	}

}
