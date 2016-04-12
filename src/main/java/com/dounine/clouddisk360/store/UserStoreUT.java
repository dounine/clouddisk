package com.dounine.clouddisk360.store;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONWriter;
import com.dounine.clouddisk360.parser.deserializer.login.Login;

public class UserStoreUT {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserStoreUT.class);

	public void writeStoreToDisk(String diskPath,Login login){
		File cookiesFile = new File(diskPath + "userinfo.txt");
		try {
			if (!cookiesFile.exists()) {
				cookiesFile.getParentFile().mkdirs();
			}
			JSONWriter writer = new JSONWriter(new FileWriter(cookiesFile));
			writer.startObject();
			writer.writeObject(login);
			writer.endObject();
			writer.flush();
			writer.close();
			LOGGER.info(String.format("360云盘用户登录信息保存路径 [ %s ] ", cookiesFile.getAbsolutePath()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
