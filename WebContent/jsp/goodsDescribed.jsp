<%@ page language="java" pageEncoding="utf-8"%>

<%@ page import="util.*"%>
<%@ page import="vo.Product"%>
<%@ page import="DAO.ProductDAO" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	int gid = Integer.parseInt(request.getParameter("gid"));
	Product p = ProductDAO.getProductInfo(gid);
	int number = p.getNum();
	String name = p.getName();
	String described = p.getInformation();
	String producer = p.getOwner();
	Double price = p.getPrice();
%>
<!doctype html>
<html class="no-js" lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>商品详情页面</title>
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
<link rel="stylesheet" href="css/product.css">
<!-- Responsive css -->
<link rel="stylesheet" href="css/responsive.css">

<!-- Modernizr JS -->
<%--<script src="js/modernizr-2.8.3.min.js"></script>--%>

</head>

<body>
	<!-- Body main wrapper start -->
	<div class="wrapper">

		<jsp:include page="head.jsp"></jsp:include>

		<!-- PAGE SECTION START -->
		<div class="page-section section pt-120 pb-120">
			<div class="container">
				<div class="row mb-40">
					<!-- Single Product Images -->
					<div class="col-md-5 col-sm-6 col-xs-12 mb-40">
						<!-- Tab panes -->
						<div class="tab-content mb-10">
							<div class="pro-large-img tab-pane active" id="pro-large-img-1">
								<img src="<%=p.getPath()%>">
							</div>

						</div>
					</div>
					<!-- Single Product Thumbnail Slider -->
					<!--<div class="pro-thumb-img-slider">
                    <div><a href="#pro-large-img-1" data-toggle="tab"><img src="img/product/10.jpg" alt="" /></a></div>
                    <div><a href="#pro-large-img-2" data-toggle="tab"><img src="img/product/11.jpg" alt="" /></a></div>
                    <div><a href="#pro-large-img-3" data-toggle="tab"><img src="img/product/12.jpg" alt="" /></a></div>
                    <div><a href="#pro-large-img-4" data-toggle="tab"><img src="img/product/13.jpg" alt="" /></a></div>
                    <div><a href="#pro-large-img-5" data-toggle="tab"><img src="img/product/14.jpg" alt="" /></a></div>
                </div>
            </div>-->
					<!-- Single Product Details -->
					<div class="col-md-7 col-sm-6 col-xs-12 mb-40">
						<div class="product-details section">
							<!-- Title -->
							<h1 class="title"><%=name%></h1>
							<!-- Price Ratting -->
							<div class="price-ratting section">
								<!-- Price -->
								<span class="price float-left"><span class="new">RMB
										<%=price%></span></span>
								<!-- Ratting -->
								<!--<span class="ratting float-right">
                            <i class="fa fa-star active"></i>
                            <i class="fa fa-star active"></i>
                            <i class="fa fa-star active"></i>
                            <i class="fa fa-star active"></i>
                            <i class="fa fa-star active"></i>
                            <span> (01 Customer Review)</span>
                        </span>-->
							</div>
							<!-- Short Description -->
							<div class="short-desc section">
								<h5 class="pd-sub-title">商品描述</h5>
								<p><%=described%>

								</p>
								<a herf="#">商家:<%=producer%></a><br>库存:<%=number%>
							</div>

							<!-- Quantity Cart -->
							<div class="quantity-cart section">
								<form action="" name="product_form">
									<div class="product-quantity">

										<input type="text" value="0" name="buyNumber">

									</div>
									<input type="hidden" name="pid" value=<%=gid%>> <input
										type="button" class="add-to-cart" value="加入购物车"
										onclick="shoppingCart()"> <input type="button"
										class="add-to-cart" value="购买" onclick="buy()">
								</form>

								<a href="jsp/itemReview.jsp?pid=<%=gid%>"><button class="add-to-cart">查看评论</button></a>

							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Body main wrapper end -->

			<!-- Placed JS at the end of the document so the pages load faster -->
			<script>
				var pay = document.querySelector('form[name=product_form]')
				function shoppingCart() {
					document.product_form.action = "AddCartServlet";
					document.product_form.submit();
				}
				function buy() {
					document.product_form.action = "BuyProductServlet";
					document.product_form.submit();
				}
			</script>
			<%--<!-- jQuery latest version -->--%>
			<%--<script src="js/jquery-3.1.1.min.js"></script>--%>
			<%--<!-- Bootstrap js -->--%>
			<%--<script src="js/bootstrap.min.js"></script>--%>
			<%--<!-- Plugins js -->--%>
			<%--<script src="js/plugins.js"></script>--%>
			<%--<!-- Main js -->--%>
			<%--<script src="js/main.js"></script>--%>
</body>

</html>
