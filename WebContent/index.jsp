<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="vo.Product" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
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

		<!-- Modernizr JS -->
		<script src="js/modernizr-2.8.3.min.js"></script>

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
								<h1 class="wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.5s">Livework-10 PEN</h1>
								<p class="wow fadeInUp" data-wow-duration="1s" data-wow-delay="1s">粉嫩马卡龙缤纷色彩斜笔头设计,书写笔划可粗可细,一笔多用好方便。 多功能创意设计使用起来好方便,书写/标记/涂鸦,让你灵感创作多多, 好写笔芯让书写多了更多乐趣,让灵感及时被留住。 还可以这样玩→粗笔端那头萤光笔,一样可以碰色,玩双层色书写, 等把碰色颜色写完,又恢复成原本笔自身颜色。
								</p>
								<a href="product-details.html" class="wow fadeInUp" data-wow-duration="1s" data-wow-delay="1.5s">shop now</a>
							</div>
						</div>
					</div>
				</div>
				<!-- Caption 2 -->
				<div id="slider-caption-2" class="nivo-html-caption">
					<div class="container">
						<div class="row">
							<div class="hero-slider-content col-sm-8 col-xs-12">
								<h1 class="wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.5s">Livework-10 PEN</h1>
								<p class="wow fadeInUp" data-wow-duration="1s" data-wow-delay="1s">There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, </p>
								<a href="product-details.html" class="wow fadeInUp" data-wow-duration="1s" data-wow-delay="1.5s">shop now</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END SLIDER SECTION -->

			<!-- PRODUCT SECTION START -->
			<div class="product-section section pt-120 pb-120">
				<div class="container">

					<div class="isotope-grid row">
						<!-- Product Item Start -->
						<%
						List<Product> goodsList=Product.getAllGoodList();
						for(int i=0;i<goodsList.size();i++){
					    %>
						<div class="isotope-item chair home-decor col-lg-3 col-md-4 col-sm-6 col-xs-12 mb-50">
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
										<a href="#" title="Buy"><i class="pe-7s-like"></i>购买</a>
										<a href="jsp/addToCart.jsp?gid=<%=goodsList.get(i).getId()%>&buyNumber=1" title="Add to Cart"><i class="pe-7s-cart"></i>加入购物车</a>
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
						<!-- Product Item End -->
						<%
							}
						%>
				

					</div>

					<div class="row">
            <div class="text-center col-xs-12 mt-30">
                <a href="#" class="btn load-more-product">load more</a>
            </div>
        </div>

				</div>
			</div>
			<!-- PRODUCT SECTION END -->

		</div>
		<!-- Body main wrapper end -->

		<!-- Placed JS at the end of the document so the pages load faster -->

		<!-- jQuery latest version -->
		<script src="js/jquery-3.1.1.min.js"></script>
		<!-- Bootstrap js -->
		<script src="js/bootstrap.min.js"></script>
		<!-- Plugins js -->
		<script src="js/plugins.js"></script>
		<!-- Main js -->
		<script src="js/main.js"></script>

	</body>

</html>