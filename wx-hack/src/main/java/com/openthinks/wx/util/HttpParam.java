package com.openthinks.wx.util;

import java.util.HashMap;
import java.util.Map;

public class HttpParam extends HashMap<String, String[]>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getParameter(String key) {
		String[] values=this.get(key);
		return values==null?null:(values.length==0?null:values[0]);
	}
	public String[] getParameterValues(String key){
		return this.get(key);
	}
	public String toString() {
		StringBuilder b=new StringBuilder();
		for(Map.Entry<String,String[]> e:entrySet()){
			b.append(e.getKey()).append("=[");
			if(e.getValue()!=null){
				for(String s: e.getValue())
					b.append(s).append(",");
				
			}
		}
		return b.toString();
	}
}
