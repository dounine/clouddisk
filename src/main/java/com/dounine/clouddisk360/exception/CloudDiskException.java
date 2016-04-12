package com.dounine.clouddisk360.exception;

public class CloudDiskException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2783602265989962551L;
	
	public CloudDiskException(String errMsg){
		super(errMsg);
	}

	public CloudDiskException(Exception e){
		super(e);
	}
}
