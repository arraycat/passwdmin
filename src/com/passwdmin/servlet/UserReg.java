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
		//������ʾע��Ľ��
		
		request.setCharacterEncoding("UTF-8");
		username = request.getParameter("username");
		if(StringType.isLetterDigitOrChinese(username)){
			reginfo.add("�û������Ϲ淶��");
			regflag.add(true);
		}
		else {
			reginfo.add("�û��������Ϲ淶����ʹ������,��ĸ,���ֻ�����ϣ�");
			regflag.add(false);
		}
		password = request.getParameter("password");
		password2 = request.getParameter("password2");
		if(password.length()<10) {
			reginfo.add("���ղ���������볤��С��10�����������룻");
			regflag.add(false);
		}
		else {
			reginfo.add("���볤�ȷ���Ҫ��");
			regflag.add(true);
		}
		if(password.equals(password2)) {
			reginfo.add("������������һ�£�");
			regflag.add(true);
			sessionPassword=password;
		}
		else {
			reginfo.add("�����������벻һ�£����������룻");
			regflag.add(false);
		}
		email = request.getParameter("email");
		ipAddr = request.getRemoteAddr();
		
		if(userDao.isUserReg(username, email)==true) {
			reginfo.add("�û���("+username+")������("+email+")�ѱ�ע�ᣬ��ʹ�������û��������䣬�����������룬������¼ҳ����������룻");
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
			reginfo.add("ע��ʧ�ܣ�");
			request.setAttribute("username", username);
			request.setAttribute("email", email);			
			request.setAttribute("reginfo", reginfo);
			request.getRequestDispatcher("reg_failure.jsp").forward(request, response);
		}
		if(flag==true) {
			reginfo.add("ע��ɹ���");
			request.setAttribute("username", username);
			request.setAttribute("email", email);
			request.setAttribute("reginfo", reginfo);
			request.getRequestDispatcher("reg_success.jsp").forward(request, response);
		}
	}
	
}
