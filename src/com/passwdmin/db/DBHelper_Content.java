package com.passwdmin.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

import com.passwdmin.dao.PasswdDAO;

public class DBHelper_Content {
	
	private static final String driver="com.mysql.cj.jdbc.Driver";
	private static final String url="jdbc:mysql://localhost:3306/passwdmin?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&connectTimeout=0&socketTimeout=0";
	//设置连接数据库的字符串url，并添加serverTimezone=UTC";
	private static final String user="";
	private static final String password="";
	
	private static Connection conn=null;
	
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static Connection getConnection() {
		if(conn==null) {
			try {
				conn=DriverManager.getConnection(url, user, password);
				dbTimer();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}
	public static void closeRsultSet(ResultSet rs) {
		if (rs != null) {// 关闭资源，避免出现异常
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void closePreparedStatement(PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void dbTimer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
            	System.out.println("dbtimer(DBhelper_content)test");
            	PasswdDAO passwdDao=new PasswdDAO();	
            	passwdDao.getAllPasswd("root", "password");
            }
        }, 1000*60*60*6, 1000*60*60*6);
    }

}
