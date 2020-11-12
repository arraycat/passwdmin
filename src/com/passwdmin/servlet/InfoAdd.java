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
import com.passwdmin.vo.Passwd;

/**
 * Servlet implementation class InfoAdd
 */
@WebServlet("/InfoAdd")
public class InfoAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoAdd() {
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

		Passwd pwd = new Passwd();
		PasswdDAO passwdDao = new PasswdDAO();
		ArrayList<Passwd> pwdlist=new ArrayList<Passwd>();
		
		String username="";//�û����û�����������session�У�
		String sessionPassword="";
		String accountname="";
		String accountpassword="";
		String accountdesc="";
		String accounttype="";
		
		request.setCharacterEncoding("utf-8");
		
		username = session.getAttribute("username").toString();
		sessionPassword = session.getAttribute("sessionPassword").toString();
		accountname = request.getParameter("accountname");
		accountpassword = request.getParameter("accountpassword");
		accountdesc = request.getParameter("accountdesc");
		accounttype =request.getParameter("accounttype");
		
		pwd.setAccount_name(accountname);
		pwd.setAccount_passwd(accountpassword);
		pwd.setAccount_desc(accountdesc);
		pwd.setAccount_type(accounttype);
//		System.out.println("InfoAddServlet����������"+pwd.toString());
		passwdDao.addPasswd(username, sessionPassword, pwd);
		pwdlist = (ArrayList<Passwd>)request.getSession().getAttribute("pwdlist");
		//���÷�����Ӽ�¼
		//pwdlist = passwdDao.getAllPasswd(username, sessionPassword);
		pwdlist.add(pwd);
		//request.getSession().setAttribute("username", username);
		//session.setAttribute("pwdlist", pwdlist);
		//���¼�¼\
		response.setCharacterEncoding("utf-8");
		response.sendRedirect("mypasswd.jsp");
		//response.sendRedirect("mypasswd.jsp");
	}

}
