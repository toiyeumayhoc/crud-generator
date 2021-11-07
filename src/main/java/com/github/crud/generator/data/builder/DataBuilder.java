package com.github.crud.generator.data.builder;

@FunctionalInterface
public interface DataBuilder {
	void addData(StringBuilder content);
}
