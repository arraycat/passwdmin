package com.passwdmin.util;

public class IsNum {
	//����һ����JAVA�Դ��ĺ���
	public static boolean isNumeric(String str){
	   for (int i = str.length();--i>=0;){  
	       if (!Character.isDigit(str.charAt(i))){
	           return false;
	       }
	   }
	   return true;
	}
}