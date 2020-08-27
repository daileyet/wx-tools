/**
  * Copyright 2016 aTool.org 
  */
package com.openthinks.wx.hack.http.base.support.htmlunit.json;

import com.google.gson.annotations.SerializedName;

/**
 * Auto-generated: 2016-03-11 21:33:54
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class GroupInfo {

	@SerializedName("group_name")
	private String groupName;
	@SerializedName("group_cnt")
	private int groupCnt;
	@SerializedName("group_create_time")
	private int groupCreateTime;
	@SerializedName("group_id")
	private int groupId;

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupCnt(int groupCnt) {
		this.groupCnt = groupCnt;
	}

	public int getGroupCnt() {
		return groupCnt;
	}

	public void setGroupCreateTime(int groupCreateTime) {
		this.groupCreateTime = groupCreateTime;
	}

	public int getGroupCreateTime() {
		return groupCreateTime;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getGroupId() {
		return groupId;
	}
}