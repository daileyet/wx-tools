package com.openthinks.wx.hack.http.user;

import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.openthinks.wx.hack.http.WeixinmpHttpRequest;
import com.openthinks.wx.hack.http.base.support.OperationAdapter;
import com.openthinks.wx.hack.http.base.support.Operations;
import com.openthinks.wx.hack.http.base.support.RandomTool;
import com.openthinks.wx.hack.http.base.support.WebSecurity;
import com.openthinks.wx.hack.http.base.support.WebSession;
import com.openthinks.wx.hack.http.base.support.htmlunit.IdentityUnit;
import com.openthinks.wx.hack.http.base.support.htmlunit.WeixinmpWebSession;
import com.openthinks.wx.hack.http.base.support.htmlunit.json.FansInfo;
import com.openthinks.wx.hack.http.base.support.htmlunit.json.UserInfo;

/**
 * https://mp.weixin.qq.com/cgi-bin/getcontactinfo?t=ajax-getcontactinfo&lang=
 * zh_CN&fakeid=o4bgnv_GukezqSa0RSdvlD3RXbNM
 * 
 */
public class GetUserInfoRequest extends WeixinmpHttpRequest {
	public UserInfo execute(WebSecurity security, final String openId) {
		final WebSession webSession = super.getWebSession(security);
		webSession.loginIfNecessary();
		final IdentityUnit unit = webSession.lookup(WeixinmpWebSession.IDENTITY_UNIT_KEY);
		Operations ops = Operations.buid(new OperationAdapter<UserInfo>() {
			private FansInfo fansInfo;

			@Override
			public void execute() throws Exception {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new NameValuePair("action", "get_fans_info"));
				params.add(new NameValuePair("token", unit.getAccessToken()));
				params.add(new NameValuePair("f", "json"));
				params.add(new NameValuePair("lang", "zh_CN"));
				params.add(new NameValuePair("ajax", "1"));
				params.add(new NameValuePair("random", RandomTool.generate()));
				params.add(new NameValuePair("user_openid", openId));
				WebRequest wr = createCommonRequest(MP_WEIXIN_GET_FANS_INFO, params);
				Page page = webSession.fetchPage(wr);
				String fanInfoJSON = page.getWebResponse().getContentAsString();
				fansInfo = FansInfo.fromJson(fanInfoJSON);
			}

			public UserInfo getResult() {
				if (fansInfo != null && fansInfo.getFirstUserInfo() != null) {
					return fansInfo.getFirstUserInfo();
				}
				return null;
			}
		});

		webSession.exec(ops);
		return ops.getFirstResult();
	}
}
