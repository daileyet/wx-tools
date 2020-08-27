package com.openthinks.wx.hack.http.base.support.htmlunit.json;

import com.google.gson.Gson;

public class RespWithTokenJSON {
	private BaseResp BaseResp;
	private String redirect_url;

	public BaseResp getBaseResp() {
		return BaseResp;
	}

	public void setBaseResp(BaseResp BaseResp) {
		this.BaseResp = BaseResp;
	}

	public String getRedirect_url() {
		return redirect_url;
	}

	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}

	@Override
	public String toString() {
		return "RespWithTokenJSON [BaseResp=" + BaseResp + ", redirect_url=" + redirect_url + "]";
	}

	public String getToken() {
		int tokenBegin = -1, tokenEnd = -1;
		if (this.redirect_url != null && (tokenBegin = this.redirect_url.indexOf("token=")) > 0) {
			String tokenPart = this.redirect_url.substring(tokenBegin + 6);
			tokenEnd = tokenPart.indexOf("&");
			if (tokenEnd > 0) {
				tokenPart = tokenPart.substring(0, tokenEnd);
			}
			return tokenPart;
		}
		return null;
	}

	public static RespWithTokenJSON fromJson(String respJson) {
		return gson.fromJson(respJson, RespWithTokenJSON.class);
	}

	private static final transient Gson gson = new Gson();
}
