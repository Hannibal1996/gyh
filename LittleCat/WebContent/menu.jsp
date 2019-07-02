<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>小天猫</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
 
    <script src="js/acommon.js"></script>
  </head>
  <script type="text/javascript">
  
  findByName=function(){
		var pname = $("input[name=pname]").val();  
		sessionStorage.removeItem("pname")
		sessionStorage.setItem("pname", pname); 
		window.location.href="serchProduct.jsp"
		
  }
  </script>
  <body>
    <div class="container ">
	<div class="row clearfix">
		<div class="col-md-12 column">
		
			<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="index.jsp">首页</a>
					 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="index.jsp"><span class="glyphicon glyphicon-piggy-bank
" aria-hidden="true"></span></a>
				</div>
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    
					<ul class="nav navbar-nav">
                        
                        
                        
						<li class="active">
							<c:choose>
							<c:when test="${sessionScope.user ==null }">
							 <a href="signin.jsp">请登录</a>
							 </c:when>
							 <c:otherwise>
							 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
							 <a href="#"><span>${sessionScope.user.name} </span></a>
							 </c:otherwise>
							 </c:choose>
						</li>
						
						<li>
						<c:choose>
							<c:when test="${sessionScope.user==null }">
							 <a href="signup.jsp">注册</a>
							 </c:when>
							 <c:otherwise>
							 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
							 <a href="signout.jsp"  onclick="logout();"><span>退出 </span></a>
							 </c:otherwise>
							 </c:choose>
						</li>
					</ul>
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand"> <span class="glyphicon glyphicon-search" aria-hidden="true"></span></a>
                    
					<form class="navbar-form navbar-left" role="search" action="" method="post">
						<div class="form-group">
							<input type="text" name="pname" class="form-control" placeholder="搜索商品"/>
						</div> <button type="button" onclick="findByName();" class="btn btn-default">搜索</button>
					</form>
					<ul class="nav navbar-nav navbar-right">
					<li>
							 <a href="ShoppingCart.jsp">购物车</a>
						</li>
						<li>
							 <a href="shopcar/findAllOrders#all">我的订单</a>
						</li>
							 	<li>
							 <a href=" historyPage.jsp ">历史记录</a>
						</li>
							 
						 
						
					   <!--  <li class="dropdown">
							 <a href="#" class="dropdown-toggle" data-toggle="dropdown">商品分类<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <a href="#">家具</a>
								</li>
								<li>
									 <a href="#">电器</a>
								</li>
								<li>
									 <a href="#">空调</a>
								</li>
                                <li>
									 <a href="#">电子</a>
								</li>
								<li class="divider">
								</li>
								<li>
									 <a href="#">生活用品</a>
								</li>
							</ul>
						</li> -->
					</ul>
				</div>
			</nav>
		</div>
	</div>

</div>
  </body>
</html>
