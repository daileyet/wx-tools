package com.openthinks.wx.hack.http.base.support;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openthinks.wx.hack.http.base.support.htmlunit.UnExpectedOperationException;


public class OperationAdapter<E> implements Operation<E> {
	protected Map<String, Object> referenceCnotext;
	private final static Logger LOGGER = LoggerFactory.getLogger(OperationAdapter.class);

	public void run() {
		try {
			execute();
		} catch (Exception e) {
			LOGGER.warn("Operation Exception: ", e);
			if (!isContinueWhenError()) {
				throw new UnExpectedOperationException(e);
			}
		}
	}

	protected void execute() throws Exception {
		// TODO 
	}

	public void setContext(final Map<String, Object> context) {
		this.referenceCnotext = context;
	}

	public boolean isContinueWhenError() {
		return true;
	}

	public E getResult() {
		return null;
	}

}
