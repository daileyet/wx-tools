package com.openthinks.wx.hack.http.base.support.htmlunit;

import java.io.Serializable;

public class IdentityUnit implements Serializable {
	private static final long serialVersionUID = 8248832182333288794L;
	private String userName;
	private String accessToken;
	private String sessionId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public IdentityUnit() {
		super();
	}

	public IdentityUnit(String userName, String accessToken) {
		super();
		this.userName = userName;
		this.accessToken = accessToken;
	}

	public IdentityUnit(String userName, String accessToken, String sessionId) {
		super();
		this.userName = userName;
		this.accessToken = accessToken;
		this.sessionId = sessionId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdentityUnit other = (IdentityUnit) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IdentityUnit [userName=" + userName + ", accessToken=" + accessToken + ", sessionId=" + sessionId + "]";
	}

}
