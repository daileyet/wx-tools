package com.openthinks.wx.hack.http.base.support.htmlunit;

import static com.openthinks.wx.hack.http.WeixinmpHttpRequest.COOKIE_SESSION_ID;
import static com.openthinks.wx.hack.http.WeixinmpHttpRequest.LOGIN_PAGE;
import static com.openthinks.wx.hack.http.WeixinmpHttpRequest.MP_WEIXIN_BASE;
import static com.openthinks.wx.hack.http.WeixinmpHttpRequest.MP_WEIXIN_LOGIN;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.openthinks.wx.hack.http.base.support.NamePassWebSecurity;
import com.openthinks.wx.hack.http.base.support.Operations;
import com.openthinks.wx.hack.http.base.support.SessionIdWebSecurity;
import com.openthinks.wx.hack.http.base.support.WebSecuritys;
import com.openthinks.wx.hack.http.base.support.WebSession;
import com.openthinks.wx.hack.http.base.support.htmlunit.json.RespWithTokenJSON;

public class WeixinmpWebSession implements WebSession {
	private WebClient webClient;
	private HtmlPage currentPage;
	private NamePassWebSecurity securityNP;
	private SessionIdWebSecurity securitySID;
	private final Map<String, Object> cache = new ConcurrentHashMap<String, Object>();

	private final static Logger LOGGER = LoggerFactory.getLogger(WeixinmpWebSession.class);

	public static final String IDENTITY_UNIT_KEY = "WS_IDENTITY_KEY";

	public WeixinmpWebSession(NamePassWebSecurity securityNP) {
		super();
		this.securityNP = securityNP;
		initial();
	}

	private void initial() {
		this.webClient = new WebClient(BrowserVersion.FIREFOX_60);
		//this.webClient.getOptions().setProxyConfig(new ProxyConfig("cn-proxy.jp.oracle.com", 80));
		this.webClient.getCookieManager().setCookiesEnabled(true);//must enabled cookies
		this.webClient.getOptions().setJavaScriptEnabled(false);
		this.webClient.getOptions().setCssEnabled(false);
		this.webClient.getOptions().setThrowExceptionOnScriptError(false);
		this.webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		this.webClient.getOptions().setTimeout(35000);
	}

	public void loginIfNecessary() {
		if (isSessionOut()) {
			try {
				RespWithTokenJSON respWithTokenJSON = loginBySpecialPost();
				String token = null;
				if (respWithTokenJSON == null || (token = respWithTokenJSON.getToken()) == null) {
					throw new LoginFailedException("Failed to login " + MP_WEIXIN_LOGIN
							+ "; No expected json with token responsed.");
				}
				HtmlPage homePage = this.webClient.getPage(MP_WEIXIN_BASE + respWithTokenJSON.getRedirect_url());
				setCurrentPage(homePage);
				updateIdentityUnit(token);
			} catch (LoginFailedException e) {
				throw e;
			} catch (Exception ex) {
				LOGGER.error("Request Login Page Error.", ex);
			}
		}
	}

	private void updateIdentityUnit(String token) {
		IdentityUnit unit = new IdentityUnit(this.securityNP.getName(), token);
		Set<Cookie> cookies = this.webClient.getCookieManager().getCookies();
		if (cookies != null && !cookies.isEmpty()) {
			for (Cookie cookie : cookies) {
				if (COOKIE_SESSION_ID.equals(cookie.getName())) {
					unit.setSessionId(cookie.getValue());
					this.securitySID = WebSecuritys.simpleSID(cookie.getValue());
					break;
				}
			}
		}
		cache.put(IDENTITY_UNIT_KEY, unit);
	}

	protected RespWithTokenJSON loginBySpecialPost() throws FailingHttpStatusCodeException, IOException {
		WebRequest wr = new WebRequest(new URL(MP_WEIXIN_LOGIN), HttpMethod.POST);
		wr.getAdditionalHeaders().put("Accept", "*/*");
		wr.getAdditionalHeaders().put("Accept-Encoding", "gzip, deflate, br");
		wr.getAdditionalHeaders().put("Accept-Language", "en-US,en;q=0.5");
		wr.getAdditionalHeaders().put("Connection", "keep-alive");
		wr.getAdditionalHeaders().put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		wr.getAdditionalHeaders().put("Host", "mp.weixin.qq.com");
		wr.getAdditionalHeaders().put("Referer", "https://mp.weixin.qq.com/");
		wr.getAdditionalHeaders().put("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0");
		wr.getAdditionalHeaders().put("X-Requested-With", "XMLHttpRequest");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new NameValuePair("f", "json"));
		params.add(new NameValuePair("imgcode", ""));
		//here password should be encrypt by MD5 and to lower case
		params.add(new NameValuePair("pwd", this.securityNP.getPass()));
		params.add(new NameValuePair("username", this.securityNP.getName()));
		wr.setRequestParameters(params);
		Page page = this.webClient.getPage(wr);
		String respJson = page.getWebResponse().getContentAsString();
		return RespWithTokenJSON.fromJson(respJson);
	}

	void setCurrentPage(final HtmlPage currentPage) {
		this.currentPage = currentPage;
	}

	public WebClient getInstance() {
		return webClient;
	}

	public void logoutIfNecessary() {
		if (this.currentPage != null) {
			this.currentPage.cleanUp();
		}
	}

	public void destroy() {
		logoutIfNecessary();
		this.webClient.close();
	}

	public void exec(Operations operations) {
		if (operations != null)
			operations.run();
	}

	public boolean isSessionOut() {
		try {
			final HtmlPage page = webClient.getPage(LOGIN_PAGE);
			String fullUrl = page.getUrl().toString();
			if (fullUrl.indexOf("token=") > 0) {
				return false;
			}
		} catch (Exception e) {
			return true;
		}
		return true;
	}

	public NamePassWebSecurity getSecurityNP() {
		return securityNP;
	}

	public SessionIdWebSecurity getSecuritySID() {
		return securitySID;
	}

	public void setSecuritySID(SessionIdWebSecurity securitySID) {
		this.securitySID = securitySID;
	}

	public HtmlPage getCurrentPage() {
		return currentPage;
	}

	@SuppressWarnings("unchecked")
	public <T> T lookup(String property) {
		return (T) cache.get(property);
	}

	public Page fetchPage(WebRequest request) throws FailingHttpStatusCodeException, IOException {
		Page page = this.webClient.getPage(request);
		if (page.isHtmlPage())
			this.setCurrentPage((HtmlPage) page);
		return page;
	}

}
