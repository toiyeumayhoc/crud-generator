package com.github.crud.generator.data.builder;

import com.github.crud.generator.constant.SystemProperty;
import com.github.crud.generator.domain.AnnotatedClass;

public class EntityImportBuilder extends FieldLibraryBuilder {

	public EntityImportBuilder(AnnotatedClass annotatedClass) {
		super(annotatedClass);
	}
	
	@Override
	public void addData(StringBuilder content) {
		content.append("import ").append(annotatedClass.getEntityClass().getClassType()).append(";").append(SystemProperty.BREAK_LINE);
	}


}
