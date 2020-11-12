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
//���ڽ����޸ĺ�����ݣ������д���
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
		//�˴�sessionҲ��ֱ������request.getsession()
		Passwd pwd = new Passwd();//��Ӧpwdlist��
		Passwd passwd = new Passwd();//��Ӧ���ݿ�����е�
		PasswdDAO passwdDao = new PasswdDAO();
		ArrayList<Passwd> pwdlist=new ArrayList<Passwd>();
		
		String username="";//�û����û�����������session�У�
		String sessionPassword="";//�û�������
		String accountname="";
		String accountpassword="";
		String accountdesc="";
		String accounttype="";
//		i id="";//��Ӧ���ݿ��е�pid
		String list_id="-1";//��ӦArrayList�е�id
		int listid=0;
		int id=0;
		
		list_id=request.getSession().getAttribute("infoalterlistid").toString();
		//�˴�����session��getAttribute
//		System.out.println(list_id);
		listid=Integer.parseInt(list_id);
		username = session.getAttribute("username").toString();
		sessionPassword = session.getAttribute("sessionPassword").toString();
		//�û���������
		accountname = request.getParameter("accountname");
		accountpassword = request.getParameter("accountpassword");
		accountdesc = request.getParameter("accountdesc");
		accounttype =request.getParameter("accounttype");
		//jspҳ��alterinfo�������Ĳ���
		
		pwdlist = (ArrayList<Passwd>)request.getSession().getAttribute("pwdlist");
		pwd=pwdlist.get(listid);//��listid��ȡpwdlist�е�Ԫ��
		
		id=pwd.getPid();
		
		passwd.setAccount_name(accountname);
		passwd.setAccount_passwd(accountpassword);
		passwd.setAccount_desc(accountdesc);
		passwd.setAccount_type(accounttype);
		passwd.setPid(id);
		
//		System.out.println(passwd.toString());
		
		boolean flag=passwdDao.updatePasswd(username, sessionPassword, passwd);
//		System.out.println(flag);
		//���÷�����Ӽ�¼(���ݿ����)
		pwdlist.set(listid, passwd);
		response.sendRedirect("mypasswd.jsp");
		
	}

}
