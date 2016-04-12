package com.dounine.clouddisk360.parser.deserializer.file.move;

import java.util.ArrayList;
import java.util.List;

import com.dounine.clouddisk360.parser.deserializer.BaseParameter;

public class FileMoveParameter extends BaseParameter {

	private List<String> moveFiles = new ArrayList<>(0);
	/**
	 * 移动到新路径
	 */
	private String newPath;

	public List<String> getMoveFiles() {
		return moveFiles;
	}

	public void setMoveFiles(List<String> moveFiles) {
		this.moveFiles = moveFiles;
	}

	public String getNewPath() {
		return newPath;
	}

	public void setNewPath(String newPath) {
		this.newPath = newPath;
	}

}
