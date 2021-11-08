package com.github.crud.generator.data.builder;

import org.apache.commons.lang3.StringUtils;

import com.github.crud.generator.constant.ImportedAnnotation;
import com.github.crud.generator.constant.SystemProperty;
import com.github.crud.generator.domain.AnnotatedClass;
import com.github.crud.generator.service.FileInforRetreiver;
import com.github.crud.generator.serviceImpl.MongoRepositoryFileGenerator;
import com.github.crud.generator.serviceImpl.RequestFileGenerator;
import com.github.crud.generator.serviceImpl.ResponseFileGenerator;

public class ServiceImplementContentBuilder implements DataBuilder {

	private final AnnotatedClass annotatedClass;
	private final FileInforRetreiver request;
	private final FileInforRetreiver response;
	private final FileInforRetreiver repository;
	private final String fromEntity;

	public ServiceImplementContentBuilder(AnnotatedClass annotatedClass) {
		this.annotatedClass = annotatedClass;
		this.request = new RequestFileGenerator(annotatedClass);
		this.response = new ResponseFileGenerator(annotatedClass);
		this.repository = new MongoRepositoryFileGenerator(annotatedClass);
		this.fromEntity = new StringBuilder(this.response.getName()).append(".from")
				.append(StringUtils.capitalize(this.annotatedClass.getEntityClass().getName())).append("(entity)")
				.toString();
	}

	@Override
	public void addData(StringBuilder content) {
		addRepository(content);
		addActionMethod(content, "create");
		addActionMethod(content, "update");
		addDeleteMethod(content);
		addGetMethod(content);
		addListMethod(content);
	}

	private void addRepository(StringBuilder content) {
		content.append(SystemProperty.BREAK_LINE).append("	").append(ImportedAnnotation.AUTOWIRED.getValue()).append(SystemProperty.BREAK_LINE)
				.append("	").append(StringUtils.capitalize(repository.getName())).append(" ")
				.append(StringUtils.uncapitalize(repository.getName())).append(";").append(SystemProperty.BREAK_LINE);
	}

	private void addActionMethod(StringBuilder content, String action) {
		content.append(SystemProperty.BREAK_LINE).append("	").append(ImportedAnnotation.OVERRIDE.getValue()).append(SystemProperty.BREAK_LINE);

		content.append("	public ").append(this.response.getName()).append(" ").append(action)
				.append(StringUtils.capitalize(this.annotatedClass.getEntityClass().getName())).append("(")
				.append(this.request.getName()).append(" request) {").append(SystemProperty.BREAK_LINE);

		content.append("		").append(annotatedClass.getEntityClass().getName()).append(" entity = ")
				.append(StringUtils.uncapitalize(repository.getName())).append(".save(request.to")
				.append(StringUtils.capitalize(this.annotatedClass.getEntityClass().getName())).append("());")
				.append(SystemProperty.BREAK_LINE);

		content.append("		").append("return ").append(this.fromEntity).append(";")
				.append(SystemProperty.BREAK_LINE).append("	}").append(SystemProperty.BREAK_LINE);

	}

	private void addDeleteMethod(StringBuilder content) {
		content.append(SystemProperty.BREAK_LINE).append("	").append(ImportedAnnotation.OVERRIDE.getValue()).append(SystemProperty.BREAK_LINE);

		content.append("	public ").append(this.response.getName()).append(" delete")
				.append(StringUtils.capitalize(this.annotatedClass.getEntityClass().getName())).append("(")
				.append(this.request.getName()).append(" request) {").append(SystemProperty.BREAK_LINE);

		content.append("		").append(StringUtils.uncapitalize(repository.getName())).append(".delete(request.to")
				.append(StringUtils.capitalize(this.annotatedClass.getEntityClass().getName())).append("());")
				.append(SystemProperty.BREAK_LINE);

		content.append("		").append("return ").append(this.response.getName()).append(".from")
				.append(StringUtils.capitalize(this.annotatedClass.getEntityClass().getName())).append("(request.to")
				.append(StringUtils.capitalize(this.annotatedClass.getEntityClass().getName())).append("());")
				.append(SystemProperty.BREAK_LINE).append("	}").append(SystemProperty.BREAK_LINE);

	}

	private void addGetMethod(StringBuilder content) {
		content.append(SystemProperty.BREAK_LINE).append("	").append(ImportedAnnotation.OVERRIDE.getValue()).append(SystemProperty.BREAK_LINE);

		content.append("	public ").append(this.response.getName()).append(" get")
				.append(StringUtils.capitalize(this.annotatedClass.getEntityClass().getName())).append("(")
				.append("String ").append(" id) {").append(SystemProperty.BREAK_LINE);

		content.append("		Optional<").append(this.annotatedClass.getEntityClass().getName()).append(">")
				.append(" entity = ").append(StringUtils.uncapitalize(repository.getName())).append(".findById(id);")
				.append(SystemProperty.BREAK_LINE);

		content.append("		if(entity.isPresent()) {").append(SystemProperty.BREAK_LINE);
		content.append("			return ").append(this.response.getName()).append(".from")
				.append(StringUtils.capitalize(this.annotatedClass.getEntityClass().getName()))
				.append("(entity.get());").append(SystemProperty.BREAK_LINE).append("		}")
				.append(SystemProperty.BREAK_LINE);
		content.append("		return new ").append(this.response.getName()).append("();")
				.append(SystemProperty.BREAK_LINE).append("	}").append(SystemProperty.BREAK_LINE);

	}

	private void addListMethod(StringBuilder content) {
		content.append(SystemProperty.BREAK_LINE).append("	").append(ImportedAnnotation.OVERRIDE.getValue()).append(SystemProperty.BREAK_LINE);

		content.append("	public List<").append(this.response.getName()).append("> list")
				.append(StringUtils.capitalize(this.annotatedClass.getEntityClass().getName())).append("s() {")
				.append(SystemProperty.BREAK_LINE);

		content.append("		return ").append(StringUtils.uncapitalize(repository.getName()))
				.append(".findAll().stream().map(entity -> ").append(this.fromEntity)
				.append(").collect(Collectors.toList());").append(SystemProperty.BREAK_LINE)
				.append(SystemProperty.BREAK_LINE).append("	}").append(SystemProperty.BREAK_LINE);

	}

}
