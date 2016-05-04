package com.dounine.clouddisk360.parse;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import com.dounine.clouddisk360.store.CookieStoreUT;
import junit.framework.TestCase;
import org.apache.http.client.CookieStore;

public class CookieStoreTest extends TestCase {

	public void testRead() {
		final LoginUserToken loginUserToken = new LoginUserToken("102535481@qq.com","");
		
		final CookieStoreUT cookieStoreUT = new CookieStoreUT();
		cookieStoreUT.setLoginUserToken(loginUserToken);
		final CookieStore cookieStore = cookieStoreUT.readCookieStoreForDisk();
		System.out.println(JSON.toJSONString(cookieStore));
	}

}
