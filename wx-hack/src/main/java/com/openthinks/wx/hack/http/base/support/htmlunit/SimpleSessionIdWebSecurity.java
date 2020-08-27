package com.openthinks.wx.hack.http.base.support.htmlunit;

import com.openthinks.wx.hack.http.base.support.SessionIdWebSecurity;

public class SimpleSessionIdWebSecurity implements SessionIdWebSecurity {
	private static final long serialVersionUID = 2314386236664538886L;
	private String sessionId;

	public SimpleSessionIdWebSecurity() {
		super();
	}

	public SimpleSessionIdWebSecurity(String sessionId) {
		super();
		this.sessionId = sessionId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
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
		SimpleSessionIdWebSecurity other = (SimpleSessionIdWebSecurity) obj;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SimpleSessionIdWebSecurity [sessionId=" + sessionId + "]";
	}

}
