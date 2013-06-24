package net.wincn.report.exception;

public class CreateExcelException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateExcelException() {

	}

	public CreateExcelException(String msg, Throwable e) {
		new Exception(msg, e);
	}
}
