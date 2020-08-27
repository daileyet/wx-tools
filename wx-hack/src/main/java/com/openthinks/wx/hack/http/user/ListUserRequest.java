package com.openthinks.wx.hack.http.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
import com.openthinks.wx.hack.http.base.support.htmlunit.json.UserInfo;

public class ListUserRequest extends WeixinmpHttpRequest {

	private final class ListUserRequestInner extends WeixinmpHttpRequest {
		public List<UserInfo> execute(WebSecurity ws, final int limit, final int offset) {
			final WebSession webSession = super.getWebSession(ws);
			webSession.loginIfNecessary();
			final IdentityUnit unit = webSession.lookup(WeixinmpWebSession.IDENTITY_UNIT_KEY);
			Operations ops = Operations.buid(new OperationAdapter<List<UserInfo>>() {
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
					params.add(new NameValuePair("limit", String.valueOf(limit)));
					params.add(new NameValuePair("offset", String.valueOf(offset)));
					params.add(new NameValuePair("random", RandomTool.generate()));
					params.add(new NameValuePair("token", unit.getAccessToken()));
					WebRequest wr = createCommonRequest(MP_WEIXIN_GET_FANS_INFO, params);
					wr.setHttpMethod(HttpMethod.GET);
					Page page = webSession.fetchPage(wr);
					String fanInfoJSON = page.getWebResponse().getContentAsString();
					fansInfo = FansInfo.fromJson(fanInfoJSON);
				}

				@Override
				public List<UserInfo> getResult() {
					if (fansInfo.getUsersList() == null)
						return Collections.emptyList();
					return fansInfo.getUsersList().getUserInfoList();
				}
			});
			webSession.exec(ops);
			return ops.getFirstResult();
		}
	}

	private int total = 0; // total user 
	private int limit = 20; //page size
	private int offset = 0;
	private int index = 0;// current page index 

	public Iterator<List<UserInfo>> execute(final WebSecurity ws) {
		initialRequest(ws);
		return new Iterator<List<UserInfo>>() {
			public boolean hasNext() {
				return getCurrentPageIndex() < getPageNumber();
			}

			public List<UserInfo> next() {
				ListUserRequestInner request = new ListUserRequestInner();
				List<UserInfo> users = request.execute(ws, ListUserRequest.this.limit, ListUserRequest.this.offset);
				index++;
				ListUserRequest.this.offset += ListUserRequest.this.limit;
				return users;
			}

			public void remove() {
				
			}
		};
	}
	
	private void initialRequest(WebSecurity ws) {
		ListUserGroupRequest groupRequest = new ListUserGroupRequest();
		List<GroupInfo> groups = groupRequest.execute(ws);
		int userTotal = 0;
		for (GroupInfo group : groups) {
			userTotal = userTotal + group.getGroupCnt();
		}
		this.total = userTotal;
	}

	/**
	 * get how many pages
	 * @return page number
	 */
	public int getPageNumber() {
		if (total == 0 || limit == 0)
			return 0;
		return total / limit + ((total % limit == 0) ? 0 : 1);
	}

	public int getCurrentPageIndex() {
		return index;
	}

	public int getTotal() {
		return total;
	}

	public int getLimit() {
		return limit;
	}
}
