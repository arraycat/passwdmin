package com.passwdmin.test;

import java.util.ArrayList;

import com.passwdmin.util.IsEmail;
import com.passwdmin.util.IsNum;
import com.passwdmin.vo.User;

public class Logic {
	public static void main(String[] args) {
		boolean flag=false;
		/*flag=IsEmail.isEmail("@@sjfhfhn");
		System.out.println(flag);*/
		
		
		ArrayList<String> reginfo=new ArrayList<String>();
		
		System.out.println(reginfo);
		reginfo.add("woshiyi");
		reginfo.add("hakh");
		System.out.println(reginfo);
		for(int i=0;i<reginfo.size();i++) {
			String info=reginfo.get(i);
			System.out.println(info);
		}
		reginfo=new ArrayList<String>();
//		reginfo.clear();
		System.out.println(reginfo);
		
		/*User u=new User();
		u.setUsername("1054");
		String uname="u"+u.getUsername();
		System.out.println(uname);*/
		
	}
}
