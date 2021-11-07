package com.github.crud.generator.data.builder;

import com.github.crud.generator.constant.SystemProperty;

public class LombokLibraryBuilder implements DataBuilder {

	public LombokLibraryBuilder() {
	}

	@Override
	public void addData(StringBuilder content) {
		content.append("import lombok.AllArgsConstructor;").append(SystemProperty.BREAK_LINE);
		content.append("import lombok.Builder;").append(SystemProperty.BREAK_LINE);
		content.append("import lombok.Data;").append(SystemProperty.BREAK_LINE);
		content.append("import lombok.NoArgsConstructor;").append(SystemProperty.BREAK_LINE);
	}

}
