package com.passwdmin.util;

public class IsNum {
	//方法一：用JAVA自带的函数
	public static boolean isNumeric(String str){
	   for (int i = str.length();--i>=0;){  
	       if (!Character.isDigit(str.charAt(i))){
	           return false;
	       }
	   }
	   return true;
	}
}
