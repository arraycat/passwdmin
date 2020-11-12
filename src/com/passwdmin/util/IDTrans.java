package com.passwdmin.util;

import java.util.ArrayList;

import com.passwdmin.dao.PasswdDAO;
import com.passwdmin.vo.Passwd;

import sun.tools.jar.Main;

public class IDTrans {
	//用于密码数据库ID和列表中ID的转换
	public static int dbId2ListId(int dbpid, ArrayList<Passwd> list) {
		
		int listid = -1;
		Passwd p = new Passwd();
		
		if(list!=null) {
			for(int i=0; i<list.size(); i++) {
				p = list.get(i);
				if(p.getPid()==dbpid) {
					listid = i;
				}
			}
		}
		return listid;
	}
	
	public static int listId2dbId(int listpid, ArrayList<Passwd> list) {
		
		int dbpid = -1;
		Passwd p = new Passwd();
		
		p=list.get(listpid);
		dbpid=p.getPid();
		return dbpid;
	}
/*	public static void main(String[] args) {
		ArrayList<Passwd> pwdlist = new ArrayList<Passwd>();
		PasswdDAO passwdDao = new PasswdDAO();
		pwdlist = passwdDao.getAllPasswd("root", "daf5c8a1:");
		
		int dbpid=listId2dbId(0, pwdlist);
		System.out.println("db:"+dbpid);
		int listid=dbId2ListId(dbpid, pwdlist);
		System.out.println("list:"+listid);
	}*/
	
}
