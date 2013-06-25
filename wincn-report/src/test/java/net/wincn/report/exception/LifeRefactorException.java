package net.wincn.report.exception;

public class LifeRefactorException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LifeRefactorException() {
		super();
	}

	public LifeRefactorException(String msg) {
		super(msg);
	}

	public LifeRefactorException(String msg, Throwable e) {
		super(msg, e);
	}
}
