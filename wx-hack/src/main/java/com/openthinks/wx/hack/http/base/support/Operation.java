package com.openthinks.wx.hack.http.base.support;

import java.util.Map;

/**
 * represent the details business in each request, will be invoked by
 * {@link WebSession}
 * 
 * @author dailey.yet@outlook.com
 * @date 3/7/2016
 * @param <T>
 */
public interface Operation<T> extends Runnable {

	public void setContext(final Map<String, Object> context);

	/**
	 * identity tag for ignoring exception when {@link Operation#run()} occur
	 * error in runtime. if this value is True, that's mean the next
	 * {@link Operation} in group {@link Operations} will still be invoked.
	 * 
	 * @return boolean
	 */
	public boolean isContinueWhenError();

	/**
	 * get operation result when finished
	 * 
	 * @return T
	 */
	public T getResult();

}
