<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="vo.Product" %>
<%@ page import="util.Database" %>
<%@ page import="java.util.Random" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
	List<Product> Products= Product.getAllGoodList();
	Random ran = new Random();
	int k = ran.nextInt(Products.size());
	int j = ran.nextInt(Products.size());
	while (j==k){
		j = ran.nextInt(Products.size());
	}
	int x=1;
	int num = 8;
	if (request.getParameter("num")!=null) num = Integer.parseInt(request.getParameter("num"));
	boolean bool = true;
%>
<!DOCTYPE html>

<html class="no-js" lang="en">

	<head>
		<meta charset="utf-8">
		<meta http-equiv="x-ua-compatible" content="ie=edge">
		<title>购物商城首页</title>
		<base href="<%=basePath%>">
		<meta name="description" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<!-- Favicon -->
		<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">

		<!-- All CSS Files -->
		<!-- Bootstrap css -->
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<!-- Icon Font -->
		<link rel="stylesheet" href="css/font-awesome.min.css">
		<link rel="stylesheet" href="css/pe-icon-7-stroke.css">
		<!-- Plugins css file -->
		<link rel="stylesheet" href="css/plugins.css">
		<!-- Theme main style -->
		<link rel="stylesheet" href="css/style_for_index.css">
		<!-- Responsive css -->
		<link rel="stylesheet" href="css/responsive.css">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/jquery.easing.1.3.js"></script>
<script src="js/jquery-3.2.1.min.js"></script>

		<!-- Modernizr JS -->


	</head>

	<body>

<jsp:include page="jsp/head.jsp"></jsp:include>
		<!-- Body main wrapper start -->
		<div class="wrapper">

        
			<!-- START SLIDER SECTION -->
			<div class="home-slider-section section">
				<!-- Home Slider -->
				<div id="home-slider" class="slides">
					<img src="images/banner-1.jpg" alt="" title="#slider-caption-1" />
					<img src="images/banner-magazine.jpg" alt="" title="#slider-caption-2" />
				</div>

				<!-- Caption 1 -->
				<div id="slider-caption-1" class="nivo-html-caption">
					<div class="container">
						<div class="row">
							<div class="hero-slider-content col-sm-8 col-xs-12">
								<h1 class="wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.5s"><%=Products.get(k).getName()%></h1>
								<p class="wow fadeInUp" data-wow-duration="1s" data-wow-delay="1s"><%=Products.get(k).getInformation()%>
								</p>
								<a href="jsp/goodsDescribed.jsp?gid=<%=Products.get(k).getId()%>" class="wow fadeInUp" data-wow-duration="1s" data-wow-delay="1.5s">shop now</a>
							</div>
						</div>
					</div>
				</div>
				<!-- Caption 2 -->
				<div id="slider-caption-2" class="nivo-html-caption">
					<div class="container">
						<div class="row">
							<div class="hero-slider-content col-sm-8 col-xs-12">
								<h1 class="wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.5s"><%=Products.get(j).getName()%></h1>
								<p class="wow fadeInUp" data-wow-duration="1s" data-wow-delay="1s"><%=Products.get(j).getInformation()%> </p>
								<a href="jsp/goodsDescribed.jsp?gid=<%=Products.get(j).getId()%>" class="wow fadeInUp" data-wow-duration="1s" data-wow-delay="1.5s">shop now</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END SLIDER SECTION -->

			<!-- PRODUCT SECTION START -->
			<!--<div class="product-section section pt-120 pb-120">
				<div class="container">

					<div class="isotope-grid row">-->
						<!-- Product Item Start -->
						<div  style="width: 80%;margin-left: 10%;" align="center">
						<%
						List<Product> goodsList=Product.getAllGoodList();
						if (num<goodsList.size()){
						for(int i=0;i<goodsList.size();i++){if(i<8){
					    %>
						<div id=<%=i%> class="isotope-item chair home-decor col-lg-3 col-md-4 col-sm-6 col-xs-12 mb-50" >
							<div class="product-item text-center">
								<!-- Product Image -->
								<div class="product-img">
									<!-- Image -->
									<a class="image" href="jsp/goodsDescribed.jsp?gid=<%=goodsList.get(i).getId()%>">
									<img src="<%=goodsList.get(i).getPath()%>" alt="" /></a>
									<!-- Wishlist Button -->
									<!--<a class="wishlist" href="#" title="Wishlist"><i class="pe-7s-like"></i></a>-->
									<!-- Action Button -->
									<div class="action-btn fix">
										<a href="BuyProductServlet?pid=<%=goodsList.get(i).getId()%>&buyNumber=1" title="Buy"><i class="pe-7s-like"></i>购买</a>
										<a href="AddCartServlet?pid=<%=goodsList.get(i).getId()%>&buyNumber=1" title="Add to Cart"><i class="pe-7s-cart"></i>加入购物车</a>
									</div>
								</div>
								<!-- Portfolio Info -->
								<div class="product-info text-left">
									<!-- Title -->
									<h5 class="title"><a href="jsp/goodsDescribed.jsp?gid=<%=goodsList.get(i).getId()%>"><%=goodsList.get(i).getName()%></a></h5>
									<!-- Price Ratting -->
									<div class="price-ratting fix">
										<span class="price float-left"><span class="new">RMB <%=goodsList.get(i).getPrice()%></span></span>
										<span class="ratting float-right">
                                <i class="fa fa-star active"></i>
                                <i class="fa fa-star active"></i>
                                <i class="fa fa-star active"></i>
                                <i class="fa fa-star active"></i>
                                <i class="fa fa-star active"></i>
                            </span>
									</div>
								</div>
							</div>
						</div>
						<%}else{%>
							<div id=<%=i%> class="isotope-item chair home-decor col-lg-3 col-md-4 col-sm-6 col-xs-12 mb-50" style="display:none" >
							<div class="product-item text-center">
								<!-- Product Image -->
								<div class="product-img">
									<!-- Image -->
									<a class="image" href="jsp/goodsDescribed.jsp?gid=<%=goodsList.get(i).getId()%>">
									<img src="<%=goodsList.get(i).getPath()%>" alt="" /></a>
									<!-- Wishlist Button -->
									<!--<a class="wishlist" href="#" title="Wishlist"><i class="pe-7s-like"></i></a>-->
									<!-- Action Button -->
									<div class="action-btn fix">
										<a href="BuyProductServlet?pid=<%=goodsList.get(i).getId()%>&buyNumber=1" title="Buy"><i class="pe-7s-like"></i>购买</a>
										<a href="AddCartServlet?pid=<%=goodsList.get(i).getId()%>&buyNumber=1" title="Add to Cart"><i class="pe-7s-cart"></i>加入购物车</a>
									</div>
								</div>
								<!-- Portfolio Info -->
								<div class="product-info text-left">
									<!-- Title -->
									<h5 class="title"><a href="jsp/goodsDescribed.jsp?gid=<%=goodsList.get(i).getId()%>"><%=goodsList.get(i).getName()%></a></h5>
									<!-- Price Ratting -->
									<div class="price-ratting fix">
										<span class="price float-left"><span class="new">RMB <%=goodsList.get(i).getPrice()%></span></span>
										<span class="ratting float-right">
                                <i class="fa fa-star active"></i>
                                <i class="fa fa-star active"></i>
                                <i class="fa fa-star active"></i>
                                <i class="fa fa-star active"></i>
                                <i class="fa fa-star active"></i>
                            </span>
									</div>
								</div>
							</div>
						</div>
						<%}%>
						
						<!-- Product Item End -->

						<%
							}
							}
						%>
					</div>
					<div class="row">						
            <div class="text-center col-xs-12 mt-30">
                <button id="loadmore" class="btn load-more-product">load more</button>
            </div>
						
        </div>
</div>
				<!--</div>
			</div>
			

		</div>-->
		
		<!-- Body main wrapper end -->

		<!-- Placed JS at the end of the document so the pages load faster -->

		<!-- jQuery latest version -->
		<script src="js/jquery-3.1.1.min.js"></script>
		<!-- Bootstrap js -->
		<%--<script src="js/bootstrap.min.js"></script>--%>
		<!-- Plugins js -->
		<script src="js/plugins.js"></script>
		<!-- Main js -->
		<script src="js/main.js"></script>

<script> 
	var j=8;
$("#loadmore").click(function(){
//	$("#"+j).show();
//	if(j%3==2){
//		var header1 = document.getElementById(j); 
//var p = document.createElement("a");// 创建一个元素节点
//var para =document.createTextNode("???????");
//p.appendChild(para);
//insertAfter(p,header1);
//	}
//	j+=1;
	var m=j;
	for (j;j<m+8;j++) {
		
$("#"+j).show(500);	
	}
	
	
});
</script>
	</body>

</html>