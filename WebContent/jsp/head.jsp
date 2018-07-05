<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<%--<%--%>
	<%--String uname = (String) session.getAttribute("uname");--%>
	<%--String uid = String.valueOf(session.getAttribute("uid"));--%>
<%--%>--%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/another_style.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/memenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/responsiveslides.min.js"></script>
<script type="text/javascript" src="js/memenu.js"></script>
<script>
	$(document).ready(function() {
		$(".memenu").memenu();
	});
</script>
<script type="text/javascript">
	function key() {
		return confirm("确定退出吗？");
	}
</script>
<script>
	function showtime() {
		var myDate = new Date();
		document.getElementById("time").innerHTML = myDate.getHours() + "点"
				+ myDate.getMinutes() + "分" + myDate.getSeconds() + "秒";
		setTimeout("showtime()", 1000);
	}
</script>
</head>
<body>
<!--header-->
<div class="header">
	<div class="header-top">
		<div class="container">
			<div class="col-sm-4 world">
				<ul>

				</ul>
			</div>
			<div class="col-sm-4 logo">
				<img src="images/logo.png" alt="">
			</div>
			<div class="col-sm-4 header-left">
				<p class="log">


					<a href=\"jsp/showMessage.jsp\" target=\"_blank\">个人信息</a>
					<a href=\"servlet/LogoutServlet\" onClick=\"return key()\">退出 </a>

					<a href=\"jsp/login.jsp\">登录</a>

					<a href=\"jsp/register.jsp\">注册</a>



				</p>
				<!--<div class="cart box_1">
                    <a href="jsp/shoppingCart.jsp"> <img src="images/cart.png"
                        alt="" />
                        <h3>购物车</h3>
                    </a>
                </div>-->
			</div>
		</div>
	</div>
	<div class="container">
		<div class="head-top">
			<div class="col-sm-8 h_menu4">
				<ul class="memenu skyblue" id="menu">
					<li class=" grid"><a href="index.jsp">首页</a></li>
					<li><a href="jsp/shoppingCart.jsp">商品</a></li>
					<li><a href="jsp/shoppingCart.jsp">购物车</a></li>
					<li><a href="jsp/alreadyBuy.jsp">已购买</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>

</body>
</html>