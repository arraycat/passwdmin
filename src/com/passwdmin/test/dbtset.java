package com.passwdmin.test;

import java.util.ArrayList;

import com.passwdmin.vo.Passwd;
import com.passwdmin.vo.User;
import com.passwdmin.dao.*;

public class dbtset {
	public static void main(String[] args) {
		UserDAO userDao=new UserDAO();
		PasswdDAO passwdDao=new PasswdDAO();
		boolean flag=false;
//		ArrayList<User> list = userDao.getAllUser();
//		ArrayList<Passwd> list2 = passwdDao.getAllPasswd("root", "daf5c8a1");
//		String passwdSha=userDao.getUserPasswd("root");
		/*for(int i=0;i<list2.size();i++) {
//			User user = list.get(i);
	
//			System.out.println(user.getCreate_time());
			Passwd passwd = list2.get(i);
			System.out.println(passwd.getPid());
			System.out.println(passwd.getAccount_passwd());
		}*/
		
		//����������ԣ��Ȼ�ȡ����
		/*Passwd pwd=new Passwd();
		Passwd passwd=new Passwd();
//		pwd = list2.get(0);
		
		System.out.println(pwd.toString());
		passwd=pwd;
		passwd.setAccount_passwd("�۹���");
		System.out.println(passwd.toString());*/
		//flag=passwdDao.updatePasswd("root", "daf5c8a1", passwd);//��ϸ������Խ���
		
		
		//��ȡaeskey����
		/*String aeskey=userDao.getAESKey("ssh");
		System.out.println(aeskey);
		System.out.println(aeskey.length());
		
		boolean flag=false;
		AESEncryptTools.setKey("");
		String testasekey=AESEncryptTools.Enrypt("vGjaH2dx4nFnDqSu");
		System.out.println("���ԣ�"+testasekey);
		String key = AESEncryptTools.deCrypt("B67E035105DE384923C36D04C589BA599A87F3FFC8FF5C5443641817A5895540");
		System.out.println(key);*/
		
		
		/*User u =new User();
		u.setUsername("root");
		u.setPassword("test");*/
		//flag=userDao.userReg(u);
		
		/*Passwd pwd=new Passwd();
		pwd.setAccount_name("��");
		pwd.setAccount_passwd("������");
		System.out.println(pwd.toString());
		flag=passwdDao.addPasswd("root", "daf5c8a1", pwd);*/
		
		/*Passwd pwd = new Passwd();
		pwd.setAccount_name("�ȸ�4");
		pwd.setAccount_passwd("������");
		pwd.setAccount_type("web");
		pwd.setAccount_desc("guge");
		flag=passwdDao.addPasswd("root", pwd);*/
		
		
//		flag = passwdDao.delPasswd("root", 65);
		
//		flag=userDao.isUserReg("tomcat","dd");
//		System.out.println(flag);
		
		//���������ȡ
		/*String email="pingguanzhang@qq.com";
		User u =new User();
		u=userDao.getUserByEmail(email);
		System.out.println(u.toString());*/
		
		
		//�����޸�����
		/*flag=userDao.updateUser("1723194159", "ssh", "1054353584");*/
		
		//���������ϴε�¼ʱ��
		/*flag=userDao.setLoginTime(u);*/
		System.out.println(flag);
		
		//���Բ�������
		flag=passwdDao.addExamplePasswd("123", "1234567890");
		System.out.println(flag);
		
//		System.out.println(passwdSha);
	}
}
