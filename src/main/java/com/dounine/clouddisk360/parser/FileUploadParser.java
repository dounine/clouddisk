package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.Dependency;
import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.parser.deserializer.file.download.upaddress.FileUploadAddress;
import com.dounine.clouddisk360.parser.deserializer.file.upload.*;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import com.dounine.clouddisk360.pool.PoolingHttpClientConnection;
import org.apache.http.Consts;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

@Parse("文件上传")
@Dependency(depends = { FileUploadAddressParser.class })
public class FileUploadParser extends
		BaseParser<HttpPost, FileUpload, FileUploadConst, FileUploadParameter, FileUploadRequestInterceptor, FileUploadResponseHandle, FileUploadParser> {

	public FileUploadParser(LoginUserToken loginUser) {
		super(loginUser);
	}

	public FileUploadParser() {
		super();
	}

	public HttpPost initRequest(FileUploadParameter parameter) {
		FileUploadAddress fileUploadAddress = getDependResult(FileUploadAddress.class);
		HttpPost request = new HttpPost("http://" + fileUploadAddress.getData().getUp() + CONST.URI_PATH);
		MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
		multipartEntity.setCharset(Consts.UTF_8);
		multipartEntity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		multipartEntity.addPart(CONST.QID_NAME, new StringBody(getLoginInfo().getQid(), ContentType.DEFAULT_BINARY));
		multipartEntity.addPart("ofmt", new StringBody("json", ContentType.DEFAULT_BINARY));
		multipartEntity.addPart("method", new StringBody("Upload.web", ContentType.DEFAULT_BINARY));
		multipartEntity.addPart("token", new StringBody(readCookieStoreValue("token"), ContentType.DEFAULT_BINARY));
		multipartEntity.addPart("v", new StringBody("1.0.1", ContentType.DEFAULT_BINARY));
		multipartEntity.addPart("tk", new StringBody(fileUploadAddress.getData().getTk(), ContentType.DEFAULT_BINARY));
		multipartEntity.addPart("Upload", new StringBody("Submit Query", ContentType.DEFAULT_BINARY));
		multipartEntity.addPart("devtype", new StringBody("web", ContentType.DEFAULT_BINARY));
		multipartEntity.addPart("pid", new StringBody("ajax", ContentType.DEFAULT_BINARY));
		multipartEntity.addPart("Filename",
				new StringBody(parameter.getUploadFile().getName(), ContentType.APPLICATION_JSON));
		multipartEntity.addPart("path", new StringBody(parameter.getPath(), ContentType.APPLICATION_JSON));// 解决中文不识别问题
		multipartEntity.addBinaryBody("file", parameter.getUploadFile());
		request.setEntity(multipartEntity.build());
		return request;
	}

	@Override
	public FileUpload execute(HttpPost request) {
		if(hasException()){
			return execClouddiskException();
		}
		httpClient = HttpClients.custom().setConnectionManager(PoolingHttpClientConnection.getInstalce()).build();
		try {
			return httpClient.execute(request, responseHandler, this.httpClientContext);
		} catch (IOException e) {
			executeException(e,request);
		}
		return null;
	}

}
