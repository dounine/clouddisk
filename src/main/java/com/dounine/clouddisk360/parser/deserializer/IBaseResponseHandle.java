package com.dounine.clouddisk360.parser.deserializer;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;

public interface IBaseResponseHandle<T> {

	T handleResponse(HttpResponse response) throws ClientProtocolException, IOException;
	
	default void executeBefore(HttpResponse response) {
		
	}
	default void executeAfter(HttpResponse response) {
		
	}
	
	default String disassemblyResult(String result){
		return result;
	}

	T desializer(String result);
	
	default void saveCookie(){
		
	}
	
}
