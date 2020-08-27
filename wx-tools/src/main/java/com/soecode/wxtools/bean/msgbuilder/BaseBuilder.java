package com.soecode.wxtools.bean.msgbuilder;

import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.bean.WxMessage;

public class BaseBuilder {
	protected String msgType;
	protected String agentId;
	protected String toUser;
	protected String toParty;
	protected String toTag;
	protected String safe;

	public BaseBuilder agentId(String agentId) {
		this.agentId = agentId;
		return this;
	}

	public BaseBuilder toUser(String toUser) {
		this.toUser = toUser;
		return this;
	}

	public BaseBuilder toParty(String toParty) {
		this.toParty = toParty;
		return this;
	}

	public BaseBuilder toTag(String toTag) {
		this.toTag = toTag;
		return this;
	}

	public BaseBuilder safe(String safe) {
		this.safe = safe;
		return this;
	}

	public WxMessage build() {
		WxMessage m = new WxMessage();
		m.setAgentId(this.agentId);
		m.setMsgType(this.msgType);
		m.setToUser(this.toUser);
		m.setToParty(this.toParty);
		m.setToTag(this.toTag);
		m.setSafe((this.safe == null || "".equals(this.safe)) ? WxConsts.CUSTOM_MSG_SAFE_NO : this.safe);
		return m;
	}

}
