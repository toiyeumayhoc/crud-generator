package com.github.crud.generator.data.builder;

import java.util.ArrayList;
import java.util.List;

public class BuilderPipeline {
	private final List<DataBuilder> dataBuilders;

	public BuilderPipeline() {
		this.dataBuilders = new ArrayList<>();
	}
	
	public BuilderPipeline(DataBuilder newBuilder) {
		this.dataBuilders = new ArrayList<>();
		this.dataBuilders.add(newBuilder);
	}
	
	public BuilderPipeline add(DataBuilder newBuilder) {
		this.dataBuilders.add(newBuilder);
		return this;
	}
	
	public String buildContent() {
		StringBuilder content = new StringBuilder();
		for(DataBuilder dataBuilder : dataBuilders) {
			dataBuilder.addData(content);
		}
		return content.toString();
	}
	
}
