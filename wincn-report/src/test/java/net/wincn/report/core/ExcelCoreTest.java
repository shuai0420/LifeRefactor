package net.wincn.report.core;

import org.junit.Test;

/**
 * 创建excel的类
 * 
 * @project wincn-report
 * @author gefangshuai
 * @email gefangshuai@163.com
 * @createDate 2013-6-24 下午3:32:23
 */
public class ExcelCoreTest {
	
	@Test
	public void createExcel() {
		new ExcelCore().createExcel("F:/test/jxl/1.xls", "table1");
	}
	
}
