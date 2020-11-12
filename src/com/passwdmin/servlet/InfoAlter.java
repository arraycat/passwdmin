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
 * Servlet implementation class InfoAlter
 */
@WebServlet("/InfoAlter")
//用于接受修改后的数据，并进行处理
public class InfoAlter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoAlter() {
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
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		//此处session也可直接试用request.getsession()
		Passwd pwd = new Passwd();//对应pwdlist、
		Passwd passwd = new Passwd();//对应数据库操作中的
		PasswdDAO passwdDao = new PasswdDAO();
		ArrayList<Passwd> pwdlist=new ArrayList<Passwd>();
		
		String username="";//用户的用户名（保存在session中）
		String sessionPassword="";//用户的密码
		String accountname="";
		String accountpassword="";
		String accountdesc="";
		String accounttype="";
//		i id="";//对应数据库中的pid
		String list_id="-1";//对应ArrayList中的id
		int listid=0;
		int id=0;
		
		list_id=request.getSession().getAttribute("infoalterlistid").toString();
		//此处试用session来getAttribute
//		System.out.println(list_id);
		listid=Integer.parseInt(list_id);
		username = session.getAttribute("username").toString();
		sessionPassword = session.getAttribute("sessionPassword").toString();
		//用户名和密码
		accountname = request.getParameter("accountname");
		accountpassword = request.getParameter("accountpassword");
		accountdesc = request.getParameter("accountdesc");
		accounttype =request.getParameter("accounttype");
		//jsp页面alterinfo传过来的参数
		
		pwdlist = (ArrayList<Passwd>)request.getSession().getAttribute("pwdlist");
		pwd=pwdlist.get(listid);//以listid获取pwdlist中的元素
		
		id=pwd.getPid();
		
		passwd.setAccount_name(accountname);
		passwd.setAccount_passwd(accountpassword);
		passwd.setAccount_desc(accountdesc);
		passwd.setAccount_type(accounttype);
		passwd.setPid(id);
		
//		System.out.println(passwd.toString());
		
		boolean flag=passwdDao.updatePasswd(username, sessionPassword, passwd);
//		System.out.println(flag);
		//调用方法添加记录(数据库操作)
		pwdlist.set(listid, passwd);
		response.sendRedirect("mypasswd.jsp");
		
	}

}
