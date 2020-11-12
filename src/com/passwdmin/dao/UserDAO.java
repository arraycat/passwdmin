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
			String sql="SELECT * FROM customer;";//从用户表中获取用户数据
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
		//判断传入的username是否有数字,servlet中要判断用户名中是否符合要求
		//即能否作为表名
		if(IsNum.isNumeric(username)==true) {
			String uname="";
			uname="u"+username;
			username=uname;
		}
		
		try {
			conn=DBHelper.getConnection();
			String sql="SELECT * FROM customer where username=?;";//从用户表中获取用户数据
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
			String sql="SELECT * FROM customer where email=?;";//从用户表中获取用户数据
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
	public String getUserPasswd(String username){//按用户名获取密码，返回密码SHA512
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String passwd="";
		
		//判断传入的username是否有数字,servlet中要判断用户名中是否符合要求
		//即能否作为表名
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
			return "";//出现异常返回空
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
		//按用户名获AESKey，返回密钥
		
		//判断传入的username是否有数字,servlet中要判断用户名中是否符合要求
		//即能否作为表名
		
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
			return "";//出现异常返回空
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
		//判断传入的username是否有数字,servlet中要判断用户名中是否符合要求
		//即能否作为表名
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
			return "";//出现异常返回空
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
		//判断传入的username是否有数字,servlet中要判断用户名中是否符合要求
		//即能否作为表名
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
			//生成随机key
			aeskey=AESTool.enCrypt(key, sessionPassword);
			//对Key加密
			conn=DBHelper.getConnection();
			String sql="INSERT INTO customer (`username`, `password`, `email`, `last_ipaddr`,`aes_key`) VALUES (?, ?, ?, ?, ?);";
			//在passwdmin_user表中插入用户信息
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, u.getUsername());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getEmail());
			pstmt.setString(4, u.getLast_ipaddr());
			pstmt.setString(5, aeskey);
			pstmt.executeUpdate();
			if(passwdDao.createTable(u.getUsername())) {
				//在passwdmin表中创建用户表	
				userDao.setCreateTime(u);//设置创建时间
				passwdDao.addExamplePasswd(u.getUsername(), sessionPassword);
				return true;
			}
			else {
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;//出现异常返回空
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
			//在passwdmin_user表中插入用户信息
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, u.getUsername());
			pstmt.executeUpdate();
			
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;//出现异常返回空
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
			//在passwdmin_user表中插入用户信息
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, u.getUsername());
			pstmt.executeUpdate();
			
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;//出现异常返回空
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
	
	public boolean isUserReg(String username, String email){//按用户名获取密码，返回密码SHA512
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
			return true;//出现异常返回空
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
			//在passwdmin_user表中插入用户信息
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, u.getLast_ipaddr());
			pstmt.setString(2, u.getUsername());
			pstmt.executeUpdate();
			
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;//出现异常返回空
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
		//传入新密码之前需要判断是否符合规则
		//传入修改后的u，之前的密码，之前的用户名（保存在session中）
		//目前仅开发修改密码
		Connection conn=null;
		PreparedStatement pstmt=null;
		String aeskey="";
		String key="";
		String newaeskey="";
		try {
			if(sessionPassword.equals(newPassword)==false) {
				aeskey=getAESKey(sessionUsername);
				key=AESTool.deCrypt(aeskey, sessionPassword);
				//获得密钥（注册时随机生成的字符串)
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
			return false;//出现异常返回false
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
