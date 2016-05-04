package com.dounine.clouddisk360.store;

import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.JSONWriter;
import com.dounine.clouddisk360.parser.deserializer.BaseConst;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CookieStoreUT {

	private static final Logger LOGGER = LoggerFactory.getLogger(CookieStoreUT.class);

	public static final String COOKIE_STORE_PATH = File.separator+"cookieStore"+File.separator;

	private CookieStore cookieStore;
	private LoginUserToken loginUserToken;

	public void writeCookieStoreToDisk(final CookieStore cookieStore, final String[] filterCookies) {
		writeCookiesToDisk(cookieStore.getCookies(), filterCookies, true);
	}

	public void writeCookieStoreToDisk(final CookieStore cookieStore, final String[] filterCookies, boolean converCookieStore) {
		writeCookiesToDisk(cookieStore.getCookies(), filterCookies, converCookieStore);
	}

	public CookieStore readCookieStoreForDisk() {
		return readCookieStoreForDisk(null);
	}

	public String readCookieValueForDisk(final String filter) {
		final CookieStore cookieStore = readCookieStoreForDisk(new String[] { filter });
		final List<Cookie> cookies = cookieStore.getCookies();
		for (final Cookie cookie : cookies) {
			if (cookie.getName().equals(filter)) {
				return cookie.getValue();
			}
		}
		return null;
	}

	public CookieStore readCookieStoreForDisk(String[] filterCookies) {
		if (null == filterCookies){
			filterCookies = new String[0];
		}
		final List<BasicCookieStore3> cookies = new ArrayList<>(0);
		final String path = new StringBuilder(BasePathCommon.BASE_PATH).append(loginUserToken.getAccount()).append(COOKIE_STORE_PATH).append(BaseConst.COOKIES_PATH_NAME).toString();
		final File cookiesFile = new File(path);
		JSONReader reader = null;
		FileReader fileReader = null;
		try {
			if (!cookiesFile.exists()) {
				cookiesFile.getParentFile().mkdirs();
				cookiesFile.createNewFile();
				LOGGER.info(path + "文件不存在,主动创建一个空文件,并返回新创建的cookieStore");
				return new BasicCookieStore();
			}
			if (FileUtils.readFileToString(cookiesFile).length()<2) {
				return new BasicCookieStore();
			}
			fileReader = new FileReader(cookiesFile);
			reader = new JSONReader(fileReader);
			reader.startArray();
			while (reader.hasNext()) {
				cookies.add(reader.readObject(BasicCookieStore3.class));
			}
			reader.endArray();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null!=fileReader){
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null!=reader){
				reader.close();
			}
		}
		final List<Cookie> appacheCookies = new ArrayList<>(cookies.size());
		BasicClientCookie2 cookie2 = null;
		for (final BasicCookieStore3 cookieStore3 : cookies) {
			cookie2 = new BasicClientCookie2(cookieStore3.getName(), cookieStore3.getValue());
			cookie2.setCreationDate(cookieStore3.getCreationDate());
			cookie2.setDomain(cookieStore3.getDomain());
			cookie2.setExpiryDate(cookieStore3.getExpiryDate());
			cookie2.setPath(cookieStore3.getPath());
			cookie2.setSecure(cookieStore3.getSecure());
			cookie2.setVersion(cookieStore3.getVersion());
			appacheCookies.add(cookie2);
		}
		final List<Cookie> filterCookieLists = new ArrayList<>(appacheCookies.size());
		for (final Cookie cookie : appacheCookies) {
			boolean isFilter = false;
			if (filterCookies.length == 0) {
				isFilter = true;
			} else {
				for (final String cookieName : filterCookies) {
					if (cookie.getName().equals(cookieName)) {
						isFilter = true;
						break;
					}
				}
			}
			if (isFilter) {
				filterCookieLists.add(cookie);
			}
		}
		cookieStore = new CookieStoreImpl(filterCookieLists);
		return cookieStore;
	}

	private void writeCookiesToDisk(final List<Cookie> responseCookies, String[] filterCookies, final boolean converCookieStore) {
		if (null == filterCookies) {
			filterCookies = new String[0];// 手动初始化,防止异常
		}
		if (null != responseCookies && responseCookies.size() > 0) {
			List<Cookie> writeDiskCookies = new ArrayList<>(0);// 真正写入磁盘的cookies集合

			final List<Cookie> readDiskCookies = readCookieStoreForDisk().getCookies();// 读取磁盘上的cookies缓存

			if (converCookieStore) {
				for (final Cookie responseCookie : responseCookies) {
					if (filterCookies.length == 0) {
						writeDiskCookies.add(responseCookie);
					} else {
						for (final String cookieName : filterCookies) {
							if (responseCookie.getName().equals(cookieName)) {
								writeDiskCookies.add(responseCookie);
								break;
							}
						}
					}
				}
				for (final Cookie diskCookie : readDiskCookies) {
					if (false == writeDiskCookies.stream().anyMatch(c -> c.getName().equals(diskCookie.getName()))) {// 把不存在的cookie重新添加回去
						writeDiskCookies.add(diskCookie);
					}
				}
			} else {
				writeDiskCookies = new ArrayList<>(readDiskCookies);
				for (final Cookie responseCookie : responseCookies) {
					if (false == readDiskCookies.stream().anyMatch(c -> c.getName().equals(responseCookie.getName()))) {// 把新的cookie添加进去
						if (filterCookies.length == 0) {
							writeDiskCookies.add(responseCookie);
						} else {
							for (final String cookieName : filterCookies) {
								if (responseCookie.getName().equals(cookieName)) {
									writeDiskCookies.add(responseCookie);
									break;
								}
							}
						}
					}
				}
			}

			final File cookiesFile = new File(new StringBuffer(BasePathCommon.BASE_PATH).append(loginUserToken.getAccount()).append(COOKIE_STORE_PATH).append(BaseConst.COOKIES_PATH_NAME).toString());
			JSONWriter writer = null;
			FileWriter fileWriter = null;
			try {
				if (!cookiesFile.exists()) {
					cookiesFile.getParentFile().mkdirs();
				}
				fileWriter = new FileWriter(cookiesFile);
				writer = new JSONWriter(fileWriter);
				writer.startArray();
				writeDiskCookies.forEach(writer::writeObject);
				writer.endArray();
				writer.flush();
				cookieStore = new BasicCookieStore();
				cookieStore.getCookies().clear();
				cookieStore.getCookies().addAll(writeDiskCookies);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(null!=fileWriter){
					try {
						fileWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(null!=writer){
					try {
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void writeCookieStoreToDisk(final CookieStore cookieStore,final  boolean converCookieStore) {
		writeCookiesToDisk(cookieStore.getCookies(), null, converCookieStore);
	}

	public CookieStore getCookieStore() {
		return cookieStore;
	}

	public void setCookieStore(CookieStore cookieStore) {
		this.cookieStore = cookieStore;
	}

	public LoginUserToken getLoginUserToken() {
		return loginUserToken;
	}

	public void setLoginUserToken(LoginUserToken loginUserToken) {
		this.loginUserToken = loginUserToken;
	}

}
