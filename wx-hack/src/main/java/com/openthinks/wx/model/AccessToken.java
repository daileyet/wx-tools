package com.openthinks.wx.model;

import java.util.Date;

public class AccessToken {
	private String token;
	private Date expiresIn;
	
	public AccessToken(String token, Date expiresIn) {
		super();
		this.token = token;
		this.expiresIn = expiresIn;
	}
	public String getToken() {
		return token;
	}
	public Date getExpiresIn() {
		return expiresIn;
	}
	@Override
	public String toString() {
		return "AccessToken [token=" + token + ", expiresIn=" + expiresIn
				+ ", " + super.toString() + "]";
	}
	
}
