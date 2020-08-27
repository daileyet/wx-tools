package com.openthinks.wx.hack.http.base.support;

import com.openthinks.wx.hack.http.base.support.htmlunit.SimpleNamePassWebSecurity;
import com.openthinks.wx.hack.http.base.support.htmlunit.SimpleSessionIdWebSecurity;

/**
 * {@link WebSecurity} simple factory
 * 
 * @author dailey.yet@outlook.com
 * @date 3/7/2016
 */
public final class WebSecuritys {
	private WebSecuritys() {
	}

	public static final NamePassWebSecurity simpleNP(final String name, final String pass) {
		return new SimpleNamePassWebSecurity(name,pass) ;
	}

	public static final SessionIdWebSecurity simpleSID(final String sessionId) {
		return new SimpleSessionIdWebSecurity(sessionId) ;
	}

}
