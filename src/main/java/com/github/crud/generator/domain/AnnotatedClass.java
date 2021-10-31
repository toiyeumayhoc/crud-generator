package com.github.crud.generator.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class AnnotatedClass {

	private ClassInfo entityClass;
	private List<ClassInfo> fields;
	private String basePackage;
	
	public AnnotatedClass() {
		this.fields = new ArrayList<>();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Base Package : ").append(basePackage).append(System.getProperty("line.separator"));
		sb.append("Annotated class : ").append(entityClass.getType()).append(" ").append(entityClass.getName());
		if(fields != null && fields.size()!=0 ) {
			sb.append(System.getProperty("line.separator"));
			for(ClassInfo field : fields) {
				sb.append("Field : ").append(field.getType()).append(" ").append(field.getName());
				sb.append(System.getProperty("line.separator"));
			}
		}
		return sb.toString();
	}
}
