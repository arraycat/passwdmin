package com.passwdmin.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.passwdmin.dao.PasswdDAO;
import com.passwdmin.dao.UserDAO;
import com.passwdmin.util.Encrypt;
import com.passwdmin.util.PwdSerach;
import com.passwdmin.util.StringType;
import com.passwdmin.vo.Passwd;
import com.passwdmin.vo.User;

/**
 * Servlet implementation class DoLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		UserDAO userDao=new UserDAO();
		PasswdDAO passwdDao=new PasswdDAO();
		User u=new User();
		ArrayList<Passwd> pwdlist=new ArrayList<Passwd>();
		ArrayList<Passwd> searchlist=new ArrayList<Passwd>();
		//存储搜索的集合
		String username="";
		String password="";
		String search="";//登录表单中提交的搜索内容
		String sessionPassword="";
		String ipAddr="";
		String dbPasswd="";//存储在数据库中的128位密码
		String SHA_password="";//表单接收的密码生成128位字符串
		request.setCharacterEncoding("utf-8");
		 
		ipAddr = request.getRemoteAddr();
		username=request.getParameter("username");
		password=request.getParameter("password");
		search=request.getParameter("search");
		
		if(StringType.isEmail(username)) {
			SHA_password=Encrypt.SHA512(password);
			u=userDao.getUserByEmail(username);//与数据库中的对比
			dbPasswd=u.getPassword();
			username=u.getUsername();		
		}
		else {
			SHA_password=Encrypt.SHA512(password);
			dbPasswd=userDao.getUserPasswd(username);//与数据库中的对比
		}
		if(dbPasswd.equals(SHA_password)){
			sessionPassword=password;
			u.setUsername(username);
			u.setLast_ipaddr(ipAddr);
			userDao.setLoginTime(u);//设置登陆的时间
			userDao.setIpAddr(u);
			//蛇者登陆的ip地址
			u=userDao.getUser(username);
			//获取用户信息,用于在userinfo.jsp中显示
			pwdlist=passwdDao.getAllPasswd(username,sessionPassword);
			session.setMaxInactiveInterval(5*60);//以秒为单位
			session.setAttribute("username", username);//session中设置用户名
			session.setAttribute("u", u);
			session.setAttribute("sessionPassword", sessionPassword);
			session.setAttribute("pwdlist", pwdlist);
			if(search!="") {
				searchlist=PwdSerach.search(search, pwdlist);
				session.setAttribute("searchlist", searchlist);
				response.sendRedirect("search.jsp");
			}
			else {
			//request.setAttribute("pdwlist", pwdlist);
			response.sendRedirect("mypasswd.jsp");
			session.setAttribute("searchlist", searchlist);
			}
		}
		else{
			response.sendRedirect("login_failure.html");
		}
	}

}
