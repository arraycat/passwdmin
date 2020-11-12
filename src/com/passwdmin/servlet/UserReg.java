package com.passwdmin.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.passwdmin.dao.PasswdDAO;
import com.passwdmin.dao.UserDAO;
import com.passwdmin.util.Encrypt;
import com.passwdmin.util.StringType;
import com.passwdmin.vo.User;

/**
 * Servlet implementation class UserReg
 */
@WebServlet("/UserReg")
public class UserReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserReg() {
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
		User u = new User();
		UserDAO userDao = new UserDAO();
		PasswdDAO passwdDao = new PasswdDAO();
		String username = "";
		String password = "";
		String password2 = "";
		String email = "";
		String ipAddr = "";
		String sessionPassword="";
		boolean flag = false;
		ArrayList<String> reginfo=new ArrayList<String>();
		ArrayList<Boolean> regflag=new ArrayList<Boolean>();
		//用于显示注册的结果
		
		request.setCharacterEncoding("UTF-8");
		username = request.getParameter("username");
		if(StringType.isLetterDigitOrChinese(username)){
			reginfo.add("用户名符合规范；");
			regflag.add(true);
		}
		else {
			reginfo.add("用户名不符合规范，请使用中文,字母,数字或其组合；");
			regflag.add(false);
		}
		password = request.getParameter("password");
		password2 = request.getParameter("password2");
		if(password.length()<10) {
			reginfo.add("您刚才输入的密码长度小于10，请重新输入；");
			regflag.add(false);
		}
		else {
			reginfo.add("密码长度符合要求；");
			regflag.add(true);
		}
		if(password.equals(password2)) {
			reginfo.add("两次密码输入一致；");
			regflag.add(true);
			sessionPassword=password;
		}
		else {
			reginfo.add("两次密码输入不一致，请重新输入；");
			regflag.add(false);
		}
		email = request.getParameter("email");
		ipAddr = request.getRemoteAddr();
		
		if(userDao.isUserReg(username, email)==true) {
			reginfo.add("用户名("+username+")或邮箱("+email+")已被注册，请使用其他用户名或邮箱，如您忘记密码，请点击登录页面的忘记密码；");
			regflag.add(false);
		}
		else {
			regflag.add(true);
		}
		boolean reg=false;
		for(int i=0;i<regflag.size();i++) {
			reg=regflag.get(i);
			if(reg==false) {
				break;
			}
		}
		if(reg!=false) {
			u.setUsername(username);
			u.setPassword(Encrypt.SHA512(password));
			u.setEmail(email);
			u.setLast_ipaddr(ipAddr);
			flag=userDao.userReg(u, sessionPassword);
		}
		else {
			reginfo.add("注册失败！");
			request.setAttribute("username", username);
			request.setAttribute("email", email);			
			request.setAttribute("reginfo", reginfo);
			request.getRequestDispatcher("reg_failure.jsp").forward(request, response);
		}
		if(flag==true) {
			reginfo.add("注册成功！");
			request.setAttribute("username", username);
			request.setAttribute("email", email);
			request.setAttribute("reginfo", reginfo);
			request.getRequestDispatcher("reg_success.jsp").forward(request, response);
		}
	}
	
}
