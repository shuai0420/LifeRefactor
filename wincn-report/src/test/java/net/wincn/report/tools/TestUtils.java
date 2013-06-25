package net.wincn.report.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestUtils {
	@Test
	public void listFilesTest() {
		List<File> files = new ArrayList<File>();
		files = FileUtils.listFiles("F:\\test\\**/*.pdf");
		for (File file : files) {
			System.out.println(file.getAbsolutePath());
		}
	}

	public static void main(String[] args) {
		List<File> files = new ArrayList<File>();
		System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());

		files = FileUtils.listFiles("F:\\test\\**/2.pdf");
		for (File file : files) {
			System.out.println(file.getAbsolutePath());
		}
		System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
	}
}
