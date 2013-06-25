package net.wincn.report.exception;

public class ReportException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReportException() {
		super();
	}

	public ReportException(String msg) {
		super(msg);
	}

	public ReportException(String msg, Throwable e) {
		super(msg, e);
	}
}
