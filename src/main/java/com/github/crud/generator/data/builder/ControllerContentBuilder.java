package com.github.crud.generator.data.builder;

import org.apache.commons.lang3.StringUtils;

import com.github.crud.generator.constant.ImportedAnnotation;
import com.github.crud.generator.constant.SystemProperty;
import com.github.crud.generator.domain.AnnotatedClass;
import com.github.crud.generator.service.FileInforRetreiver;
import com.github.crud.generator.serviceImpl.RequestFileGenerator;
import com.github.crud.generator.serviceImpl.ResponseFileGenerator;
import com.github.crud.generator.serviceImpl.ServiceFileGenerator;

public class ControllerContentBuilder implements DataBuilder {

	private final AnnotatedClass annotatedClass;
	private final FileInforRetreiver request;
	private final FileInforRetreiver response;
	private final FileInforRetreiver service;

	public ControllerContentBuilder(AnnotatedClass annotatedClass) {
		this.annotatedClass = annotatedClass;
		this.request = new RequestFileGenerator(annotatedClass);
		this.response = new ResponseFileGenerator(annotatedClass);
		this.service = new ServiceFileGenerator(annotatedClass);
	}

	@Override
	public void addData(StringBuilder content) {
		addService(content);
		addPostMethod(content);
		addPutMethod(content);
		addDeleteMethod(content);
		addListMethod(content);
		addGetMethod(content);
	}

	private void addService(StringBuilder content) {
		content.append(SystemProperty.BREAK_LINE).append("	").append(ImportedAnnotation.AUTOWIRED.getValue()).append(SystemProperty.BREAK_LINE)
				.append("	").append(service.getName()).append(" ").append(StringUtils.uncapitalize(service.getName()))
				.append(";").append(SystemProperty.BREAK_LINE);
	}

	private void addPostMethod(StringBuilder content) {
		content.append(SystemProperty.BREAK_LINE).append("	@PostMapping(\"\")").append(SystemProperty.BREAK_LINE);
		addActionMethod(content, "create");
	}

	private void addPutMethod(StringBuilder content) {
		content.append(SystemProperty.BREAK_LINE).append("	@PutMapping(\"\")").append(SystemProperty.BREAK_LINE);
		addActionMethod(content, "update");
	}

	private void addDeleteMethod(StringBuilder content) {
		content.append(SystemProperty.BREAK_LINE).append("	@DeleteMapping(\"\")").append(SystemProperty.BREAK_LINE);
		addActionMethod(content, "delete");
	}

	private void addActionMethod(StringBuilder content, String action) {

		content.append("	public ").append(this.response.getName()).append(" ").append(action)
				.append(StringUtils.capitalize(this.annotatedClass.getEntityClass().getName())).append("(@RequestBody ")
				.append(this.request.getName()).append(" request) {").append(SystemProperty.BREAK_LINE);

		content.append("		").append("return ").append(StringUtils.uncapitalize(service.getName())).append(".")
				.append(action).append(StringUtils.capitalize(this.annotatedClass.getEntityClass().getName()))
				.append("(request);").append(SystemProperty.BREAK_LINE).append("	}")
				.append(SystemProperty.BREAK_LINE);

	}

	private void addListMethod(StringBuilder content) {
		content.append(SystemProperty.BREAK_LINE).append("	@GetMapping(\"\")").append(SystemProperty.BREAK_LINE);
		content.append("	public List<").append(this.response.getName()).append("> list")
				.append(StringUtils.capitalize(this.annotatedClass.getEntityClass().getName())).append("s() {").append(SystemProperty.BREAK_LINE);

		content.append("		").append("return ").append(StringUtils.uncapitalize(service.getName())).append(".list")
				.append(StringUtils.capitalize(this.annotatedClass.getEntityClass().getName())).append("s();")
				.append(SystemProperty.BREAK_LINE).append("	}").append(SystemProperty.BREAK_LINE);
	}
	
	private void addGetMethod(StringBuilder content) {
		content.append(SystemProperty.BREAK_LINE).append("	@GetMapping(\"/{id}\")").append(SystemProperty.BREAK_LINE);
		content.append("	public ").append(this.response.getName()).append(" get")
				.append(StringUtils.capitalize(this.annotatedClass.getEntityClass().getName())).append("(@PathVariable ").append(annotatedClass.getIdType().getSimpleName()).append(" id) {").append(SystemProperty.BREAK_LINE);

		content.append("		").append("return ").append(StringUtils.uncapitalize(service.getName())).append(".get")
		.append(StringUtils.capitalize(this.annotatedClass.getEntityClass().getName()))
		.append("(id);").append(SystemProperty.BREAK_LINE).append("	}")
		.append(SystemProperty.BREAK_LINE);
	}

}
