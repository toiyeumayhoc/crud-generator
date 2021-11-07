package com.github.crud.generator.data.builder;

import org.apache.commons.lang3.StringUtils;

import com.github.crud.generator.constant.SystemProperty;
import com.github.crud.generator.domain.AnnotatedClass;
import com.github.crud.generator.domain.ClassInfo;

public class GetterSetterBuilder implements DataBuilder {
	private final AnnotatedClass annotatedClass;
	
	public GetterSetterBuilder(AnnotatedClass annotatedClass) {
		this.annotatedClass = annotatedClass;
	}

	@Override
	public void addData(StringBuilder content) {
		for(ClassInfo classInfo : this.annotatedClass.getFields()) {
			addGetter(content, classInfo);
			addSetter(content, classInfo);
		}
	}
	
	private void addGetter(StringBuilder content, ClassInfo classInfo) {
		content.append("	public ").append(classInfo.getTypeName()).append(" get").append(StringUtils.capitalize(classInfo.getName()))
		.append("() {").append(SystemProperty.BREAK_LINE)
		.append("		return this.").append(classInfo.getName()).append(";").append(SystemProperty.BREAK_LINE)
		.append("	}").append(SystemProperty.BREAK_LINE);
	}
	
	private void addSetter(StringBuilder content, ClassInfo classInfo) {
		content.append("	public void set").append(StringUtils.capitalize(classInfo.getName()))
		.append("(").append(classInfo.getTypeName()).append(" ").append(classInfo.getName()).append(") {").append(SystemProperty.BREAK_LINE)
		.append("		this.").append(classInfo.getName()).append(" = ").append(classInfo.getName()).append(";").append(SystemProperty.BREAK_LINE)
		.append("	}").append(SystemProperty.BREAK_LINE);
	}

}
