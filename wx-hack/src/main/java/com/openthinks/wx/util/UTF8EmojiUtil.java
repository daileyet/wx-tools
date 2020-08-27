package com.openthinks.wx.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.text.StringEscapeUtils;


/**
 * 微信可以采用emoj表情 utf，它是4字节的utf比较麻烦，这里给出两个存储方案
 * @author Alex
 *
 */
public class UTF8EmojiUtil {
	private UTF8EmojiUtil(){}
	
	/**
	 * 方案1，直接往数据库存储utf码制形如\u7f00\u098f...
	 * @param src 原始字符串
	 * @return
	 */
	public static String escapeJava(String src){
		try {
			return StringEscapeUtils.escapeJava(src);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return src;
	}
	/**
	 * utf 码值串
	 * @param src
	 * @return
	 */
	public static String unescapeJava(String src){
		try {
			return StringEscapeUtils.unescapeJava(src);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return src;
	}
	
	/**
	 * 参考 http://apps.timwhitlock.info/emoji/tables/unicode#block-6c-other-additional-symbols
	 *  表情
	 * use 
	 */
	public static String emojo(String src){
		StringBuilder builder=new StringBuilder();
		try {
			byte bytes[]=src.getBytes("utf8");
			int i=0;
			for(;i<bytes.length;i++){
				if((bytes[i]&0x000000F0)==0x000000F0){//四字节utf
					builder.append("<span class=\"emoji emoji"+get4BytesUtf8(bytes, i)+"\"></span>");
				}
				else if((bytes[i]&0x000000E0)==0x000000E0){//3字节utf
					builder.append(new String(bytes, i, 3, "utf8"));
				}
				else if((bytes[i]&0x00000090)==0x00000090){//2字节utf
					builder.append(new String(bytes, i, 2, "utf8"));
				}
				else {
					builder.append(new String(bytes, i, 1, "utf8"));
				}
			}
			return builder.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return src;
	}

	private static int get4BytesUtf8(byte[] bytes, int index) {
		int val=0x00000000;
		
		val=val|(0x0F&bytes[index]);//NO1 byte
		
		for(int i=0;i<3;i++){
			val<<=6;//后面三个数字都是10打头的6bits
			val = val|(0x3F&bytes[index+i+1]);
		}
		
		return val;
	}
}
