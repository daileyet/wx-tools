package com.openthinks.wx.hack.http.base.support.htmlunit;

import com.openthinks.wx.hack.http.base.support.WebSecurity;
import com.openthinks.wx.hack.http.base.support.WebSession;
import com.openthinks.wx.hack.http.base.support.WebSessionPool;
import com.openthinks.wx.hack.http.base.support.WebSessionProvider;

public class WeixinmpSessionProvider implements WebSessionProvider {
	private WebSessionPool wsPool = WeixinmpSessionPool.getInstance();

	public WebSession get(WebSecurity security) {
		WebSession ws = wsPool.lookup(security);
		if (ws == null || ws.isSessionOut()) {
			ws = wsPool.updateAndRrefresh(security);
		}
		return ws;
	}

}
