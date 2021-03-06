package com.soecode.wxtools.util.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;

import com.soecode.wxtools.bean.result.WxError;
import com.soecode.wxtools.exception.WxErrorException;
import com.soecode.wxtools.util.StringUtils;
import com.soecode.wxtools.util.file.FileUtils;

public class QrCodeDownloadGetRequestExecutor implements RequestExecutor<File, Map<String,String>> {

	private File qrDirFile;

	public QrCodeDownloadGetRequestExecutor() {
		super();
	}

	public QrCodeDownloadGetRequestExecutor(File qrDirFile) {
		super();
		this.qrDirFile = qrDirFile;
	}

	@Override
	public File execute(CloseableHttpClient httpclient, String uri, Map<String,String> params)
			throws WxErrorException, IOException {
		if (params != null) {
			uri += '?';
			for(String key : params.keySet()){
				uri+=key+"="+params.get(key)+"&";
			}
			uri = uri.substring(0, uri.length()-1);
		}
		
		HttpGet httpGet = new HttpGet(uri);

		try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
			Header[] contentTypeHeader = response.getHeaders("Content-Type");
			if (contentTypeHeader != null && contentTypeHeader.length > 0) {
				if (ContentType.TEXT_PLAIN.getMimeType().equals(contentTypeHeader[0].getValue())) {
					String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
					throw new WxErrorException(WxError.fromJson(responseContent));
				}
			}
			InputStream inputStream = InputStreamResponseHandler.INSTANCE.handleResponse(response);
			String fileName = createFileName(response);
			if (StringUtils.isBlank(fileName)) {
				return null;
			}
			String[] name_ext = fileName.split("\\.");
			File localFile = FileUtils.createMaterialFile(inputStream, name_ext[0], name_ext[name_ext.length-1], qrDirFile);
			return localFile;
		}
	}

	protected String createFileName(CloseableHttpResponse response) {
		Header[] contentDispositionHeader = response.getHeaders("Cache-control");
		Pattern p = Pattern.compile(".*max-age=(.*)");
		Matcher m = p.matcher(contentDispositionHeader[0].getValue());
		m.matches();
		String maxage = m.group(m.groupCount());
		contentDispositionHeader = response.getHeaders("Content-Type");
		p = Pattern.compile(".*image/(.*)");
		m = p.matcher(contentDispositionHeader[0].getValue());
		m.matches();
		String type = m.group(m.groupCount());
		String fileName = "qr-maxage-" +maxage +"-tmp-"+ System.currentTimeMillis()+"."+type;
		return fileName;
	}

}
