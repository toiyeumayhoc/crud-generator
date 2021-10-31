package com.github.crud.generator.annotation;

public enum AnnotationName {
	CRUD_GENERATOR("CrudGenerator");
	 
    private AnnotationName(final String value) {
        this.value = value;
    }

    private String value;
 
    public String getValue() {
        return value;
    }
}
