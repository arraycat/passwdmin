package com.passwdmin.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.passwdmin.dao.PasswdDAO;
import com.passwdmin.util.IDTrans;
import com.passwdmin.vo.Passwd;

/**
 * Servlet implementation class Query
 */
@WebServlet("/Query")
public class Query extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Query() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String methodName=request.getParameter("methodName");
		int method=Integer.parseInt(methodName);
		switch(method){
		case 1:
			infoDel(request, response);
			break;
		case 2:
			infoDetail(request, response);
			break;
		default:
			request.getRequestDispatcher("mypasswd.jsp").forward(request, response);
		}
		
		
	}

	private void infoDetail(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		try {
			try {
				request.getRequestDispatcher("alterinfo.jsp").forward(request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void infoDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PasswdDAO passwdDao = new PasswdDAO();
		ArrayList<Passwd> pwdlist=new ArrayList<Passwd>();
		ArrayList<Passwd> searchlist=new ArrayList<Passwd>();
		String username="";//（保存在session中的用户名）
		String id="";//对应数据库中的pid
		String list_id="";//对应ArrayList中的id
		
		username=request.getSession().getAttribute("username").toString();
		id=request.getParameter("id");
		list_id=request.getParameter("listid");
		//获取超链接传过来的参数
		
		int pid=0;//对应数据库
		int listid=-1;//对应pwdlist
		int searchlistid=-1;//对应seachlist
		pid=Integer.parseInt(id);
		
		passwdDao.delPasswd(username, pid);
		//调用方法删除数据库中的记录
		pwdlist=(ArrayList<Passwd>)request.getSession().getAttribute("pwdlist");
		searchlist=(ArrayList<Passwd>)request.getSession().getAttribute("searchlist");
		listid=IDTrans.dbId2ListId(pid, pwdlist);
		searchlistid=IDTrans.dbId2ListId(pid, searchlist);
		pwdlist.remove(listid);
		if(searchlistid!=-1) {
			searchlist.remove(searchlistid);
		}
		response.sendRedirect("mypasswd.jsp");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
