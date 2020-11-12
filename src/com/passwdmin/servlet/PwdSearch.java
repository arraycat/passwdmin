package com.passwdmin.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.passwdmin.util.PwdSerach;
import com.passwdmin.vo.Passwd;

/**
 * Servlet implementation class PwdSearch
 */
@WebServlet("/PwdSearch")
public class PwdSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwdSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String search="";
		ArrayList<Passwd> searchlist=new ArrayList<Passwd>();
		ArrayList<Passwd> pwdlist=new ArrayList<Passwd>();
		
		searchlist=(ArrayList<Passwd>)request.getSession().getAttribute("searchlist");
		pwdlist=(ArrayList<Passwd>)request.getSession().getAttribute("pwdlist");
		
		search=request.getParameter("search");
		System.out.println(search);
		if(searchlist!=null) {
			searchlist.removeAll(searchlist);
			searchlist.addAll(PwdSerach.search(search, pwdlist));
			response.sendRedirect("search.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
