package com.github.crud.generator.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import com.github.crud.generator.annotation.AnnotationName;
import com.github.crud.generator.annotation.CrudGenerator;
import com.github.crud.generator.domain.AnnotatedClass;
import com.github.crud.generator.domain.ClassInfo;
import com.github.crud.generator.service.FileGeneratorService;
import com.google.auto.service.AutoService;

@SupportedAnnotationTypes("com.github.crud.generator.annotation.CrudGenerator")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class CrudGeneratorProcessor extends AbstractProcessor {
	
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		List<AnnotatedClass> annotatedClasses = new ArrayList<>();
		for (TypeElement annotation : annotations) {
			if (AnnotationName.CRUD_GENERATOR.getValue().equals(annotation.getSimpleName().toString())) {
				Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
				annotatedElements.forEach(annotatedElement -> {
					AnnotatedClass annotatedClass = new AnnotatedClass();
					CrudGenerator definedAnnotation = annotatedElement.getAnnotation(CrudGenerator.class);
					annotatedClass.setBasePackage(definedAnnotation.basePackage());
					annotatedClass.setEntityClass(ClassInfo.builder().name(annotatedElement.getSimpleName().toString())
							.type(annotatedElement.asType().toString()).build());
					List<? extends Element> enclosedElements = annotatedElement.getEnclosedElements();
					for (Element enclosedElement : enclosedElements) {
						if (enclosedElement.getKind().isField()) {
							annotatedClass.getFields()
									.add(ClassInfo.builder().name(enclosedElement.getSimpleName().toString())
											.type(enclosedElement.asType().toString()).build());
						}
					}
					annotatedClasses.add(annotatedClass);
				});
			}
		}
		FileGeneratorService fileGeneratorService = new FileGeneratorService(annotatedClasses);
		fileGeneratorService.generate();
		return true;
	}
	


}
