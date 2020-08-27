package com.openthinks.wx.hack.http.base;

import com.openthinks.wx.hack.http.WeixinmpHttpRequest;
import com.openthinks.wx.hack.http.base.support.OperationAdapter;
import com.openthinks.wx.hack.http.base.support.Operations;
import com.openthinks.wx.hack.http.base.support.WebSecuritys;
import com.openthinks.wx.hack.http.base.support.WebSession;
import com.openthinks.wx.hack.http.base.support.htmlunit.IdentityUnit;
import com.openthinks.wx.hack.http.base.support.htmlunit.WeixinmpWebSession;
import com.openthinks.wx.model.AccessToken;

/**
 * http和saas保持一致的API封装风格。saas中是用appid+appsecert来换取token，每次使用token
 * 来调用saasapi。通过http来hack的api也类似，用一组用户米密码来换取一个服务器上的sessionid，
 * 后面每次都是用sessionid来执行业务逻辑。
 * 
 * 
 * 此类和@see
 * net.sf.weixinmp.saas.dialog.base.GetAccessTokenRequest类似，模拟用户登录，换取sessionid，
 * 把sessionid封装为一个@see
 * net.sf.weixinmp.model.AccessToken模型，其中session的超时时间只能更具weixin 平台来推测。
 * http和weixin提供的saas一样
 * 
 */
public class GetSessionRequest extends WeixinmpHttpRequest {

	public AccessToken execute(String username, String password) {
		final WebSession webSession = super.getWebSession(WebSecuritys.simpleNP(username, password));
		webSession.loginIfNecessary();

		Operations ops = Operations.buid(new OperationAdapter<AccessToken>() {
			@Override
			public AccessToken getResult() {
				IdentityUnit unit = webSession.lookup(WeixinmpWebSession.IDENTITY_UNIT_KEY);
				AccessToken token= new AccessToken(unit.getAccessToken(),null);
				return token;
			}
		});
		webSession.exec(ops);

		return ops.getFirstResult();
	}
}
