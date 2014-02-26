package com.dreammore.framework.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Tools {
	
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT =  new SimpleDateFormat("yyyy-MM-dd");
	
	private static final SimpleDateFormat LONG_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	
	/**
	 * 列表转换为数组
	 * @param srcs
	 * @param dests 空的数组
	 */
	public static <T> void list2Array(List<T> srcs, T[] dests){
		for(int i = 0, length = srcs.size(); i < length; i++){
			dests[i] = srcs.get(i);
		}
	}
	
	/**
	 * 数组转换为列表
	 * @param srcs
	 * @param dests  空的列表
	 */
	public static <T> void array2List(T[] srcs, List<T> dests){
		for(T src : srcs){
			dests.add(src);
		}
	}
	
	/**
	 * 是否null 或者 “”
	 * @return
	 */
	public static boolean empty(String target){
		if (target == null || "".equals(target) || "null".equals(target) ) {
			return true;
		}
		
		return false;
		
	}
	
	public static <T>  boolean empty(T target){
		if (target == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 是否null 或者 empty
	 * @param target
	 * @return
	 */
	public static <T> boolean empty(List<T> target){
		if (target == null || target.isEmpty()) {
			return true;
		}
		return false;
	}
	
	
	public static <K, V> boolean empty(Map<K, V> target){
		if (target == null || target.isEmpty()) {
			return true;
		}
		return false;
	}
	
	
	public static <T> boolean empty(T[] target){
		if (target == null || target.length == 0) {
			return true;
		}
		return false;
	}
	
	
	public static String getDateString(Date date){
		if (date == null) {
			return null;
		}
		return SIMPLE_DATE_FORMAT.format(date);
	}
	
	public static Date getDate(String dateString){
		if (dateString == null) {
			return null;
		}
		try {
			return SIMPLE_DATE_FORMAT.parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getLongDateString(Date date){
		if (date == null) {
			return null;
		}
		return LONG_DATE_FORMAT.format(date);
	}
	
	public static Date getLongDate(String dateString){
		if (dateString == null) {
			return null;
		}
		try {
			return LONG_DATE_FORMAT.parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static <K,T>  Map<K, T> inverseMap(Map<T, K> sourceMap){
		Map<K, T> destMap = new HashMap<K, T>();
		Set<Entry<T, K>> tempSet = sourceMap.entrySet();
		for(Entry<T, K> entry : tempSet){
			destMap.put(entry.getValue(), entry.getKey());
		}
		return destMap;
	}
	
	public static String whitespaceFormFull2Half(String input){
		if (input == null) {
			return "";
		}
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			int d = c[i];
			if (d == 160) {
				c[i] = 0;
			}
		}

		return new String(c);
	}
	
	public static List<String> snatchDateString(String sourceString){
		List<String> targets = new ArrayList<String>();
		Pattern pattern = Pattern.compile("[0-9]{4}[年|\\-|/][0-9]{1,2}[月|\\-|/][0-9]{1,2}");
		Matcher matcher = pattern.matcher(sourceString);
		while (matcher.find()) {
			if (!"".equals(matcher.group())) {
				String date = matcher.group();
				date = date.replaceAll("年", "-");
				date = date.replaceAll("月", "-");
				date = date.replaceAll("/", "-");
				targets.add(date);
			}
		}
		
		return targets;
	}
	
	
	public static <T> List<T> getEmptyList(){
		return new LinkedList<T>();
	}
	
	public static <T> List<T> add2List(T t, List<T> list){
		list.add(t);
		return list;
	}
	
	public static String list2String(List<String> list){
		StringBuffer sb = new StringBuffer();
		sb.append("(");
		for(String var : list){
			sb.append("\"").append(var).append("\", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append(")");
		return sb.toString();
	}
	
	public static String list2Str(List<String> list){
		StringBuffer sb = new StringBuffer();
		for(String var : list){
			sb.append(",").append(var);
		}
		return sb.toString().substring(1);
	}
	
//	public static void main(String[] args) {
//		String str = "本页面最后修订于2012年2月14日 (星期二) 2015-13/35日 11:29。2012-135/14<br /></li>";
//		System.out.println(snatchDateString(str));
//	}
	
	public static void main(String[] args) {
//		System.out.println(Tools.getLongDateString(Calendar.getInstance().getTime()));
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		System.out.println(list2String(list));
 		
	}
	

}
