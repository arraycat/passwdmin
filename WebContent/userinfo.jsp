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
        function info() {
            if (!confirm("关闭浏览器即可自动退出，超时(5min)也会自动退出。")) {
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
<%
	request.setCharacterEncoding("utf-8");
	User u=new User();
	if(request.getSession().getAttribute("u")!=null){
		u=(User)request.getSession().getAttribute("u");
	}
%>
                </div>
                <div class="row">
                    <div class="col-md-6 login-form mx-auto col-xl-4">
                        <!-- form card login -->
                        <div class="card">
                            <div class="card-header">
                                <h3 class="mb-0">用户信息</h3>
                            </div>
                            <div class="card-body">
                                <form action="UserLogin" id="formLogin" method="POST" class="formLogin">
                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <span style="text-align: center"></span>
                                            <span style="text-align: center"></span>
                                            <span style="text-align: justify"></span>
                                            <p class="alert-success" align="center">您的用户名是：<%=u.getUsername() %></p>
                                        </div>
                                        <div class="col-md-12">
                                            <p class="alert-success" align="center">您的邮箱是：<%=u.getEmail() %></p>
                                            <p class="alert-success" align="center">您的容量是：<%=u.getLevel()*20 %></p>
                                            <p class="alert-danger" align="center">
                                                出于安全考虑，即使管理员也无法帮您找回密码！忘记密码只能重置账户，请牢记您的邮箱及密码！</p>
                                        </div>
                                    </div>
                                </form>
                                <div class="naw-text mb-3">
                                    <h5><a href="updateuserpwd.html"><span>修改密码</span></a></h5>
                                </div>
                                <div class="row mb-3">
                                    <div class="col-lg-6"><a href="mypasswd.jsp"
                                            class="btn btn-primary btn-wd fb btn-block"> <span> 查看记录</span></a></div>
                                    <div class="col-lg-6"><a href="#" class="btn btn-info btn-wd gp btn-block"
                                            onclick="return info()"> <span>退出</span></a></div>
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