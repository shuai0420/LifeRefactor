package net.wincn.utils.classes;

import java.util.Set;

/**
 * 扫描java，将指定的java类加载到内存
 * 
 * @project wincn-report
 * @author gefangshuai
 * @email gefangshuai@163.com
 * @createDate 2013-6-25 上午11:02:15
 */
public class ClassesLoader {
	private String classPack;
	boolean recursive = false;

	public String getClassPack() {
		return classPack;
	}

	public void setClassPack(String classPack) {
		this.classPack = classPack;
	}

	/**
	 * 从包package中获取所有的Class
	 * 
	 * @return
	 */
	public Set<Class<?>> loadClasses() {
		
		return null;
	}

}
