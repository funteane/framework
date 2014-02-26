package com.dreammore.framework.codegeneration.service;

public interface Generation {
	
	public StringBuffer generate(Class<?> clazz);
	
	public void write(Class<?> clazz) throws Exception;

}
