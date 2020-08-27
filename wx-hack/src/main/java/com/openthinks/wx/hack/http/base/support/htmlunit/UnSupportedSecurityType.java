package com.openthinks.wx.hack.http.base.support.htmlunit;

public class UnSupportedSecurityType extends RuntimeException {

	private static final long serialVersionUID = -2236067732099258613L;

	public UnSupportedSecurityType() {
		super();
	}

	public UnSupportedSecurityType(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UnSupportedSecurityType(String message, Throwable cause) {
		super(message, cause);
	}

	public UnSupportedSecurityType(String message) {
		super(message);
	}

	public UnSupportedSecurityType(Throwable cause) {
		super(cause);
	}

}
