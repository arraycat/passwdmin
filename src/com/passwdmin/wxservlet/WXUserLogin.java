package com.passwdmin.wxservlet;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.passwdmin.vo.User;

/**
 * Servlet implementation class WXUserLogin
 */
@WebServlet("/WXUserLogin")
public class WXUserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WXUserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        //设置请求编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        /* 设置响应头允许ajax跨域访问 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        User user = new User();
        //获取微信小程序get的参数值并打印
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        System.out.println("username="+user.getUsername()+" ,password="+user.getPassword());
        //转成json数据
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", user);
        result.put("msg", "后台已收到");
        //使用Gson类需要导入gson-2.8.0.jar
        String json = new Gson().toJson(result);

        //返回值给微信小程序
        Writer out = response.getWriter();
        out.write(json);
        out.flush();

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
