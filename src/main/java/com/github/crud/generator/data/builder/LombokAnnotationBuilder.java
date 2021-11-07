package com.github.crud.generator.data.builder;

import com.github.crud.generator.constant.SystemProperty;

public class LombokAnnotationBuilder implements DataBuilder {

	@Override
	public void addData(StringBuilder content) {
		content.append("@Data").append(SystemProperty.BREAK_LINE);
		content.append("@Builder").append(SystemProperty.BREAK_LINE);
		content.append("@NoArgsConstructor").append(SystemProperty.BREAK_LINE);
		content.append("@AllArgsConstructor").append(SystemProperty.BREAK_LINE);
	}
	
}
