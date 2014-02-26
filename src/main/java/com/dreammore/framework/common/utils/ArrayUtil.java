package com.dreammore.framework.common.utils;

/**
 * 数组工具类
 * @author huhai
 * @since 2012-10-12
 *
 */
public class ArrayUtil {

	/**
	 * 将数组用指定分隔符和并为一个字符串
	 * @param o
	 * @param flag
	 * @return
	 * @author huhai
	 * @since 2012-10-12
	 */
	public static String join( Object[] o , String flag ){
		StringBuffer str_buff = new StringBuffer();
	 
		for(int i=0 , len=o.length ; i<len ; i++){
			str_buff.append( String.valueOf( o[i] ) );
			if(i<len-1)str_buff.append( flag );
		}

		return str_buff.toString(); 
	}
}
