package com.dounine.clouddisk360.util;

public final class TimeUtil {
	
    private TimeUtil(){}
    
	public static String getTimeLenth(final int length){
		final String time = String.valueOf(System.currentTimeMillis());
		if(length>time.length()){
			return time;
		}
		return time.substring(0,length);
	}
}
