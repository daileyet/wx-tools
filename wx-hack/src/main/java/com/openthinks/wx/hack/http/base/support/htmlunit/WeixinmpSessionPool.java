package com.openthinks.wx.hack.http.base.support.htmlunit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.openthinks.wx.hack.http.base.support.NamePassWebSecurity;
import com.openthinks.wx.hack.http.base.support.SessionIdWebSecurity;
import com.openthinks.wx.hack.http.base.support.WebSecurity;
import com.openthinks.wx.hack.http.base.support.WebSession;
import com.openthinks.wx.hack.http.base.support.WebSessionPool;

public final class WeixinmpSessionPool implements WebSessionPool {
	private final Map<WebSecurity, WebSession> poolCache = new ConcurrentHashMap<WebSecurity, WebSession>();

	private WeixinmpSessionPool() {
	}

	public WebSession lookup(WebSecurity security) {
		WebSession ws = poolCache.get(security);
		return ws;
	}

	public WebSession updateAndRrefresh(final WebSecurity security) {
		WebSession ws = poolCache.get(security);
		if (ws == null) {
			if (security instanceof NamePassWebSecurity) {
				ws = new WeixinmpWebSession((NamePassWebSecurity) security) {
					@Override
					public void destroy() {
						super.destroy();
						poolCache.remove(security);//remove entry from this pool
					}
				};
			} else if (security instanceof SessionIdWebSecurity) {
				throw new UnSupportedSecurityType("This type is not supported for update and refresh WebSession.");
			}
		}
		ws.loginIfNecessary();
		return ws;
	}

	private final static class WebSessionPoolHolder {
		private final static WeixinmpSessionPool instance = new WeixinmpSessionPool();
	}

	public final static WebSessionPool getInstance() {
		return WebSessionPoolHolder.instance;
	}

}
