package com.passwdmin.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.passwdmin.dao.UserDAO;
import com.passwdmin.util.Encrypt;
import com.passwdmin.vo.User;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User u = new User();
		UserDAO userDao = new UserDAO();
		ArrayList<String> updateinfo=new ArrayList<String>();
		ArrayList<Boolean> updateflag=new ArrayList<Boolean>();
		//�߼�����ע��
	
		String oldpassword="";
		String sessionPassword="";
		String newpassword1="";
		String newpassword2="";
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		oldpassword=request.getParameter("oldpassword");
		newpassword1=request.getParameter("newpassword1");
		newpassword2=request.getParameter("newpassword2");
		sessionPassword=request.getSession().getAttribute("sessionPassword").toString();
		System.out.println(oldpassword);
		System.out.println(newpassword1);
		System.out.println(newpassword2);
		if(request.getSession().getAttribute("u")!=null){
			u=(User)request.getSession().getAttribute("u");
		}//��ȡsession�е��û���Ϣ
		
		if(oldpassword.equals(sessionPassword)) {
			updateinfo.add("ԭ������֤�ɹ���");
			updateflag.add(true);
		}
		else {
			updateinfo.add("ԭ������֤ʧ�ܣ�");
			updateflag.add(false);
		}
		if(newpassword1.length()<10) {
			updateinfo.add("���ղ�����������볤��С��10�����������룻");
			updateflag.add(false);
		}
		else {
			updateinfo.add("�����볤�ȷ���Ҫ��");
			updateflag.add(true);
		}
		if(newpassword1.equals(newpassword2)) {
			updateinfo.add("������������������һ�£�");
			updateflag.add(true);
		}
		else {
			updateinfo.add("�����������������벻һ�£����������룻");
			updateflag.add(false);
		}
		
		boolean update=true;
		for(int i=0;i<updateflag.size();i++) {
			update=updateflag.get(i);
			if(update==false) {
				break;
			}
		}
		
		boolean flag=false;//���ݿ������flag
		if(update==true) {
			System.out.println("old"+oldpassword);
			System.out.println("new"+newpassword1);
			System.out.println(newpassword2);
			System.out.println("�û���:"+u.getUsername());
			System.out.println("sessionOld"+sessionPassword);
			flag=userDao.updateUser(newpassword1, u.getUsername(), sessionPassword);
			if(flag==true) {
				updateinfo.add("�����޸ĳɹ���");
				sessionPassword=newpassword1;
				System.out.println("sessionNew"+sessionPassword);
				request.getSession().setAttribute("sessionPassword", sessionPassword);
				u.setPassword(Encrypt.SHA512(newpassword1));
			}
			else {
				updateinfo.add("�����޸�ʧ�ܣ�");
			}
		}
		else {
			updateinfo.add("�޸�ʧ�ܣ�");
		}
		request.setAttribute("u", u);
		request.setAttribute("updateinfo", updateinfo);
		request.getRequestDispatcher("updateinfo.jsp").forward(request, response);
		
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		response.getWriter().append("���ڿ���,�����ڴ���");
	}

}
