package com.passwdmin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.passwdmin.db.DBHelper;
import com.passwdmin.test.encrypt;
import com.passwdmin.util.*;
import com.passwdmin.vo.Passwd;
import com.passwdmin.vo.User;

public class UserDAO {
	public ArrayList<User> getAllUser(){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<User> list=new ArrayList<User>();
		
		try {
			conn=DBHelper.getConnection();
			String sql="SELECT * FROM customer;";//���û����л�ȡ�û�����
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				User user=new User();
				user.setUid(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setLastTime(rs.getTimestamp(5));
				user.setLast_ipaddr(rs.getString(6));
				list.add(user);
			}
			return list;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try {
				DBHelper.closeRsultSet(rs);
				DBHelper.closePreparedStatement(pstmt);
//				DBHelper.closeConnection(conn);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		//return list;
	}
	
	public User getUser(String username){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		User user=new User();
		//�жϴ����username�Ƿ�������,servlet��Ҫ�ж��û������Ƿ����Ҫ��
		//���ܷ���Ϊ����
		if(IsNum.isNumeric(username)==true) {
			String uname="";
			uname="u"+username;
			username=uname;
		}
		
		try {
			conn=DBHelper.getConnection();
			String sql="SELECT * FROM customer where username=?;";//���û����л�ȡ�û�����
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				user.setUid(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setLastTime(rs.getTimestamp(5));
				user.setLast_ipaddr(rs.getString(6));
				user.setLevel(rs.getInt(9));
			}
			return user;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try {
				DBHelper.closeRsultSet(rs);
				DBHelper.closePreparedStatement(pstmt);
//				DBHelper.closeConnection(conn);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		//return list;
	}
	
	public User getUserByEmail(String email){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		User user=new User();

		
		try {
			conn=DBHelper.getConnection();
			String sql="SELECT * FROM customer where email=?;";//���û����л�ȡ�û�����
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				user.setUid(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setLastTime(rs.getTimestamp(5));
				user.setLast_ipaddr(rs.getString(6));
			}
			return user;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try {
				DBHelper.closeRsultSet(rs);
				DBHelper.closePreparedStatement(pstmt);
//				DBHelper.closeConnection(conn);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		//return list;
	}
	public String getUserPasswd(String username){//���û�����ȡ���룬��������SHA512
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String passwd="";
		
		//�жϴ����username�Ƿ�������,servlet��Ҫ�ж��û������Ƿ����Ҫ��
		//���ܷ���Ϊ����
		if(IsNum.isNumeric(username)==true) {
			String uname="";
			uname="u"+username;
			username=uname;
		}
		
		try {
			conn=DBHelper.getConnection();
			String sql="SELECT * FROM customer WHERE username='"+username+"';";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				passwd=rs.getString(3);
			}
			return passwd;
		}
		catch(Exception e) {
			e.printStackTrace();
			return "";//�����쳣���ؿ�
		}
		finally {
			try {
				DBHelper.closeRsultSet(rs);
				DBHelper.closePreparedStatement(pstmt);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getAESKey(String username){
		//���û�����AESKey��������Կ
		
		//�жϴ����username�Ƿ�������,servlet��Ҫ�ж��û������Ƿ����Ҫ��
		//���ܷ���Ϊ����
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String aeskey="";
		if(IsNum.isNumeric(username)==true) {
			String uname="";
			uname="u"+username;
			username=uname;
		}
		
		try {
			conn=DBHelper.getConnection();
			String sql="SELECT * FROM customer WHERE username='"+username+"';";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				aeskey=rs.getString(8);
			}
			return aeskey;
		}
		catch(Exception e) {
			e.printStackTrace();
			return "";//�����쳣���ؿ�
		}
		finally {
			try {
				DBHelper.closeRsultSet(rs);
				DBHelper.closePreparedStatement(pstmt);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getUid(String username){
		//�жϴ����username�Ƿ�������,servlet��Ҫ�ж��û������Ƿ����Ҫ��
		//���ܷ���Ϊ����
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String uid="";
		if(IsNum.isNumeric(username)==true) {
			String uname="";
			uname="u"+username;
			username=uname;
		}
		try {
			conn=DBHelper.getConnection();
			String sql="SELECT * FROM customer WHERE username='"+username+"';";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				uid=String.valueOf(rs.getInt(1));
			}
			return uid;
		}
		catch(Exception e) {
			e.printStackTrace();
			return "";//�����쳣���ؿ�
		}
		finally {
			try {
				DBHelper.closeRsultSet(rs);
				DBHelper.closePreparedStatement(pstmt);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean userReg(User u, String sessionPassword) {
		//�жϴ����username�Ƿ�������,servlet��Ҫ�ж��û������Ƿ����Ҫ��
		//���ܷ���Ϊ����
		PasswdDAO passwdDao=new PasswdDAO();
		Connection conn=null;
		PreparedStatement pstmt=null;
		UserDAO userDao=new UserDAO();
		if(IsNum.isNumeric(u.getUsername())==true) {
			String uname="";
			uname="u"+u.getUsername();
			u.setUsername(uname);
		}
		try {
			String key=GetString.getRandomString(16);
			String aeskey="";
			//�������key
			aeskey=AESTool.enCrypt(key, sessionPassword);
			//��Key����
			conn=DBHelper.getConnection();
			String sql="INSERT INTO customer (`username`, `password`, `email`, `last_ipaddr`,`aes_key`) VALUES (?, ?, ?, ?, ?);";
			//��passwdmin_user���в����û���Ϣ
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, u.getUsername());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getEmail());
			pstmt.setString(4, u.getLast_ipaddr());
			pstmt.setString(5, aeskey);
			pstmt.executeUpdate();
			if(passwdDao.createTable(u.getUsername())) {
				//��passwdmin���д����û���	
				userDao.setCreateTime(u);//���ô���ʱ��
				passwdDao.addExamplePasswd(u.getUsername(), sessionPassword);
				return true;
			}
			else {
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;//�����쳣���ؿ�
		}
		finally {
			try {
				DBHelper.closePreparedStatement(pstmt);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean setCreateTime(User u) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DBHelper.getConnection();
			String sql="UPDATE customer SET create_time=last_logintime WHERE username=?;";
			//��passwdmin_user���в����û���Ϣ
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, u.getUsername());
			pstmt.executeUpdate();
			
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;//�����쳣���ؿ�
		}
		finally {
			try {
				DBHelper.closePreparedStatement(pstmt);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean setLoginTime(User u) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DBHelper.getConnection();
			String sql="UPDATE customer SET last_logintime=NOW() WHERE username=?;";
			//��passwdmin_user���в����û���Ϣ
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, u.getUsername());
			pstmt.executeUpdate();
			
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;//�����쳣���ؿ�
		}
		finally {
			try {
				DBHelper.closePreparedStatement(pstmt);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isUserReg(String username, String email){//���û�����ȡ���룬��������SHA512
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=DBHelper.getConnection();
			String sql="SELECT * FROM customer;";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				if(username.equals(rs.getString(2)) || email.equals(rs.getString(4))) {
					return true;
				}
				
			}
			return false;
		}
		catch(Exception e) {
			e.printStackTrace();
			return true;//�����쳣���ؿ�
		}
		finally {
			try {
				DBHelper.closeRsultSet(rs);
				DBHelper.closePreparedStatement(pstmt);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean setIpAddr(User u) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DBHelper.getConnection();
			String sql="UPDATE customer SET last_ipaddr=? WHERE username=?;";
			//��passwdmin_user���в����û���Ϣ
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, u.getLast_ipaddr());
			pstmt.setString(2, u.getUsername());
			pstmt.executeUpdate();
			
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;//�����쳣���ؿ�
		}
		finally {
			try {
				DBHelper.closePreparedStatement(pstmt);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean updateUser(String newPassword, String sessionUsername, String sessionPassword) {
		//����������֮ǰ��Ҫ�ж��Ƿ���Ϲ���
		//�����޸ĺ��u��֮ǰ�����룬֮ǰ���û�����������session�У�
		//Ŀǰ�������޸�����
		Connection conn=null;
		PreparedStatement pstmt=null;
		String aeskey="";
		String key="";
		String newaeskey="";
		try {
			if(sessionPassword.equals(newPassword)==false) {
				aeskey=getAESKey(sessionUsername);
				key=AESTool.deCrypt(aeskey, sessionPassword);
				//�����Կ��ע��ʱ������ɵ��ַ���)
				newaeskey=AESTool.enCrypt(key, newPassword);
			}
			else {
				return true;
			}
			conn=DBHelper.getConnection();
			String sql="UPDATE customer SET password = ?, aes_key=? WHERE username=?;";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Encrypt.SHA512(newPassword));
			pstmt.setString(2, newaeskey);
			pstmt.setString(3, sessionUsername);
			pstmt.executeUpdate();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;//�����쳣����false
		}
		finally {
			try {
				DBHelper.closePreparedStatement(pstmt);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
