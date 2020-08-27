/**
  * Copyright 2016 aTool.org 
  */
package com.openthinks.wx.hack.http.base.support.htmlunit.json;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Auto-generated: 2016-03-11 21:33:54
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class FansInfo {

	@SerializedName("base_resp")
	private BaseResp baseResp;
	@SerializedName("group_info")
	private GroupsList groupsList;
	@SerializedName("user_list")
	private UsersList usersList;

	public BaseResp getBaseResp() {
		return baseResp;
	}

	public void setBaseResp(BaseResp baseResp) {
		this.baseResp = baseResp;
	}

	public GroupsList getGroupsList() {
		return groupsList;
	}

	public void setGroupsList(GroupsList groupsList) {
		this.groupsList = groupsList;
	}

	public UsersList getUsersList() {
		return usersList;
	}

	public void setUsersList(UsersList usersList) {
		this.usersList = usersList;
	}

	public UserInfo getFirstUserInfo() {
		if (this.getUsersList() != null && this.getUsersList().getUserInfoList() != null
				&& this.getUsersList().getUserInfoList().size() > 0) {
			return this.getUsersList().getUserInfoList().get(0);
		}
		return null;
	}

	public static FansInfo fromJson(String respJson) {
		return gson.fromJson(respJson, FansInfo.class);
	}

	private static final transient Gson gson = new Gson();

}