/**
  * Copyright 2016 aTool.org 
  */
package com.openthinks.wx.hack.http.base.support.htmlunit.json;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Auto-generated: 2016-03-11 21:33:54
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class UsersList {

	@SerializedName("user_info_list")
	private List<UserInfo> userList;

	public void setUserInfoList(List<UserInfo> userInfoList) {
		this.userList = userInfoList;
	}

	public List<UserInfo> getUserInfoList() {
		return userList;
	}


}