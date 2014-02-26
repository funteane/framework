package com.dreammore.framework.codegeneration.util;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {
	
	public static void writeToFile(StringBuffer sb, String fileName){
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
			fileOutputStream.write(sb.toString().getBytes());
			fileOutputStream.flush();
			fileOutputStream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
