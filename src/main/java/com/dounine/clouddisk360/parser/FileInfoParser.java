package com.dounine.clouddisk360.parser;

import com.dounine.clouddisk360.annotation.Parse;
import com.dounine.clouddisk360.exception.CloudDiskException;
import com.dounine.clouddisk360.parser.deserializer.file.info.*;
import com.dounine.clouddisk360.parser.deserializer.file.list.FileList;
import com.dounine.clouddisk360.parser.deserializer.file.list.FileListData;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpPost;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Parse("文件信息")
public class FileInfoParser extends
		BaseParser<HttpPost, FileInfo, FileInfoConst, FileInfoParameter, FileInfoRequestInterceptor, FileInfoResponseHandle, FileInfoParser> {

	public FileInfoParser() {
		super();
	}

	public FileInfoParser(LoginUserToken loginUser) {
		super(loginUser);
	}

	@Override
	public FileInfo parse(FileInfoParameter parameter) {
		if(hasException()){
			return execClouddiskException();
		}
		FileListParser fileListParser = new FileListParser(loginUserToken);
		if(StringUtils.isBlank(parameter.getFilePath())){
			throw new CloudDiskException("要查找的文件路径不能为空.");
		}else if(parameter.getFilePath().charAt(parameter.getFilePath().length()-1)=='/'){
			throw new CloudDiskException("此方法不能查找文件夹相关信息.");
		}
		parameter.getFileListParameter()
				.setPath(parameter.getFilePath().substring(0, parameter.getFilePath().lastIndexOf("/") + 1));
		FileList fileList = fileListParser.parse(parameter.getFileListParameter());
		FileInfo fileInfo = new FileInfo();
		fileInfo.setErrmsg(fileList.getErrmsg());
		fileInfo.setErrno(fileList.getErrno());
		if (fileList.getData().size() > 0) {
			List<FileListData> fileInfosRemoveRepeat = new ArrayList<>(0);//去空内容
			for(FileListData fileListData : fileList.getData()){
				if(StringUtils.isNotBlank(fileListData.getOriName())){
					fileInfosRemoveRepeat.add(fileListData);
				}
			}
			List<FileListData> fileInfos = fileInfosRemoveRepeat.stream()
					.filter(f -> f.getPath().equals(parameter.getFilePath())).collect(Collectors.toList());
			if (null != fileInfos && fileInfos.size() == 1) {
				FileListData fileListData = fileInfos.get(0);
				fileInfo.setDate(fileListData.getDate());
				fileInfo.setFhash(fileListData.getFhash());
				fileInfo.setFileType(fileListData.getFileType());
				fileInfo.setHasThumb(fileListData.getHasThumb());
				fileInfo.setIsDir(fileListData.getIsDir());
				fileInfo.setIsFav(fileListData.getIsFav());
				fileInfo.setFmtime(fileListData.getFmtime());
				fileInfo.setMtime(fileListData.getMtime());
				fileInfo.setNid(fileListData.getNid());
				fileInfo.setOriName(fileListData.getOriName());
				fileInfo.setOriSize(fileListData.getOriSize());
				fileInfo.setPath(fileListData.getPath());
				fileInfo.setPic(fileListData.getPic());
				fileInfo.setPreview(fileListData.getPreview());
				fileInfo.setScid(fileListData.getScid());
				fileInfo.setSize(fileListData.getSize());
				fileInfo.setThumb(fileListData.getThumb());
				return fileInfo;
			}
		}
		return fileInfo;
	}

}
