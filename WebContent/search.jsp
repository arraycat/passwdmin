<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.passwdmin.util.Encrypt, java.util.ArrayList"%>
<%@ page import="com.passwdmin.dao.*, com.passwdmin.vo.*"%>

<!DOCTYPE html>
<html lang="zh">

<head>
    <title>小密书</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- description -->
    <meta name="description" content="密码管理">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- Material Design Icons CSS -->
    <link rel="stylesheet" href="css/materialdesignicons.css">
    <!-- Bootstrap Checkbox CSS -->
    <link rel="stylesheet" href="css/awesome-bootstrap-checkbox.css">
    <!-- Rippler CSS -->
    <link rel="stylesheet" href="css/rippler.min.css">
    <!-- Style CSS -->
    <link rel="stylesheet" href="css/style.css">
    <!-- Responsive  CSS -->
    <link rel="stylesheet" href="css/responsive.css">
    <script type="text/javascript">
        function del() {
            if (!confirm("确认要删除？")) {
                window.event.returnValue = false;
            }
        } 
    </script>
</head>

<body>

    <div class="container py-2">
        <div class="row">
            <div class="col-md-12">
                <div class="logo">
                    <a href="#">
                        <h1><br>小密书</h1>
                    </a>
                </div>
                <div class="row">
                    <div class="col-md-6 login-form mx-auto col-xl-7">
                        <!-- form card login -->
                        <div class="card">
                            <div class="card-header">
                                <h3 class="mb-0">搜索结果</h3>
<%
	request.setCharacterEncoding("utf-8");
	
	ArrayList<Passwd> pwdlist=null;
	
	if(session.getAttribute("searchlist")==null){
		out.println("<p>非法访问，请从登录页面进入！</p>");
	}
	else{
		pwdlist=(ArrayList<Passwd>)session.getAttribute("searchlist");
		//此处直接使用mypasswd.jsp中的,只是把pwdlist改为searchlist
%>

                            </div>
                            <div class="card-body">
                                <table width="100%" border="1" align="center" class="alert-success">
                                    <tbody>
                                        <tr>
                                            <th class="alert-dark" scope="col" align="center">账户名称 </th>
                                            <th class="alert-dark" scope="col" align="center"> 账户密码</th>
                                            <th class="alert-dark" scope="col" align="center">账户描述 </th>
                                            <th class="alert-dark" scope="col" align="center">账户类型 </th>
                                            <th class="alert-dark" scope="col" align="center">操作 </th>
                                        </tr>
<%
		for(int i=0;i<pwdlist.size();i++){
			Passwd pwd=pwdlist.get(i);
			if(i%2==1){
%>
                                        <tr>
                                            <td><a
                                                    href="Query?id=<%=pwd.getPid() %>&methodName=<%=2 %>&listid=<%=i%>">点击查看修改</a>
                                            </td>
                                            <td><%="****" %></td>
                                            <td><%=pwd.getAccount_desc() %></td>
                                            <td><%=pwd.getAccount_type() %></td>
                                            <td>
                                                <a href="Query?id=<%=pwd.getPid() %>&methodName=<%=1%>&listid=<%=i%>"
                                                    onclick="return del()">删除</a>
                                            </td>
                                        </tr>
<%
			}
			else{
%>
                                        <tr>
                                            <td class="alert-warning"><a
                                                    href="Query?id=<%=pwd.getPid() %>&methodName=<%=2 %>&listid=<%=i%>">点击查看修改</a>
                                            </td>
                                            <td class="alert-warning"><%="****" %></td>
                                            <td class="alert-warning"><%=pwd.getAccount_desc() %></td>
                                            <td class="alert-warning"><%=pwd.getAccount_type() %></td>
                                            <td class="alert-warning">
                                                <a href="Query?id=<%=pwd.getPid() %>&methodName=<%=1 %>&listid=<%=i%>"
                                                    onclick="return del()">删除</a>
                                            </td>
                                        </tr>
                                        <tr>
<%
			}
		}
	}
%>
                                    </tbody>
                                </table>
                                <p><br>
                                </p>
                                <p>&nbsp;</p>
                                <form action="PwdSearch" id="formSearch" method="GET" class="formLogin">
                                    <div class="form-group">
                                        <input for="search" class="form-control" id="search" name="search"
                                            placeholder="按照描述查找" type="text" required="">
                                        <i class="fa fa-search"></i>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-lg-12 text-center"><button type="submit"
                                                class="btn btn-success hover-btn btn-wd "
                                                id="btnLogin"><span>搜索</span></button></div>
                                    </div>
                                </form>
                                <div class="row mb-3">
                                    <div class="col-md-12">
                                        <div>
                                            <p><br>
                                            </p>
                                            <p>&nbsp;</p>
                                            <p>&nbsp;&nbsp;</p>
                                        </div>
                                        <p class="alert-danger" align="center">
                                            出于安全考虑，即使管理员也无法帮您找回密码！忘记密码只能重置账户，请牢记您的邮箱及密码！</p>
                                    </div>
                                </div>
                                <div class="naw-text mb-3">
                                    <h5>增加账户密码或管理账户</h5>
                                </div>
                                <div class="row mb-3">
                                    <div class="col-lg-6"><a href="addinfo.html"
                                            class="btn btn-primary btn-wd fb btn-block"><span>添加记录</span></a></div>
                                    <div class="col-lg-6"><a href="mypasswd.jsp"
                                            class="btn btn-info btn-wd gp btn-block"><span>返回查看全部记录</span></a></div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-12">
                                        <div class="create-box text-block text-center my-0">
                                            <span class="text-small font-weight-semibold">需要支持？</span>
                                            <a href="#" class="text-black text-small">联系我们</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--/card-block-->
                            <div class="card-footer">
                                <p class="copyright">&copy; 小密书</p>
                            </div>
                        </div>
                        <!-- /form card login -->
                    </div>
                </div>
                <!--/row-->
            </div>
            <!--/col-->
        </div>
        <!--/row-->
    </div>

</body>

</html>