package com.openthinks.wx.hack.http.base.support;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;

/**
 * Web session which hold one {@link WebClient}
 * 
 * @author dailey.yet@outlook.com
 * @date 3/7/2016
 */
public interface WebSession {

	/**
	 * this method should called before do other operations
	 */
	public void loginIfNecessary();

	public Page fetchPage(WebRequest request) throws Exception;

	public void logoutIfNecessary();

	public void exec(Operations operations);

	/**
	 * judge the session of this connection is out or still alive
	 * @return boolean
	 */
	public boolean isSessionOut();

	/**
	 * provider a way that get the inner data in this instance; for example, most time, the token and login name are used usually.
	 * @param property String
	 * @return Object property value
	 */
	public <T extends Object> T lookup(String property);

	/**
	 * call when user logout from the front web system; this method should remove instance and any reference especially in the {@link WebSessionPool}
	 */
	public void destroy();

	@Deprecated
	public WebClient getInstance();
}
