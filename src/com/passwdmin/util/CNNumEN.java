package com.passwdmin.util;

public class CNNumEN {
	
	public static boolean isLetterDigitOrChinese(String str) {
		String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
		//其他需要，直接修改正则表达式就好
		return str.matches(regex);
	}
	public static void main(String[] args) {
		String a="1";
		String b="Ddd";
		String c="黄褐色";
		String d=a+b;
		String e=a+c;
		String f=b+c;
		String g=a+b+c;
		
		System.out.println(isLetterDigitOrChinese(a));
		System.out.println(isLetterDigitOrChinese(b));
		System.out.println(isLetterDigitOrChinese(c));
		System.out.println(isLetterDigitOrChinese(d));
		System.out.println(isLetterDigitOrChinese(e));
		System.out.println(isLetterDigitOrChinese(f));
		System.out.println(isLetterDigitOrChinese(g));

	}
}
