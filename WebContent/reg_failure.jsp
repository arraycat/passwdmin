<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.passwdmin.util.Encrypt, java.util.ArrayList"%>
<%@ page import="com.passwdmin.dao.*, com.passwdmin.vo.*"%>

<!DOCTYPE html>
<html lang="zh">

<head>
    <title>小密书注册失败</title>
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
                    <div class="col-md-6 login-form mx-auto col-xl-4">
                        <!-- form card login -->
                        <div class="card">
                            <div class="card-header">
                                <h3 class="mb-0">抱歉！注册失败</h3>
                            </div>
                            <div class="card-body">
                                <form action="UserLogin" id="formLogin" method="POST" class="formLogin">
                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <span style="text-align: center"></span>
                                            <span style="text-align: center"></span>
                                            <span style="text-align: justify"></span>
                                <%
				    				String username="";
				    				String email="";
				    				ArrayList<String> reginfo=null;
				    				
				    				if(request.getAttribute("username")!=null){
				    					username=request.getAttribute("username").toString();
				    				}
				    				if(request.getAttribute("email")!=null){
				    					email=request.getAttribute("email").toString();
				    				}
				    					reginfo=(ArrayList<String>)request.getAttribute("reginfo");
				    				%>
                                            <p class="alert-info" align="center">您刚才注册的用户名是：<%=username %></p>
                                        </div>
                                        <div class="col-md-12">
                                            <p class="alert-info" align="center">您刚才注册的邮箱是：<%=email %></p>
                                    <%
									  	if(reginfo!=null){
									  		for(int i=0;i<reginfo.size();i++){
												String info=reginfo.get(i);
									  	%>
                                            <p class="alert-info" align="center">
                                                <%="（"+(i+1)+"/"+reginfo.size()+"）"+info  %></p>
                                    <%
									  		}
									  	}
									%>
                                            <p class="alert-danger" align="center">
                                                出于安全考虑，即使管理员也无法帮您找回密码！忘记密码只能重置账户，请牢记您的邮箱及密码！</p>
                                        </div>
                                    </div>
                                </form>
                                <div class="naw-text mb-3">
                                    <h5>重新注册或退出？</h5>
                                </div>
                                <div class="row mb-3">
                                    <div class="col-lg-6"><a href="reg.html"
                                            class="btn btn-primary btn-wd fb btn-block"> <span> 重新注册</span></a></div>
                                    <div class="col-lg-6"><a href="javascript:self.close()"
                                            class="btn btn-info btn-wd gp btn-block"> <span> 退出</span></a></div>
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