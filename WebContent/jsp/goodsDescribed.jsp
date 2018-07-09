<%@ page language="java" pageEncoding="utf-8"%>

<%@ page import="util.*"%>
<%@ page import="vo.Product" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	int gid = Integer.parseInt(request.getParameter("gid"));
	Product p = Product.getProductInfo(gid);
	int number = p.getNum();
	String name = p.getName();
	String described = p.getInformation();
	String producer = p.getOwner();
	Double price = p.getPrice();
%>

<!DOCTYPE html>
<html>
<head>
<title>商品详情页面</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/another_style.css" rel="stylesheet" type="text/css"
	media="all" />
<link rel="stylesheet" href="css/flexslider.css" type="text/css"
	media="screen" />

<link href="css/memenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/imagezoom.js"></script>
<script type="text/javascript" src="js/memenu.js"></script>
<script defer src="js/jquery.flexslider.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".memenu").memenu();
	});
</script>
<script type="text/javascript">
	$(window).load(function() {
		$('.flexslider').flexslider({
			animation : "slide",
			controlNav : "thumbnails"
		});
	});
</script>
<script type="text/javascript">
	$(function() {
		var menu_ul = $('.menu-drop > li > ul'), menu_a = $('.menu-drop > li > a');
		menu_ul.hide();
		menu_a.click(function(e) {
			e.preventDefault();
			if (!$(this).hasClass('active')) {
				menu_a.removeClass('active');
				menu_ul.filter(':visible').slideUp('normal');
				$(this).addClass('active').next().stop(true, true).slideDown(
						'normal');
			} else {
				$(this).removeClass('active');
				$(this).next().stop(true, true).slideUp('normal');
			}
		});

	});
</script>
<script type="text/javascript">
	function editHref() {
		var number = document.getElementById("buyNumber").value;

		if (number >
<%=number%>
	) {
			alert("要购买的数量大于库存，请重新选择");
			return false;
		}
		document.getElementById("carthref").href = document
				.getElementById("carthref").href
				+ number;
	}
</script>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>

	<div class="single">
		<div class="container">
			<!--<div class="col-md-9">-->

			<div class="col-md-5 grid">
				<div class="flexslider">

					<!--
                    	作者：sandysandrawu@163.com
                    	时间：2018-07-05
                    	描述：商品图片加在这里
                    -->

					<img src="<%=p.getPath()%>" data-imagezoom="true"
						class="img-responsive">
				</div>

			</div>
			<!--</div>-->
			<div class="col-md-7 single-top-in">
				<div class="single-para simpleCart_shelfItem">

					<form id = "goods" action="BuyProductServlet" method="post">
						<!--产品名称-->
						<h1><%=name%></h1>
						<!--商品描述-->
						<p><%=described%></p>

						<div class="star-on">
							<div class="review">
								<a>商家：<%=producer%></a> <br> <a>库存：<%=number%></a>
							</div>
							<div class="clearfix"></div>
						</div>
						<label class="add-to item_price"><%=price%>元</label>
						<div class="available">
							<h6>购买数量 :</h6>
							<input name="buyNumber" type="number" min="1" max=<%=number%>
								value="1" id="buyNumber">
						</div>
						<!--a id="carthref"
						href="../jsp/addToCart.jsp?pid=<%=gid%>&buyNumber="
						class="cart item_add" onclick="return editHref()">加入购物车</a-->
						<!-- input type="button" onclick="buyProduct()" value="购买"/-->
						<!-- a id="carthref" href="BuyProductServlet?pid=<%=gid%>&buyNumber="
						class="cart item_add" onclick="return editHref()">购买</a-->
						<input type="hidden" name="pid" value=<%=gid %> >
						<input type="submit" value="购买">
						<input type="button" value="查看评价" onclick="javascript:window.location.href='jsp/itemReview.jsp?pid=<%=gid%>'">

<input type="button" value="查看商家" onclick="viewStore()"></input>

                        <br><br><input type="button" value="加入购物车" onclick="cart()">

						<script type="text/javascript">
                            function cart(){
                                document.forms.goods.action="AddCartServlet";
                                document.forms.goods.submit();
                            }
						</script>
					</form>
			<!--		<div>
					   <br>
						<form action="AddCartServlet" method="post">
							<input type="hidden" name="gid" value=<%=gid%>  >
							<input type="hidden" name="buyNumber" value=<%=number%>  >
							<input type="submit" value="加入购物车" name="commit">
					</div>-->
				</div>
			</div>
		</div>

		<div class="clearfix"></div>

	</div>
	</div>
	<script type="text/javascript">
		function buy() {
			$.ajax({
				data : {
					'buyNumber' : $("#buyNumber").val(),
					'pid' :<%=gid%>
		},
				url : 'BuyProductServlet'
			})
		}
		function viewStore() {
			$.ajax({
				data : {
					'pid' :<%=gid%>
		},
				url : ''
			})
		}
	</script>
</body>
</html>