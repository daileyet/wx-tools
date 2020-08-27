package com.soecode.wxtools.util.http;

import java.io.IOException;

import org.apache.http.impl.client.CloseableHttpClient;

import com.soecode.wxtools.exception.WxErrorException;

public interface RequestExecutor<T, E> {

	T execute(CloseableHttpClient httpclient, String uri, E data)
			throws WxErrorException,  IOException;

}
