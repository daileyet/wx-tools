package com.openthinks.wx.hack.http.base.support;

/**
 * The pool of {@link WebSession}, will be used in {@link WebSessionProvider}.
 * It will be cached by the value of {@link WebSecurity#getName()} as key for
 * {@link WebSession}
 * 
 * @author dailey.yet@outlook.com
 * @date 3/7/2016
 */
public interface WebSessionPool {

	WebSession lookup(WebSecurity security);

	WebSession updateAndRrefresh(WebSecurity security);

}
