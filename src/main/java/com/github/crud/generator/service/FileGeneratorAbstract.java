package com.github.crud.generator.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;

import com.github.crud.generator.domain.AnnotatedClass;

public abstract class FileGeneratorAbstract implements FileGenerator {

	protected final AnnotatedClass annotatedClass;
	protected final String rootPath;
	protected final String SEPATATOR = FileSystems.getDefault().getSeparator();

	public FileGeneratorAbstract(AnnotatedClass annotatedClass) {
		this.annotatedClass = annotatedClass;
		this.rootPath = getRootPath();

	}

	@Override
	public void excecute() {
		try {
			String filePath = getFilePath();
			String data = getfileData();
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

	protected abstract String getfileData();

	private String getRootPath() {
		StringBuilder sb = new StringBuilder("src").append(SEPATATOR).append("main").append(SEPATATOR).append("java")
				.append(SEPATATOR);
		String[] folderNames = annotatedClass.getBasePackage().split("\\.");
		for (String folerName : folderNames) {
			sb.append(folerName).append(SEPATATOR);
		}
		return sb.toString();
	}

}
