package com.github.crud.generator.data.builder;

import java.util.Set;
import java.util.stream.Collectors;

import com.github.crud.generator.constant.SystemProperty;
import com.github.crud.generator.domain.AnnotatedClass;
import com.github.crud.generator.domain.ClassInfo;

public class FieldLibraryBuilder implements DataBuilder {

	protected final AnnotatedClass annotatedClass;

	public FieldLibraryBuilder(AnnotatedClass annotatedClass) {
		this.annotatedClass = annotatedClass;
	}

	@Override
	public void addData(StringBuilder content) {
		Set<String> importedClasses = this.annotatedClass.getFields().stream().map(ClassInfo::getClassType)
				.filter(classType -> classType != null).collect(Collectors.toSet());
		importedClasses.forEach(importedClass -> {
			content.append("import ").append(importedClass).append(";").append(SystemProperty.BREAK_LINE);
		});

	}

}
