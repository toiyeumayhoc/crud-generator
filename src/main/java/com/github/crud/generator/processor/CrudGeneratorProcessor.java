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
import javax.lang.model.type.MirroredTypeException;

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
					annotatedClass.setUseLombok(definedAnnotation.useLombok());
					annotatedClass.setRepositoryType(definedAnnotation.repositoryType());
					annotatedClass.setIdType(getIdType(definedAnnotation));
					annotatedClass.setEntityClass(new ClassInfo(annotatedElement.getSimpleName().toString(), annotatedElement.asType().toString()));
					List<? extends Element> enclosedElements = annotatedElement.getEnclosedElements();
					for (Element enclosedElement : enclosedElements) {
						if (enclosedElement.getKind().isField()) {
							annotatedClass.getFields()
									.add(new ClassInfo(enclosedElement.getSimpleName().toString(),enclosedElement.asType().toString()));
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
	
	//Tricky function but still work fine. 
	private static Class<?> getIdType(CrudGenerator annotation) {
	    try {
	        annotation.idType();
	    } catch( MirroredTypeException mte ) {
	    	try {
				Class<?> cls = Class.forName(mte.getTypeMirror().toString());
				return cls;
			} catch (ClassNotFoundException e) {
			}
	    }
	    return String.class;
	}


}
