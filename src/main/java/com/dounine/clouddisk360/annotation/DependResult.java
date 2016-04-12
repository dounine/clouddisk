package com.dounine.clouddisk360.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.dounine.clouddisk360.parser.deserializer.BaseDes;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface DependResult {

	public Class<? extends BaseDes> result();
	
	public boolean customInit();
	
}
