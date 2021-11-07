package com.github.crud.generator.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.github.crud.generator.constant.SystemProperty;
import com.github.crud.generator.data.builder.BuilderPipeline;
import com.github.crud.generator.data.builder.DataBuilder;
import com.github.crud.generator.domain.AnnotatedClass;

public abstract class FileGeneratorAbstract implements FileGenerator {

	protected final AnnotatedClass annotatedClass;
	protected final String rootPath;

	public FileGeneratorAbstract(AnnotatedClass annotatedClass) {
		this.annotatedClass = annotatedClass;
		this.rootPath = getRootPath();

	}

	@Override
	public void excecute() {
		try {
			String filePath = getFilePath();
			String data = getBuilderPipeline().buildContent();
			File file = new File(filePath.toString());
			file.getParentFile().mkdirs();
			if (file.createNewFile()) {
				FileWriter writer = new FileWriter(file.getAbsoluteFile());
				BufferedWriter buffer = new BufferedWriter(writer);
				buffer.write(data);
				buffer.close();
				System.out.println("Created: " + file.getName() + " successfully!");
			} else {
				System.out.println(file.getName() + " already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	protected abstract String getFilePath();
	protected abstract BuilderPipeline getBuilderPipeline();

	private String getRootPath() {
		StringBuilder sb = new StringBuilder("src").append(SystemProperty.SEPATATOR).append("main").append(SystemProperty.SEPATATOR).append("java")
				.append(SystemProperty.SEPATATOR);
		String[] folderNames = annotatedClass.getBasePackage().split("\\.");
		for (String folerName : folderNames) {
			sb.append(folerName).append(SystemProperty.SEPATATOR);
		}
		return sb.toString();
	}
}
