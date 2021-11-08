package com.github.crud.generator.data.builder;

import org.apache.commons.lang3.StringUtils;

import com.github.crud.generator.constant.SystemProperty;
import com.github.crud.generator.domain.AnnotatedClass;
import com.github.crud.generator.service.FileInforRetreiver;
import com.github.crud.generator.serviceImpl.RequestFileGenerator;
import com.github.crud.generator.serviceImpl.ResponseFileGenerator;

public class ServicePrototypeBuilder implements DataBuilder {

	private final AnnotatedClass annotatedClass;
	private final FileInforRetreiver request;
	private final FileInforRetreiver response;

	public ServicePrototypeBuilder(AnnotatedClass annotatedClass) {
		this.annotatedClass = annotatedClass;
		this.request = new RequestFileGenerator(annotatedClass);
		this.response = new ResponseFileGenerator(annotatedClass);
	}

	@Override
	public void addData(StringBuilder content) {
		addActionMethod(content, "create");
		addActionMethod(content, "update");
		addActionMethod(content, "delete");
		addListMethod(content);
		addGetMethod(content);
	}
	
	
	private void addActionMethod(StringBuilder content, String action) {
		content.append("	public ").append(this.response.getName()).append(" ").append(action)
		.append(StringUtils.capitalize(this.annotatedClass.getEntityClass().getName()))
		.append("(").append(this.request.getName()).append(" request);").append(SystemProperty.BREAK_LINE);
	}
	
	private void addListMethod(StringBuilder content) {
		content.append("	public List<").append(this.response.getName()).append("> list")
		.append(StringUtils.capitalize(this.annotatedClass.getEntityClass().getName()))
		.append("s();").append(SystemProperty.BREAK_LINE);
	}
	
	private void addGetMethod(StringBuilder content) {
		content.append("	public ").append(this.response.getName()).append(" get")
		.append(StringUtils.capitalize(this.annotatedClass.getEntityClass().getName())).append("(")
		.append(annotatedClass.getIdType().getSimpleName())
		.append(" id);").append(SystemProperty.BREAK_LINE);
	}

}
