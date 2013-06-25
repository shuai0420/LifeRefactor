package net.wincn.report.model;

import net.wincn.report.annotation.SheetAnno;

@SheetAnno(sheetName = "sheetTest")
public class Model {
	private int id;
	private String name;
	private String title;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
