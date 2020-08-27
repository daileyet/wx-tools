package com.openthinks.wx.hack.http.base.support;

/**
 * {@link WebSession} provider
 * 
 * @author dailey.yet@outlook.com
 * @date 3/7/2016
 */
public interface WebSessionProvider {

	/**
	 * provide {@link WebSession} both by {@link NamePassWebSecurity} and
	 * {@link SessionIdWebSecurity}
	 * 
	 * @param security
	 *            {@link WebSecurity}
	 * @return {@link WebSession}
	 */
	public WebSession get(WebSecurity security);

}
