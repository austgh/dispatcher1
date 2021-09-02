package com.dispatcher.util;


public class CaseUtils {

	/**
	 * 将字符串首字母大写
	 */
	public static String toUpperCaseFirstChar(String str) {
		if (!CommUtils.isEmptyStr(str)) {
			char[] cArr = str.toCharArray();
			if (cArr[0] >= 'a' && cArr[0] <= 'z') {
				cArr[0] -= 32;
			}
			return String.valueOf(cArr);
		}
		return str;
	}

	/**
	 * 将字符串首字母小写
	 */
	public static String toLowerCaseFirstChar(String str) {
		if (!CommUtils.isEmptyStr(str)) {
			char[] cArr = str.toCharArray();
			if (cArr[0] >= 'A' && cArr[0] <= 'Z') {
				cArr[0] += 32;
			}
			return String.valueOf(cArr);
		}
		return str;
	}
	
	/**
	 * 将null转成空串
	 * @param str
	 * @return
	 */
	public static String changeNullStr(String str){
		return str == null ? "" : str;
	}
	
	/**
	 * 判断字符串是否为空或空串
	 * @param str
	 * @return
	 */
	public static boolean isNullStr(String str){
		return str == null || "".equals(str);
	}
	
}
