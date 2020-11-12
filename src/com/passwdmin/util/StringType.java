package com.passwdmin.util;

public class StringType {
	public static boolean isLetterDigitOrChinese(String str) {
		//判断是否是中文,数字,字母及其组合
		String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
		//其他需要，直接修改正则表达式就好
		return str.matches(regex);
	}
	
	//方法一：用JAVA自带的函数
	public static boolean isNumeric(String str){
		//判断是否是数字
		for (int i = str.length();--i>=0;){  
	        if (!Character.isDigit(str.charAt(i))){
	            return false;
	        }
	    }
	    return true;
	}
	
	public static Boolean isEmail(String str) {
		Boolean isEmail = false;
	    String expr = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})$";
	    if (str.matches(expr)) {
	       isEmail = true;
	    }
	    else {
	    	isEmail = false;
	    }
	    return isEmail;
	}
}
