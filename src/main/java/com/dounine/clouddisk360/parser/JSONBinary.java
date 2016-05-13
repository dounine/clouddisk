package com.dounine.clouddisk360.parser;

import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.JSONWriter;
import com.dounine.clouddisk360.exception.CloudDiskException;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import com.dounine.clouddisk360.store.BasePathCommon;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONBinary {

	private static final Logger LOGGER = LoggerFactory.getLogger(JSONBinary.class);

	private String binaryFilename;
	protected LoginUserToken loginUserToken;

	public String getBianaryFilenamePath(){
		return new StringBuffer(BasePathCommon.BASE_PATH).append(loginUserToken.getAccount()).append(File.separator).append(binaryFilename).toString();
	}

	public <T> void writeObjToDisk(final T obj) {
		if (StringUtils.isNotBlank(binaryFilename)) {
			final File file = new File(getBianaryFilenamePath());
			if (!file.exists()) {
				file.getParentFile().mkdirs();
			}
			JSONWriter jsonWriter = null;
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter(file);
				jsonWriter = new JSONWriter(fileWriter);
				jsonWriter.startObject();
				jsonWriter.writeObject(obj);
				jsonWriter.endObject();
				jsonWriter.flush();
				LOGGER.info("文件 { " + file.getName() + " } 写入位置" + file.getAbsolutePath());
			} catch (IOException e) {
				LOGGER.error("Error",e);
			} finally {
				try {
					if(null!=fileWriter){
						fileWriter.close();
					}
					if(null!=jsonWriter){
						jsonWriter.close();
					}
				} catch (IOException e) {
					LOGGER.error("Error",e);
				}
			}
		}
	}

	public <T> T readObjForDisk(final Class<T> clazz) {
		if (StringUtils.isNotBlank(binaryFilename)) {
			final File file = new File(getBianaryFilenamePath());
			if (!file.exists()) {
				LOGGER.error("读取的文件:[ " + file.getAbsolutePath() + " ]不存在,自动创建一个空文件");
				file.getParentFile().mkdirs();
				try {
					file.createNewFile();
				} catch (IOException e) {
					LOGGER.error("Error",e);
				}
			}else{
				try {
					if(FileUtils.readFileToString(file).length()>2){//判断文件里面是否包含有数据
						JSONReader jsonReader = null;
						FileReader fileReader = null;
						T t = null;
						try {
							fileReader = new FileReader(file);
							jsonReader = new JSONReader(fileReader);
							jsonReader.startObject();
							t = jsonReader.readObject(clazz);
							jsonReader.endObject();
						} catch (IOException e) {
							LOGGER.error("Error",e);
						} finally {
							if(null!=fileReader){
								fileReader.close();
							}
							if(null!=jsonReader){
								jsonReader.close();
							}
						}
						return t;
					}
				} catch (IOException e) {
					LOGGER.error("Error",e);
				}
			}
			return null;
		}
		throw new CloudDiskException("读取的对象文件位置与文件名不能为空");
	}

	public <T> void writeListToDisk(final List<T> lists) {
		if (StringUtils.isNotBlank(binaryFilename)) {
			final File file = new File(getBianaryFilenamePath());
			if (!file.exists()) {
				file.getParentFile().mkdirs();
			}
			JSONWriter jsonWriter = null;
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter(file);
				jsonWriter = new JSONWriter(fileWriter);
				jsonWriter.startArray();
				lists.forEach(jsonWriter::writeObject);
				jsonWriter.endArray();
				jsonWriter.flush();
			} catch (IOException e) {
				LOGGER.error("Error",e);
			} finally {
				try {
					if(null!=fileWriter){
						fileWriter.close();
					}
					if(null!=jsonWriter){
						jsonWriter.close();
					}
				} catch (IOException e) {
					LOGGER.error("Error",e);
				}
			}
		}
	}

	public <T> List<T> readListForDisk(final Class<T> clazz) {
		final List<T> lists = new ArrayList<>(0);
		if (StringUtils.isNotBlank(binaryFilename)) {
			final File file = new File(getBianaryFilenamePath());
			if (!file.exists()) {
				throw new CloudDiskException("读取的文件:[ " + file.getAbsolutePath() + " ]不存在");
			}
			JSONReader jsonReader = null;
			FileReader fileReader = null;
			try {
				fileReader = new FileReader(file);
				jsonReader = new JSONReader(fileReader);
				jsonReader.startArray();
				while (jsonReader.hasNext()) {
					lists.add(jsonReader.readObject(clazz));
				}
				jsonReader.endArray();
			} catch (IOException e) {
				LOGGER.error("Error",e);
			} finally {
				if(null!=fileReader){
					try {
						fileReader.close();
					} catch (IOException e) {
						LOGGER.error("Error",e);
					}
				}
				if(null!=jsonReader){
					jsonReader.close();
				}
			}
			return lists;
		}
		throw new CloudDiskException("读取的对象集合文件位置与文件名不能为空");
	}

	public void setBinaryFilename(final String binaryFilename) {
		this.binaryFilename = binaryFilename;
	}

	public LoginUserToken getLoginUserToken() {
		return loginUserToken;
	}

	public void setLoginUserToken(final LoginUserToken loginUserToken) {
		this.loginUserToken = loginUserToken;
	}

}
