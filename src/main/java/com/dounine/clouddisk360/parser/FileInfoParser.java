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

	public FileInfoParser(final LoginUserToken loginUser) {
		super(loginUser);
	}

	@Override
	public FileInfo parse(final FileInfoParameter parameter) {
		if(hasException()){
			return execClouddiskException();
		}
		final FileListParser fileListParser = new FileListParser(loginUserToken);
		if(StringUtils.isBlank(parameter.getFilePath())){
			throw new CloudDiskException("要查找的文件路径不能为空.");
		}else if(parameter.getFilePath().charAt(parameter.getFilePath().length()-1)=='/'){
			throw new CloudDiskException("此方法不能查找文件夹相关信息.");
		}
		parameter.getFileListParameter()
				.setPath(parameter.getFilePath().substring(0, parameter.getFilePath().lastIndexOf("/") + 1));
		final FileList fileList = fileListParser.parse(parameter.getFileListParameter());
		final FileInfo fileInfo = new FileInfo();
		fileInfo.setErrmsg(fileList.getErrmsg());
		fileInfo.setErrno(fileList.getErrno());
		if (fileList.getData().size() > 0) {
			final List<FileListData> fileInfoRemoveRepeat = new ArrayList<>(0);//去空内容
			fileInfoRemoveRepeat.addAll(fileList.getData().stream().filter(fileListData -> StringUtils.isNotBlank(fileListData.getOriName())).collect(Collectors.toList()));
			final List<FileListData> fileIfs = fileInfoRemoveRepeat.stream()
					.filter(f -> f.getPath().equals(parameter.getFilePath())).collect(Collectors.toList());
			if (null != fileIfs && fileIfs.size() == 1) {
				final FileListData fileListData = fileIfs.get(0);
				fileInfo.setFileListData(fileListData);
				return fileInfo;
			}
		}
		return fileInfo;
	}

}
