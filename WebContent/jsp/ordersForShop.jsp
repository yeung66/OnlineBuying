<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="vo.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.sql.*"%>
<!--
下面这段代码是用来统一路径的，使用后要改下面的link和script引用包的地址，把每个路径前面的../去掉
<%String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String shopID = (String) request.getSession().getAttribute("uid");
	List<Order> olist = Order.getShopOrderList(shopID);%>
-->

<!DOCTYPE html>
<html>
<head>
	<title>查看订单</title>
	<!--
	<base href="<%=basePath%>">
-->

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="../css/bootstrap.css" rel="stylesheet" type="text/css"
		  media="all" />
	<link href="../css/another_style.css" rel="stylesheet" type="text/css"
		  media="all" />
	<link href="../css/memenu.css" rel="stylesheet" type="text/css"
		  media="all" />
	<link href="../css/cartTable.css" rel="stylesheet" type="text/css"
		  media="all" />
	<link href="../css/popup.css" rel="stylesheet" type="text/css"
		  media="all" />
	<link rel="stylesheet"
		  href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<%--<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>--%>
	<%--<script--%>
			<%--src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
	<%--<script type="text/javascript" src="../js/jquery.min.js"></script>--%>
	<script type="text/javascript" src="../js/responsiveslides.min.js"></script>
	<script type="text/javascript" src="../js/memenu.js"></script>

</head>
<body>
<!--插入head-->
<jsp:include page="head.jsp"></jsp:include>
<div class="popup" id="popup">
	<div class="popup-inner">
		<div class="popup__text">
			<!-- h1>修改订单状态</h1 -->
			<div class="radioCon">
				<!-- div class="radioText">状态修改为：</div -->
				<div class="radioBlock">
					<%
						String status = request.getParameter("status");
						if (status!=null){
						%>alert(<%=status%>)<%
						if (status.equals("未发货")) {
					%>
					<div class="radio">
						<label><input type="radio" name="op" id="state-0"
									  value="fa" checked>发货</label>
					</div>
					<div class="radio">
						<label><input type="radio" name="op" id="state-2"
									  value="qv">取消订单</label>
					</div>
					<%
					} else if (status.equals("退货中")) {
					%>
					<div class="radio">
						<label><input type="radio" name="op" id="state-1"
									  value="yi">确认退货</label>
					</div>
					<%
						}
					%>
				</div>

			</div>
		</div>
		<a class="popup__close" href="jsp/ordersForShop.jsp#">X</a>
		<div class="submitChoice">
			<input type="button" value="提交修改" class="blackButton" onclick="tijiao()"></input>
		</div>
	</div>	<%
	}
%>
</div>
<script>
    function tijiao() {
        var operation = $('input:radio[name="op"]:checked').val();
        $("#operation").val(operation);
        window.location.href="../MerchantAlterOrder?operation="+operation+"&"+window.location.search;
    }
</script>
<!--显示订单-->
<center>
	<div class="content">
		<table cellspacing="0">
			<tr>
				<th style="padding: 20px; font-size: 14px; font-weight: 600;"
					colspan="8">显示订单</th>
			</tr>
			<tr>
				<th>商品图片</th>
				<th>商品名</th>
				<th>购买用户</th>
				<th>数量</th>
				<th>价格</th>
				<th>总价</th>
				<th>购买时间</th>
				<th>订单状态</th>
			</tr>
			<%
				for (Order o : olist) {
						int pid = o.getProduct();
						Product p = Product.getProductInfo(pid);
						String imgPath = p.getPath();
						String name = p.getName();
						String purchaser = o.getPurchaser();
						int num = o.getQuantity();
						Double price = p.getPrice();
						Date date = o.getStartTime();
						String statu = o.getStatus();
			%>
			<tr>
				<td><img src="<%=imgPath%>" width="70px" height="70px"></td>
				<!--这里添加一下商品详情页的链接 href-->
				<td><a href="goodsDescribed.jsp?gid=<%=pid%>"><%=name%></a></td>
				<td><%=purchaser%></td>
				<td><%=num%></td>
				<td><%=price%></td>
				<td><%=num * price%></td>
				<td><%=date%></td>
				<td><a href="jsp/ordersForShop.jsp#popup?status=<%=statu%>&id=<%=o.getId()%>" class="stateButton"><%=statu%></a></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</center>

</body>
</html>