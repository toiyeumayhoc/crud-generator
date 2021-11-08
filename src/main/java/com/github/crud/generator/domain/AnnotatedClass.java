package com.github.crud.generator.domain;

import java.util.ArrayList;
import java.util.List;

import com.github.crud.generator.constant.RepositoryType;

import lombok.Data;

@Data
public class AnnotatedClass {

	private ClassInfo entityClass;
	private List<ClassInfo> fields;
	private String basePackage;
	private boolean useLombok;
	private RepositoryType repositoryType;
	private Class<?> idType;
	
	public AnnotatedClass() {
		this.fields = new ArrayList<>();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Base Package : ").append(basePackage).append(System.getProperty("line.separator"));
		sb.append("Annotated class : ").append(entityClass.getClassType()).append(" ").append(entityClass.getName());
		if(fields != null && fields.size()!=0 ) {
			sb.append(System.getProperty("line.separator"));
			for(ClassInfo field : fields) {
				sb.append("Field : ").append(field.getClassType()).append(" ").append(field.getName());
				sb.append(System.getProperty("line.separator"));
			}
		}
		return sb.toString();
	}
}
