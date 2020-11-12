<%@page import="com.passwdmin.util.IDTrans"%>
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
                                <h3 class="mb-0">查看/修改</h3>
                            </div>

                            <%	request.setCharacterEncoding("utf-8");
                                //用于显示详情以及提交修改的表单
                                Passwd pwd = new Passwd();
                                ArrayList<Passwd> pwdlist=null;
                                if(session.getAttribute("pwdlist")!=null){
                                    pwdlist=(ArrayList<Passwd>)session.getAttribute("pwdlist");
                                }
                                String id="";//对应数据库中的pid
                                String list_id="";//对应ArrayList中的id
                                if(request.getParameter("id")!=null){
                                    id=request.getParameter("id");
                                }
                                if(request.getParameter("listid")!=null){
                                    list_id=request.getParameter("listid");
                                }
                                int pid=0;
                                int listid=-1;
                                pid=Integer.parseInt(id);
                                listid=IDTrans.dbId2ListId(pid, pwdlist);
                                
                                pwd=pwdlist.get(listid);
                                request.getSession().setAttribute("infoalterlistid", listid);
                                //用于在ServletInfoAlter中处理pwdlist
                            %>
                            <div class="card-body">
                                <form action="InfoAlter" id="formLogin" method="POST" class="formLogin">
                                    <div class="form-group">
                                        <input class="form-control" id="accountname" name="accountname"
                                            value="<%=pwd.getAccount_name() %>" type="text" autocomplete="off">
                                        <i class="fa fa-info"></i>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" value="<%=pwd.getAccount_passwd() %>" class="form-control"
                                            name="accountpassword" id="accountpassword" autocomplete="off">
                                        <i class="fa fa-info"></i>
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" id="accountdesc" name="accountdesc"
                                            value="<%=pwd.getAccount_desc() %>" type="text" autocomplete="off">
                                        <i class="fa fa-info"></i>
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" id="accounttype" name="accounttype"
                                            value="<%=pwd.getAccount_type() %>" type="text">
                                        <i class="fa fa-info"></i><i class="fa fa-info"></i>
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" id="updatetime" name="updatetime"
                                            value="<%="修改时间："+pwd.getUpdate_time() %>" readonly="readonly" type="text">
                                        <i class="fa fa-info"></i><i class="fa fa-info"></i>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-12"> </div>
                                        <div class="col-md-12">&nbsp;</div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-lg-12 text-center"><button type="submit"
                                                class="btn btn-success hover-btn btn-wd "
                                                id="btnLogin"><span>修改</span></button></div>
                                    </div>
                                </form>
                                <div class="naw-text mb-3"> </div>
                                <div class="row mb-3">
                                    <div class="col-lg-6"><a href="mypasswd.jsp"
                                            class="btn btn-primary btn-wd fb btn-block"> <span> 返回首页</span></a></div>
                                    <div class="col-lg-6"><a href="mypasswd.jsp"
                                            class="btn btn-info btn-wd gp btn-block"><span>取消修改</span></a></div>
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