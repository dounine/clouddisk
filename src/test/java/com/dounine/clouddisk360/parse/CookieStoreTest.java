package com.dounine.clouddisk360.parse;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import com.dounine.clouddisk360.store.CookieStoreUT;
import junit.framework.TestCase;
import org.apache.http.client.CookieStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookieStoreTest extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(CookieStoreTest.class);

	public void testRead() {
		LoginUserToken loginUserToken = new LoginUserToken("102535481@qq.com","");
		
		CookieStoreUT cookieStoreUT = new CookieStoreUT();
		cookieStoreUT.setLoginUserToken(loginUserToken);
		CookieStore cookieStore = cookieStoreUT.readCookieStoreForDisk();
		System.out.println(JSON.toJSONString(cookieStore));
	}

}
