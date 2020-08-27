package com.openthinks.wx.hack.http;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.openthinks.wx.hack.http.base.support.WebSecurity;
import com.openthinks.wx.hack.http.base.support.WebSession;
import com.openthinks.wx.hack.http.base.support.WebSessionProvider;
import com.openthinks.wx.hack.http.base.support.htmlunit.WeixinmpSessionProvider;


public abstract class WeixinmpHttpRequest {
	public static final String LOGIN_PAGE = "https://mp.weixin.qq.com";
	public static final String MP_WEIXIN_BASE = "https://mp.weixin.qq.com/cgi-bin/";
	public static final String MP_WEIXIN_LOGIN = "https://mp.weixin.qq.com/cgi-bin/login";

	public static final String MP_WEIXIN_GET_FANS_INFO = "https://mp.weixin.qq.com/cgi-bin/user_tag";

	public static final String MP_WEIXIN_USER_MGMT = "https://mp.weixin.qq.com/cgi-bin/user_tag?action=get_all_data&lang=zh_CN&token={TOKEN}";

	public static final String COOKIE_SESSION_ID = "slave_sid";
	// private final static Logger LOGGER =
	// Logger.getLogger(WeixinmpHttpRequest.class);

	protected WebSessionProvider provider = null;

	protected WeixinmpHttpRequest() {
		provider = new WeixinmpSessionProvider();
	}

	public final WebSession getWebSession(WebSecurity security) {
		return provider.get(security);
	}

	protected WebRequest createCommonRequest(String requestUrl, List<NameValuePair> params)
			throws MalformedURLException {
		Map<String, String> addtionalHeaders = new HashMap<String, String>();
		addtionalHeaders.put("Accept", "application/json, text/javascript, */*; q=0.01");
		addtionalHeaders.put("Accept-Encoding", "gzip, deflate, br");
		addtionalHeaders.put("Accept-Language", "en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4");
		addtionalHeaders.put("Connection", "keep-alive");
		addtionalHeaders.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		addtionalHeaders.put("Host", "mp.weixin.qq.com");
		addtionalHeaders.put("Referer", "https://mp.weixin.qq.com");
		addtionalHeaders.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0");
		addtionalHeaders.put("X-Requested-With", "XMLHttpRequest");
		return createRequest(requestUrl, addtionalHeaders, params);
	}

	protected WebRequest createRequest(String requestUrl, Map<String, String> addtionalHeaders,
			List<NameValuePair> params) throws MalformedURLException {
		WebRequest wr = new WebRequest(new URL(requestUrl), HttpMethod.POST);
		wr.getAdditionalHeaders().putAll(addtionalHeaders);
		wr.setRequestParameters(params);
		return wr;
	}
}
