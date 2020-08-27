package com.openthinks.wx.hack.http.base.support;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomTool {
	private RandomTool(){}
	public static String generate() {
		return "0."+RandomStringUtils.random(16, "0123456789".toCharArray());
	}
	
	public static void main(String[] args) {
		System.out.println(RandomTool.generate());
	}

}
