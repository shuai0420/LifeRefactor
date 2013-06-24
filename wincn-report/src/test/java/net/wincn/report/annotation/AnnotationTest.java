package net.wincn.report.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import net.wincn.report.model.Model;

public class AnnotationTest {
	public static void main(String[] args) {
		Method[] methods = Model.class.getDeclaredMethods();
		for (Method method : methods) {
			/*
			 * 判断方法中是否有指定注解类型的注解
			 */
			boolean hasAnnotation = method.isAnnotationPresent(SheetAnno.class);
			if (hasAnnotation) {
				/*
				 * 根据注解类型返回方法的指定类型注解
				 */
				SheetAnno annotation = method.getAnnotation(SheetAnno.class);
				System.out.println(annotation.sheetName());
			}
		}
		
		SheetAnno annotation = Model.class.getAnnotation(SheetAnno.class);
		System.out.println(annotation.sheetName());
	}
}
