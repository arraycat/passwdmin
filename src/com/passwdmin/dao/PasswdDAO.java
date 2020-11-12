package com.passwdmin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.passwdmin.db.DBHelper_Content;
import com.passwdmin.util.AESTool;
import com.passwdmin.util.IsNum;
import com.passwdmin.vo.Passwd;

public class PasswdDAO {
	public ArrayList<Passwd> list=null;
	
	public ArrayList<Passwd> getAllPasswd(String username, String sessionPassword){
		//��ȡ�û�ȫ������
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String aeskey="";//�洢��customer���е�ʹ��sessionPassword���ܵ�����
		String key="";//���ܺ��key
		if((sessionPassword=="") || (username=="")) {
			return null;
		}
		else {
			//�жϴ����username�Ƿ�������,servlet��Ҫ�ж��û������Ƿ����Ҫ��
			//���ܷ���Ϊ����
			if(IsNum.isNumeric(username)==true) {
				String uname="";
				uname="u"+username;
				username=uname;
			}
			list=new ArrayList<Passwd>();
			
			try {
				conn=DBHelper_Content.getConnection();
				String sql="SELECT * FROM "+username+";";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					UserDAO userDao=new UserDAO();
					aeskey=userDao.getAESKey(username);
					key=AESTool.deCrypt(aeskey, sessionPassword);
//					AESEncryptTools.setKey(key);
					Passwd pwd=new Passwd();
					
					try {
						pwd.setPid(rs.getInt(1));
						pwd.setAccount_name(AESTool.deCrypt(rs.getString(2), key));
						pwd.setAccount_passwd(AESTool.deCrypt(rs.getString(3),key));
						pwd.setAccount_type(rs.getString(4));
						pwd.setAccount_desc(rs.getString(5));
						pwd.setUpdate_time(rs.getTimestamp(6));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
					
					list.add(pwd);
				}
				return list;
			}
			catch(Exception e) {
				e.printStackTrace();
				return null;
			}
			finally {
				try {
					DBHelper_Content.closeRsultSet(rs);
					DBHelper_Content.closePreparedStatement(pstmt);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public boolean delPasswd(String username,int pid){
		//����PIDɾ���û�����
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		//�жϴ����username�Ƿ�������,servlet��Ҫ�ж��û������Ƿ����Ҫ��
		//���ܷ���Ϊ����
		if(IsNum.isNumeric(username)==true) {
			String uname="";
			uname="u"+username;
			username=uname;
		}
		
		try {
			conn=DBHelper_Content.getConnection();
			String sql="DELETE FROM "+username+" WHERE pid = "+pid+";";
			//�˴��������ֶ�������ʹ��ռλ��
			pstmt=conn.prepareStatement(sql);
			pstmt.executeUpdate();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
		
				DBHelper_Content.closePreparedStatement(pstmt);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	

	public boolean addPasswd(String username, String sessionPassword, Passwd passwd){
		//�����û������������Լ�ʵ����Passwd��seesionPassword���ڼ�����ΪKey
		Connection conn=null;
		PreparedStatement pstmt=null;
		String aeskey="";//�洢��customer���е�ʹ��sessionPassword���ܵ�����
		String key="";//���ܺ��key
		
		//�жϴ����username�Ƿ�������,servlet��Ҫ�ж��û������Ƿ����Ҫ��
		//���ܷ���Ϊ����
		if(IsNum.isNumeric(username)==true) {
			String uname="";
			uname="u"+username;
			username=uname;
		}
		
//		System.out.println("DAO addPasswd������"+passwd.toString());
		try {
//			System.out.println(sessionPassword);
//			System.out.println(passwd.toString());
			UserDAO userDao=new UserDAO();
			aeskey=userDao.getAESKey(username);
//			System.out.println("DAO addPasswd����ȡkey"+aeskey);
//			System.out.println(aeskey.length());
//			AESEncryptTools.setKey(sessionPassword);
			key=AESTool.deCrypt(aeskey, sessionPassword);//�����Կ�ı�
//			System.out.println(key);
//			AESEncryptTools.setKey(key);
			conn=DBHelper_Content.getConnection();
			String sql="INSERT INTO "+username+" (account_name, account_passwd, account_type, account_desc) VALUES (?, ?, ?, ?);";
			//�˴��������ֶ�������ʹ��ռλ��
			pstmt=conn.prepareStatement(sql);
//			System.out.println("DAO addPasswd����ȡkey"+key);
			pstmt.setString(1, AESTool.enCrypt(passwd.getAccount_name(),key));
			pstmt.setString(2, AESTool.enCrypt(passwd.getAccount_passwd(),key));
			//ʹ��AES�����˻�����
			pstmt.setString(3, passwd.getAccount_type());
			pstmt.setString(4, passwd.getAccount_desc());
			pstmt.executeUpdate();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				DBHelper_Content.closePreparedStatement(pstmt);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean updatePasswd(String username, String sessionPassword, Passwd passwd){
		//�����û��������������û������Լ�ʵ����Passwd
		Connection conn=null;
		PreparedStatement pstmt=null;
		String aeskey="";//�洢��customer���е�ʹ��sessionPassword���ܵ�����
		String key="";//���ܺ��key
		
		//�жϴ����username�Ƿ�������,servlet��Ҫ�ж��û������Ƿ����Ҫ��
		//���ܷ���Ϊ����
		if(IsNum.isNumeric(username)==true) {
			String uname="";
			uname="u"+username;
			username=uname;
		}
		
//		System.out.println(username);
//		System.out.println(sessionPassword);
		try {
			UserDAO userDao=new UserDAO();
			aeskey=userDao.getAESKey(username);
//			AESEncryptTools.setKey(sessionPassword);
			key=AESTool.deCrypt(aeskey, sessionPassword);//�����Կ�ı�
//			AESEncryptTools.setKey(key);
			conn=DBHelper_Content.getConnection();
			String sql="UPDATE "+username+" SET update_time=NOW(), account_name = ?, account_passwd = ?, account_type = ?, account_desc = ? WHERE pid = ?;";
			//�˴��������ֶ�������ʹ��ռλ��
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, AESTool.enCrypt(passwd.getAccount_name(),key));
			pstmt.setString(2, AESTool.enCrypt(passwd.getAccount_passwd(),key));
			pstmt.setString(3, passwd.getAccount_type());
			pstmt.setString(4, passwd.getAccount_desc());
			pstmt.setInt(5, passwd.getPid());
			pstmt.executeUpdate();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				DBHelper_Content.closePreparedStatement(pstmt);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean createTable(String username){
		//�����û�����������
		Connection conn=null;
		PreparedStatement pstmt=null;
		//�жϴ����username�Ƿ�������,servlet��Ҫ�ж��û������Ƿ����Ҫ��
		//���ܷ���Ϊ����
		if(IsNum.isNumeric(username)==true) {
			String uname="";
			uname="u"+username;
			username=uname;
		}
		//ʹ���û�ID��ΪKey
		try {
			conn=DBHelper_Content.getConnection();
			String sql="create table "+username+" like root;";
			//�˴��������ֶ�������ʹ��ռλ��
			pstmt=conn.prepareStatement(sql);
			pstmt.executeUpdate();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				DBHelper_Content.closePreparedStatement(pstmt);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean addExamplePasswd(String username, String sessionPassword){
		//����addPasswd��������ʾ������
		PasswdDAO passwdDao = new PasswdDAO();
		Passwd pwd1=new Passwd();
		Passwd pwd2=new Passwd();
		Passwd pwd3=new Passwd();
		
		pwd1.setAccount_name("13800871500");
		pwd1.setAccount_passwd("password");
		pwd1.setAccount_desc("΢����ʾ���˻�����ɾ����");
		pwd1.setAccount_type("��վ��app");
		
		pwd2.setAccount_name("������");
		pwd2.setAccount_passwd("password");
		pwd2.setAccount_desc("ʾ���˻�����ɾ��");
		pwd2.setAccount_type("������д");
		
		pwd3.setAccount_name("YNU_2.4G��WIFI����");
		pwd3.setAccount_passwd("password");
		pwd3.setAccount_desc("WIFI���루ʾ���˻�����ɾ����");
		pwd3.setAccount_type("�豸");
		
		try {
			passwdDao.addPasswd(username, sessionPassword, pwd1);
			passwdDao.addPasswd(username, sessionPassword, pwd2);
			passwdDao.addPasswd(username, sessionPassword, pwd3);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
