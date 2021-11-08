package com.github.crud.generator.serviceImpl;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.github.crud.generator.constant.SystemProperty;
import com.github.crud.generator.data.builder.BuilderPipeline;
import com.github.crud.generator.data.builder.InterfaceContentBuilder;
import com.github.crud.generator.data.builder.PackageBuilder;
import com.github.crud.generator.domain.AnnotatedClass;
import com.github.crud.generator.service.FileGeneratorAbstract;
import com.github.crud.generator.service.FileInforRetreiver;

public class ExtendsRepositoryFileGenerator extends FileGeneratorAbstract implements FileInforRetreiver {

	private final String FOLDER = "repository";
	private final String SUB_FOLDER = "extend";
	private final String FILE_NAME;
	protected final String CLASS_NAME;
	
	public ExtendsRepositoryFileGenerator(AnnotatedClass annotatedClass) {
		super(annotatedClass);
		this.CLASS_NAME = StringUtils.capitalize(annotatedClass.getEntityClass().getName()) + "RepositoryExtend";
		this.FILE_NAME = CLASS_NAME + ".java";
 	}

	@Override
	protected String getFilePath() {
		return this.rootPath + this.FOLDER + SystemProperty.SEPATATOR + this.SUB_FOLDER + SystemProperty.SEPATATOR + this.FILE_NAME;
	}
	
	@Override
	protected BuilderPipeline getBuilderPipeline() {
		return new BuilderPipeline().add(new PackageBuilder(annotatedClass.getBasePackage(), FOLDER + "." + SUB_FOLDER))
				.add(new InterfaceContentBuilder(CLASS_NAME, Arrays.asList()));
	}

	@Override
	public String getPath() {
		return new StringBuilder(annotatedClass.getBasePackage()).append(".").append(FOLDER).append(".").append(SUB_FOLDER).append(".").append(this.CLASS_NAME).toString();
	}

	@Override
	public String getName() {
		return this.CLASS_NAME;
	}

}
