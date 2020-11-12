package com.passwdmin.util;

public class StringType {
	public static boolean isLetterDigitOrChinese(String str) {
		//�ж��Ƿ�������,����,��ĸ�������
		String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
		//������Ҫ��ֱ���޸�������ʽ�ͺ�
		return str.matches(regex);
	}
	
	//����һ����JAVA�Դ��ĺ���
	public static boolean isNumeric(String str){
		//�ж��Ƿ�������
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
