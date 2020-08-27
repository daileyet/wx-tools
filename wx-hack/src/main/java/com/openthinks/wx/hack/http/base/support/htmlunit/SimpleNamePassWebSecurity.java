package com.openthinks.wx.hack.http.base.support.htmlunit;

import com.openthinks.wx.hack.http.base.support.NamePassWebSecurity;

public class SimpleNamePassWebSecurity implements NamePassWebSecurity {
	private String name;
	private String password;

	private static final long serialVersionUID = 6144170889339651564L;

	public SimpleNamePassWebSecurity() {
		super();
	}

	public SimpleNamePassWebSecurity(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getPass() {
		return password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		SimpleNamePassWebSecurity other = (SimpleNamePassWebSecurity) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SimpleNamePassWebSecurity [name=" + name + "]";
	}

}
