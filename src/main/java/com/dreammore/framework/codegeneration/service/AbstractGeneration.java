package com.dreammore.framework.codegeneration.service;

import com.dreammore.framework.codegeneration.util.FileUtil;

public abstract class AbstractGeneration implements Generation{
	
	private String filePath;
	
	public AbstractGeneration(String filePath){
		this.filePath = filePath;
	}
	
	protected static String BR = "\n";
	
	protected String firstLetterLower(String field){
		return field.substring(0, 1).toLowerCase().concat(field.substring(1));
	}
	
	protected String firstLetterUpper(String field){
		return field.substring(0, 1).toUpperCase().concat(field.substring(1));
	}
	
	protected abstract String getOutputFileName(Class<?> clazz);
	
	protected String getBlanks(int number){
		String blank = "";
		for(int i = 0; i < number; i++){
			blank = blank.concat("   ");
		}
		
		return blank;
	}

	public String getFilePath() {
		return filePath;
	}
	
	@Override
	public void write(Class<?> clazz) throws Exception {
		FileUtil.writeToFile(generate(clazz), getOutputFileName(clazz));
	}
	

}
