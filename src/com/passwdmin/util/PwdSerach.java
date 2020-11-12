package com.passwdmin.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.passwdmin.vo.Passwd;


public class PwdSerach {
	//原文连接:https://www.cnblogs.com/luxj/p/8279254.html
	//与原文相比,增加了泛型
	public static ArrayList<Passwd> search(String name,ArrayList<Passwd> list){
		   ArrayList<Passwd> results = new ArrayList<Passwd>();
		   Pattern pattern = Pattern.compile(name);
		   for(int i=0; i < list.size(); i++){
		      Matcher matcher = pattern.matcher(((Passwd)list.get(i)).getAccount_desc());
		      if(matcher.find()){
		         results.add(list.get(i));
		      }
		   }
		   return results;
		}
	
	/*
	 * main方法用于测试
	public static void main(String[] args) {
		PasswdDAO passwdDao=new PasswdDAO();
		ArrayList<Passwd> pwdlist=new ArrayList<Passwd>();
		pwdlist=passwdDao.getAllPasswd("root", "daf5c8a1");
		ArrayList<Passwd> result=new ArrayList<Passwd>();
		
		result=(ArrayList<Passwd>) search("印象", pwdlist);
		
		for(int i=0;i<result.size();i++) {
			Passwd pwd=result.get(i);
			System.out.println(pwd.getAccount_desc());
		}
	}*/
}
