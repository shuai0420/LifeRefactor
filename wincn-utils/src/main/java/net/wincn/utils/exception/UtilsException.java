package net.wincn.utils.exception;

public class UtilsException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UtilsException() {
		super();
	}

	public UtilsException(String msg) {
		super(msg);
	}

	public UtilsException(String msg, Throwable e) {
		super(msg, e);
	}
}
