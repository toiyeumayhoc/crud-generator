package com.github.crud.generator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.crud.generator.constant.RepositoryType;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface CrudGenerator {
	
	String basePackage();
	
	boolean useLombok() default true;
	
	RepositoryType repositoryType() default RepositoryType.MONGO;
	
	Class<?> idType() default String.class;

}
