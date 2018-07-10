<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%String uid =(String)session.getAttribute("uid"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Head</title>
        <base href="<%=basePath%>">
        <!-- Bootstrap css -->
		<link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- Icon Font -->
		<link rel="stylesheet" href="css/font-awesome.min.css">
		<link rel="stylesheet" href="css/pe-icon-7-stroke.css">
        <link rel="stylesheet" href="css/style_for_index.css">
        <!-- Responsive css -->
		<link rel="stylesheet" href="css/responsive.css">
		<!-- Modernizr JS -->
		<script src="js/modernizr-2.8.3.min.js"></script>
			<!-- jQuery latest version -->
		<script src="js/jquery-3.1.1.min.js"></script>
		<!-- Bootstrap js -->
		<script src="js/bootstrap.min.js"></script>
		<!-- Plugins js -->
		<script src="js/plugins.js"></script>
		<!-- Main js -->
		<script src="js/main.js"></script>
		<style>#sachCon {
	margin-left: 100px;
}

#search {
	border: none;
	background-color: lightgoldenrodyellow;
	position: relative;
	top: -17px;
	left: 0px;
	outline: medium;
}

#searchbtn {
	position: relative;
	top: -6px;
	left: 0px;
	outline: medium;
}</style>
    </head>
    <body>
    	
    	<!-- START HEADER SECTION -->
			<header class="header-section section sticker">
				<div class="container">
					<div class="row">
						<div class="col-xs-12">
							
							<!-- 左上角logo -->
							<div class="header-logo float-left">
								<a href="index.jsp"><img src="images/logo.png" alt="main logo"></a>
							</div >
							<div class="header-logo float-left" id="sachCon" style="display: none;position: relative;
								margin-top: 20px;">
											<form action="SearchServlet" method="post" style="display:inline;">
			<input type="text" id="search" name="search" placeholder="搜索你感兴趣的" 
				style="width: 250px; height: 40px" /> 
				<input type="image" src="images/search.jpg" id="searchbtn"
				 style="width: 35px; height: 35px" id="go" 
				title="Search" />
</form>
										</div>

							<!-- header-search & total-cart -->
							
							<div class="float-right">
								<div class="header-option-btns float-right">
									<!-- header-搜索 -->
									<div class="header-search float-left">
										<button id="searchBtn1" class="search-toggle" data-toggle="dropdown"><i class="pe-7s-search"></i></button>
										
									</div>
									<%if(!(uid==null)){%>
									<!-- header 讯息 -->
									<div class="header-account float-left">
										<ul>
											<li>
												<a href="#" data-toggle="dropdown"><i class="pe-7s-mail"></i></a>
												<!--<ul class="dropdown-menu">
													<li>
														<a href="login.html">Log in</a>
													</li>
													<li>
														<a href="register.html">Register</a>
													</li>
													<li>
														<a href="#">My Account</a>
													</li>
													<li>
														<a href="wishlist.html">Wish list</a>
													</li>
													<li>
														<a href="checkout.html">Checkout</a>
													</li>
												</ul>-->
											</li>
										</ul>
									</div>
									<!-- Header Cart -->
									<div class="header-cart float-left">
										<!-- Cart Toggle -->
										<a class="cart-toggle" href="#" data-toggle="dropdown">
											<i class="pe-7s-cart"></i>
											<!--
                                            	显示购物车里有多少个商品
                                            -->
											<span>2</span>
										</a>
										<!-- Mini Cart Brief -->
										<div class="mini-cart-brief dropdown-menu text-left">
											<!-- Cart Products -->
											<div class="all-cart-product clearfix">
												<div class="single-cart clearfix">
													
													<!--放入购物车中的商品-->
													<div class="cart-image">
														<a href="product-details.html"><img src="images/product/cart-1.jpg" alt=""></a>
													</div>
													<div class="cart-info">
														<h5><a href="product-details.html">椅子</a></h5>
														<p>1 x RMB 50.0</p>
														<!--<a href="#" class="cart-delete" title="Remove this item"><i class="pe-7s-trash"></i></a>-->
													</div>
												</div>
												
											</div>
											<!-- 购物车总价 -->
											<div class="cart-totals">
												<h5>Total <span>RMB 50.0</span></h5>
											</div>
											<!-- Cart Button -->
											<div class="cart-bottom  clearfix">
												<a href="shoppingCart.jsp">查看购物车</a>
											</div>
										</div>
									</div>
								<%}%>
								</div>
								<nav class="main-menu menu-right float-right">
									<ul>
										<%if(uid==null){%>
											<li class="active">
                                        <a href="login_registe.jsp">登陆/注册</a>
                                        </li>
                                        <%}else if(uid=="0"){%>
										<li class="active">
											<a href="index.jsp">首页</a>
						
										</li>
										<li class="active">
											<a href="perInfo.jsp">个人信息</a>
						
										</li>
										<li class="active">
											<a href="#">查询订单</a>
						
										</li>
										<%}else{%>
											<li class="active">
											<a href="index.jsp">首页</a>
						
										</li>
										<li class="active">
											<a href="perInfo.jsp">个人信息</a>
						
										</li>
										<li class="active">
											<a href="#">查询订单</a>
						
										</li>
										<li class="active">
											<a href="#">查看商品</a>
						
										</li>
										<li class="active">
											<a href="#">添加商品</a>
						
										</li>
										<li class="active">
											<a href="#">修改订单</a>
						
										</li>
										<%}%>
										
										<!--sub-menu在这里-->
										<!--<li>
											<a href="#">Pages</a>
											<ul class="sub-menu">
												<li>
													<a href="cart.html">cart</a>
												</li>
												<li>
													<a href="checkout.html">checkout</a>
												</li>
												<li>
													<a href="login.html">login</a>
												</li>
												<li>
													<a href="register.html">register</a>
												</li>
												<li>
													<a href="wishlist.html">wishlist</a>
												</li>
											</ul>
										</li>-->
										
										
									</ul>
								</nav>
							</div>
							<div class="mobile-menu"></div>
						</div>
					</div>
					
				</div>
			
			</header>
			<!-- END HEADER SECTION -->
			<script>
				$("#searchBtn1").click(function(){
					if(document.getElementById("sachCon").style.display=="none")
					{
					$("#sachCon").show(5);
					}
					else{
						$("#sachCon").hide(5);
					}
				});
			</script>
		
			
 	</body>
</html>