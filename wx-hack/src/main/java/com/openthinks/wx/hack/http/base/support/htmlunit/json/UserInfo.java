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
public class UserInfo {

	@SerializedName("user_openid")
	private String userOpenid;
	@SerializedName("user_name")
	private String userName;
	@SerializedName("user_remark")
	private String userRemark;
	@SerializedName("user_group_id")
	private List<String> userGroupId;
	@SerializedName("user_create_time")
	private int userCreateTime;
	@SerializedName("user_signature")
	private String userSignature;
	@SerializedName("user_city")
	private String userCity;
	@SerializedName("user_province")
	private String userProvince;
	@SerializedName("user_country")
	private String userCountry;
	@SerializedName("user_head_img")
	private String userHeadImg;
	@SerializedName("user_gender")
	private int userGender;

	public void setUserOpenid(String userOpenid) {
		this.userOpenid = userOpenid;
	}

	public String getUserOpenid() {
		return userOpenid;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}

	public String getUserRemark() {
		return userRemark;
	}

	public void setUserGroupId(List<String> userGroupId) {
		this.userGroupId = userGroupId;
	}

	public List<String> getUserGroupId() {
		return userGroupId;
	}

	public void setUserCreateTime(int userCreateTime) {
		this.userCreateTime = userCreateTime;
	}

	public int getUserCreateTime() {
		return userCreateTime;
	}

	public void setUserSignature(String userSignature) {
		this.userSignature = userSignature;
	}

	public String getUserSignature() {
		return userSignature;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserProvince(String userProvince) {
		this.userProvince = userProvince;
	}

	public String getUserProvince() {
		return userProvince;
	}

	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}

	public String getUserCountry() {
		return userCountry;
	}

	public void setUserHeadImg(String userHeadImg) {
		this.userHeadImg = userHeadImg;
	}

	public String getUserHeadImg() {
		return userHeadImg;
	}

	public void setUserGender(int userGender) {
		this.userGender = userGender;
	}

	public int getUserGender() {
		return userGender;
	}

}