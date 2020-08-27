package com.soecode.wxtools.api;

import com.soecode.wxtools.exception.WxErrorException;
@FunctionalInterface
public interface WxErrorExceptionHandler {

	void handle(WxErrorException e);

}
