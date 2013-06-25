package net.wincn.report.annotation;

import net.wincn.report.tools.ClassesLoader;

import org.junit.Test;

public class AnnotationTest {
	@Test
	public void scanClass() {
		ClassesLoader loader = new ClassesLoader();
		loader.setClassPack("net.*");
		for (Class c : loader.loadClasses()) {
			System.out.println(c);
		}

	}
}
