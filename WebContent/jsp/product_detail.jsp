<%@ page language="java" pageEncoding="utf-8"%>



<%@ page import="util.*"%>

<%@ page import="vo.Product" %>
<%@ page import="java.util.List" %>
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

	List<Product> goodsList = ProductDAO.searchProduct(p.getName());

	List<Product> products = ProductDAO.getAllGoodList();

	goodsList.addAll(products);

	int gids[]=new int[5],j;
    gids[0]=gid;
%>

<!doctype html>

<html class="no-js" lang="en">



<head>

	<meta charset="utf-8">

	<meta http-equiv="x-ua-compatible" content="ie=edge">

	<title>商品详情页面</title>

	<%--<base href="<%=basePath%>">--%>

	<meta name="description" content="">

	<meta name="viewport" content="width=device-width, initial-scale=1">



	<!-- Favicon -->

	<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">



	<!-- All CSS Files -->



	<!-- Bootstrap css -->

	<link rel="stylesheet" href="../css/bootstrap.min.css">

	<!-- Icon Font -->

	<link rel="stylesheet" href="../css/font-awesome.min.css">

	<link rel="stylesheet" href="../css/pe-icon-7-stroke.css">

	<!-- Plugins css file -->

	<link rel="stylesheet" href="../css/plugins.css">

	<!-- Theme main style -->

	<link rel="stylesheet" href="../css/product.css">

	<!-- Responsive css -->

	<link rel="stylesheet" href="../css/responsive.css">

	<!--<link rel="stylesheet" href="../css/product_chat.css">-->



	<!-- Modernizr JS -->

	<script src="../js/modernizr-2.8.3.min.js"></script>

	<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>

	<script type="text/javascript" src="../js/startScore.js"></script>



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

							<span class="price float-left"><span class="new">RMB <%=price%></span></span>

							<!-- 评分 -->

							<span class="ratting float-right">

                            <ul class="show_number clearfix">

                            <li>

                            <div class="atar_Show">

                            <p tip="<%=p.getScore()%>"></p>

                            </div>

                            <span></span>

                            </li>



                            </ul>





									</span>

						</div>

						<!-- Short Description -->

						<div class="short-desc section">

							<h5 class="pd-sub-title">商品描述</h5>

							<p>

								<%=described%>



							</p>

							<!--商家-->

							<div class="shop section">

								<h5 class="pd-sub-title">商家</h5>

								<a href="ShowStoreServlet?pid=<%=gid%>">

									<%=producer%>

								</a>

							</div>



							<br>库存:

							<%=number%>

							<br>商品类型:

							<%=p.getType()%>



						</div>



						<form action="AddCartServlet" method="post" name = "productbuying">

							<!-- Quantity Cart -->

							<div class="quantity-cart section">



								<div class="product-quantity">



									<input type="text" value="0" name = "buyNumber">



								</div>



								<input type="hidden" name="pid" value=<%=gid %> >
								<%
									String type = (String) request.getSession().getAttribute("type");
									if (type != null && type.equals("0")) {
								%>
								<input type="submit" class="add-to-cart" value="加入购物车">

								<input type="button" class="add-to-cart" value="购买" onclick="buy()">

								<%
									}
								%>







								<input type="button" class="add-to-cart" value="查看评论" onclick="comment()">



							</div>

						</form>

						<script>

                            function buy() {

                                document.productbuying.action = "BuyProductServlet";

                                document.productbuying.submit();

                            }

                            function comment() {

                                document.productbuying.action = "jsp/itemReview.jsp?pid=<%=gid%>";

                                document.productbuying.submit();

                            }

						</script>



					</div>

				</div>



				<!--商品推荐栏-->



				<div class="row">

					<!-- Nav tabs -->

					<div class="col-xs-12">

						<ul class="pro-info-tab-list section">

							<li class="active">

								<a href="#more-info" data-toggle="tab">Related Products</a>

							</li>

							<!--<li>

                                <a href="#data-sheet" data-toggle="tab">Data sheet</a>

                            </li>-->

							<!--<li>

                                <a href="#reviews" data-toggle="tab">Reviews</a>

                            </li>-->

						</ul>

					</div>

					<!-- Tab panes -->

					<div class="tab-content col-xs-12">

						<div class="pro-info-tab tab-pane active" id="more-info">

							<p> </p>

						</div>



					</div>

				</div>

			</div>

		</div>

		<!-- PAGE SECTION END -->







		<!-- PRODUCT SECTION START -->



		<div class="product-section section pb-120">

			<div class="container">

				<!--<div class="row">

                    <div class="section-title text-center col-xs-12 mb-60">

                        <h2></h2>

                    </div>

                </div>-->

				<div class="row">

					<div class="product-slider product-slider-4">


                     <%
						 j=1;
						 for (int i=0;i<goodsList.size();i++){
						   boolean bool = true;
						   for (int k=0;k<j;k++){
						       if (gids[k] == goodsList.get(i).getId()) {
						           bool =false;
						           break;
							   }
						   }
						   if (bool == true){
						    gids[j] = goodsList.get(i).getId();
						    j++;
					 %>
						<!--商品推荐的图放下面-->

						<!-- Product Item Start -->

						<div class="col-xs-12">

							<div class="product-item text-center">

								<div class="product-img">

									<a class="image" href="jsp/product_detail.jsp?gid=<%=goodsList.get(i).getId()%>"><img src="<%=goodsList.get(i).getPath()%>" alt="" /></a>



									<div class="action-btn fix">

										<a href="BuyProductServlet?pid=<%=goodsList.get(i).getId()%>&buyNumber=1" title="Buy"><i class="pe-7s-like"></i>购买</a>

										<a href="AddCartServlet?pid=<%=goodsList.get(i).getId()%>&buyNumber=1" title="Add to Cart"><i class="pe-7s-cart"></i>加入购物车</a>

									</div>

								</div>

								<div class="product-info text-left">

									<h5 class="title"><a href="jsp/product_detail.jsp?gid=<%=goodsList.get(i).getId()%>"><%=goodsList.get(i).getName()%></a></h5>

									<div class="price-ratting fix">

										<span class="price float-left"><span class="new"><%=goodsList.get(i).getPrice()%></span></span>

										<span class="ratting float-right">
                                <ul class="show_number clearfix">
                            <li>
        <div class="atar_Show">
          <p tip="<%=goodsList.get(i).getScore()%>"></p>
        </div>
        <!--<span></span>-->
       </li>
      
                            </ul>
                            </span>

									</div>

								</div>

							</div>

						</div>


                      <%
						  }
						  if (j>4) break;
						  }
					  %>


					</div>

				</div>

			</div>

		</div>

		<!-- PRODUCT SECTION END -->





	</div>



</div>



<div class="g-sidetab">

	<div>

		<a href="jsp/chat.jsp?to=<%=p.getOwner()%>" target="_blank">联系客服</a>

	</div>

</div>

<!-- Body main wrapper end -->



<!-- Placed JS at the end of the document so the pages load faster -->

<script>

    //显示分数

    $(".show_number li p").each(function(index, element) {

        var num=$(this).attr("tip");

        var www=num*2*16;//

        $(this).css("width",www);

        $(this).parent(".atar_Show").siblings("span").text(num+"分");

    });

</script>

<!-- jQuery latest version -->



<!-- Bootstrap js -->

<%--<script src="js/bootstrap.min.js"></script>--%>

<!-- Plugins js -->



<!-- Main js -->

<script src="js/main.js"></script>



</body>



</html>
