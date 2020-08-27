package com.soecode.wxtools.bean.outxmlbuilder;

import com.soecode.wxtools.bean.WxXmlOutMessage;

public abstract class BaseBuilder<ValueType> {

	protected String toUserName;

	protected String fromUserName;

	public BaseBuilder<ValueType> toUser(String touser) {
		this.toUserName = touser;
		return this;
	}

	public BaseBuilder<ValueType> fromUser(String fromusername) {
		this.fromUserName = fromusername;
		return this;
	}

	public abstract ValueType build();

	public void setCommon(WxXmlOutMessage m) {
		m.setToUserName(this.toUserName);
		m.setFromUserName(this.fromUserName);
		m.setCreateTime(System.currentTimeMillis() / 1000l);
	}

}
