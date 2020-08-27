package com.openthinks.wx.hack.http.base.support.htmlunit;

public class UnExpectedOperationException extends RuntimeException {

	private static final long serialVersionUID = -951007533367752011L;

	public UnExpectedOperationException() {
	}

	public UnExpectedOperationException(String message) {
		super(message);
	}

	public UnExpectedOperationException(Throwable cause) {
		super(cause);
	}

	public UnExpectedOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnExpectedOperationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
