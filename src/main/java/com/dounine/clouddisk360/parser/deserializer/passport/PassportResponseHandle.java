package com.dounine.clouddisk360.parser.deserializer.passport;

import com.dounine.clouddisk360.parser.PassportParser;
import com.dounine.clouddisk360.parser.deserializer.BaseResponseHandle;
import com.dounine.clouddisk360.parser.deserializer.login.LoginConst;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import com.dounine.clouddisk360.store.BasePathCommon;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import java.io.*;

public class PassportResponseHandle extends BaseResponseHandle<Passport, PassportParser>
		implements ResponseHandler<Passport> {

	public PassportResponseHandle(final PassportParser parse) {
		super(parse);
	}

	@Override
	public Passport handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
		executeBefore(response);
		saveCookie();
		executeAfter(response);
		final Passport passport = new Passport();
		passport.setCaptchaUrl(BasePathCommon.BASE_PATH + parse.getLoginUserToken().getAccount()+"/captcha.jpg");
		return passport;
	}

	@Override
	public void saveCookie() {
		parse.getCookieStoreUT().writeCookieStoreToDisk(parse.getHttpClientContext().getCookieStore(), new String[] { LoginConst.QUCRYPTCODE_NAME });
	}

	@Override
	public void executeBefore(final HttpResponse response) {
		final HttpEntity entity = response.getEntity();
		InputStream is = null;
		try {
			is = entity.getContent();
		} catch (UnsupportedOperationException | IOException e2) {
			e2.printStackTrace();
		}
		try {
			final LoginUserToken loginUser = parse.getLoginUserToken();
			final File file = new File(BasePathCommon.BASE_PATH + loginUser.getAccount()+"/captcha.jpg");
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			FileUtils.copyInputStreamToFile(is,file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
