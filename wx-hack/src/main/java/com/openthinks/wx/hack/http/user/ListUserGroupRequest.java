package com.openthinks.wx.hack.http.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gargoylesoftware.htmlunit.HttpMethod;
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
import com.openthinks.wx.hack.http.base.support.htmlunit.json.GroupInfo;

public class ListUserGroupRequest extends WeixinmpHttpRequest {

	public List<GroupInfo> execute(WebSecurity ws) {
		final WebSession webSession = super.getWebSession(ws);
		webSession.loginIfNecessary();
		final IdentityUnit unit = webSession.lookup(WeixinmpWebSession.IDENTITY_UNIT_KEY);
		Operations ops = Operations.buid(new OperationAdapter<List<GroupInfo>>() {
			private FansInfo fansInfo;

			@Override
			public void execute() throws Exception {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new NameValuePair("action", "get_user_list"));
				params.add(new NameValuePair("ajax", "1"));
				params.add(new NameValuePair("backfoward", "1"));
				params.add(new NameValuePair("begin_create_time", "-1"));
				params.add(new NameValuePair("begin_openid", "-1"));
				params.add(new NameValuePair("f", "json"));
				params.add(new NameValuePair("groupid", "-2"));
				params.add(new NameValuePair("lang", "zh_CN"));
				params.add(new NameValuePair("limit", "20"));
				params.add(new NameValuePair("offset", "0"));
				params.add(new NameValuePair("random", RandomTool.generate()));
				params.add(new NameValuePair("token", unit.getAccessToken()));
				WebRequest wr = createCommonRequest(MP_WEIXIN_GET_FANS_INFO, params);
				wr.setHttpMethod(HttpMethod.GET);
				Page page = webSession.fetchPage(wr);
				String fanInfoJSON = page.getWebResponse().getContentAsString();
				fansInfo = FansInfo.fromJson(fanInfoJSON);
			}

			@Override
			public List<GroupInfo> getResult() {
				if (fansInfo.getGroupsList() == null)
					return Collections.emptyList();
				return fansInfo.getGroupsList().getGroupInfoList();
			}
		});

		webSession.exec(ops);
		return ops.getFirstResult();
	}
}
