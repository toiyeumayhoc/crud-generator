package com.github.crud.generator.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClassInfo {
	
	public ClassInfo(String name, String classType) {
		this.name = name;
		if(classType != null && classType.contains(".")) {
			this.classType = classType;
			String[] folderSplit = classType.split("\\.");
			this.typeName = folderSplit[folderSplit.length-1];
		} else {
			this.typeName = classType;
		}
	}
	
	private String name;
	private String classType;
	private String typeName;
	
}
