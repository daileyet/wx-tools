package com.openthinks.wx.hack.http.base.support.htmlunit;

public class Base_Resp {
	private int ret;
	private String err_msg;

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public String getErr_msg() {
		return err_msg;
	}

	public void setErr_msg(String err_msg) {
		this.err_msg = err_msg;
	}

	@Override
	public String toString() {
		return "Base_Resp [ret=" + ret + ", err_msg=" + err_msg + "]";
	}

}
