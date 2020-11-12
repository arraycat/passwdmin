package com.passwdmin.util;

import java.util.Random;

public class GetString {
	//生成指定length的随机字符串（A-Z，a-z，0-9）
	public static String getRandomString(int length) {
	    Random random = new Random();
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < length; i++) {
	        int number = random.nextInt(3);
	        long result = 0;
	        switch (number) {
	            case 0:
	                result = Math.round(Math.random() * 25 + 65);
	                sb.append(String.valueOf((char) result));
	                break;
	            case 1:
	                result = Math.round(Math.random() * 25 + 97);
	                sb.append(String.valueOf((char) result));
	                break;
	            case 2:
	                sb.append(String.valueOf(new Random().nextInt(10)));
	                break;
	        }
	    }
	    return sb.toString();
	}
}
