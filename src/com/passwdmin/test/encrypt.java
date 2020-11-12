package com.passwdmin.test;

import com.passwdmin.util.*;
import com.passwdmin.db.*;

public class encrypt {
	public static void main(String[] args) {
		/*String ron=GetString.getRandomString(32);
		System.out.println("Key"+ron);
		System.out.println(ron.length());
		AESEncryptTools.setKey("daf5c8a1");
		String key_ase = AESEncryptTools.Enrypt(ron);
		System.out.println(key_ase.length());
		String key = AESEncryptTools.deCrypt(key_ase);
		System.out.println(key);
		b=Encrypt.SHA512(a);
		System.out.println(b);
		AESEncryptTools.setKey("2333");
		String c=AESEncryptTools.Enrypt("tomcat");
		System.out.println("加密后"+c);
		System.out.println(c.length());
		String d=AESEncryptTools.deCrypt("0AF01690431FA3DF99B90917CEE23B2D983EFBBE43F18CD6DB9B6F862620993F76B48DACA83964669AF5E360B341C39C");
		System.out.println("解密后"+d);*/
		
		
		//SHA512
		/*String b=Encrypt.SHA512("daf5c8a1");
		System.out.println(b);*/
		
		//AESEncryptTools.setKey("daf5c8a1");
		//String or=AESEncryptTools.Enrypt("root");
		//String cose=AESEncryptTools.deCrypt("D2A31E020742675406851410335A9DF424ADA2766ED90654A274F44A8B49DD24");
		
		//System.out.println("hah");
		//System.out.println(cose);
		String p="daf5c8a1:";
		String de=AESTool.deCrypt("87CE3D95A57C5F870357A85267F8C8127BFFA526E149A32F78E6D851EF0512C2", p);
		System.out.println(de);

	}
}
