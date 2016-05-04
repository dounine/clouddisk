package com.dounine.clouddisk360.exception;

public class CloudDiskException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2783602265989962551L;
	
	public CloudDiskException(final String errMsg){
		super(errMsg);
	}

	public CloudDiskException(final Exception e){
		super(e);
	}
}
