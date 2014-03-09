package com.dreammore.framework.common.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * JSON工具类
 * @since 2012-9-25
 *
 */
public class JsonUtil {

	private static Logger logger = Logger.getLogger(JsonUtil.class); 
	private static final ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * 将json字符串转为对象
	 * @param jsonStr json字符串
	 * @param classz 对象类型
	 * @return
	 * @since 2012-9-25
	 */
	public static  <T> T toObject(String jsonStr, Class<T> classz){
		T result = null;
		try {
			result = (T)mapper.readValue(jsonStr, classz);
        }
        catch (Exception e) {
        	logger.info("convert json to object failure", e);
        }
        return result;
	}
	
	/**
	 * 将字符串转换为对象
	 * @param <T>
	 * @param jsonStr jsonStr json字符串
	 * @param type 类型
	 * @return
	 * @since 2012-9-25
	 */
	@SuppressWarnings("unchecked")
    public static <T> T toObject(String jsonStr, TypeReference<T> type){
		T result = null;
		try {
			result = (T)mapper.readValue(jsonStr, type);
        }
        catch (Exception e) {
        	logger.info("convert json to object failure", e);
        }
        return result;
	}
	
	/**
	 * 将对象转为json字符串
	 * @param obj 对象
	 * @return
	 * @since 2012-9-25
	 */
	public static String toJson(Object obj){
		String result = null;
		try {
			result = mapper.writeValueAsString(obj);
        }
		 catch (Exception e) {
	        	logger.info("convert object to json failure", e);
	        }
		return result;
	}
	
	/**
	 * 将对象转为json格式写入HttpServletResponse
	 * @param response
	 * @param obj
	 * @throws IOException
	 * @since 2012-9-26
	 */
	public static void writeJson(HttpServletResponse response, Object obj) throws IOException{
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		writer.write(toJson(obj));
		writer.close();
	}
}
