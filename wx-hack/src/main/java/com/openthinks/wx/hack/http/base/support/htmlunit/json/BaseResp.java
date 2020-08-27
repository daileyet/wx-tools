/**
  * Copyright 2016 aTool.org 
  */
package com.openthinks.wx.hack.http.base.support.htmlunit.json;
import com.google.gson.annotations.SerializedName;
/**
 * Auto-generated: 2016-03-11 21:33:54
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class BaseResp {

    private int ret;
    @SerializedName("err_msg")
    private String errMsg;
    public void setRet(int ret) {
         this.ret = ret;
     }
     public int getRet() {
         return ret;
     }

    public void setErrMsg(String errMsg) {
         this.errMsg = errMsg;
     }
     public String getErrMsg() {
         return errMsg;
     }

}