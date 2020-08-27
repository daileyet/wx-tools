package com.soecode.wxtools.api;

import com.soecode.wxtools.bean.WxXmlMessage;
@FunctionalInterface
public interface WxMessageMatcher {

	boolean match(WxXmlMessage message);

}
