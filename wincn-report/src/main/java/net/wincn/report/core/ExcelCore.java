package net.wincn.report.core;

import java.io.File;
import java.io.IOException;

import net.wincn.report.exception.ReportException;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * 创建excel的类
 * 
 * @project wincn-report
 * @author gefangshuai
 * @email gefangshuai@163.com
 * @createDate 2013-6-24 下午3:32:23
 */
public class ExcelCore {
	/**
	 * 创建Excel
	 * 
	 * @param fileName
	 *            目标文件名
	 * @param sheetName
	 *            工作表名
	 * @throws IOException
	 */
	public void createExcel(String fileName, String sheetName) {
		WritableWorkbook workbook = null;
		WritableSheet sheet = null;
		try {
			workbook = Workbook.createWorkbook(new File(fileName));
			sheet = workbook.createSheet(sheetName, 0);
			workbook.write();
		} catch (IOException e) {
			throw new ReportException("目标文件无效！", e);
		} finally {
			try {
				workbook.close();
			} catch (WriteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
