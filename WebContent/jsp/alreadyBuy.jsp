<%@ page import="java.util.List" %>
<%@ page import="util.Order" %>
<%@ page import="util.Product" %>
<!--

<%@ page language="java" pageEncoding="utf-8"%>

下面这段代码是用来统一路径的，使用后要改下面的link和script引用包的地址，把每个路径前面的../去掉
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
-->

<!DOCTYPE html>
<html>
<head>
<title>已购买</title>
<!--
	<base href="<%=basePath%>">
-->

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="../css/another_style.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="../css/memenu.css" rel="stylesheet" type="text/css" media="all" />
<link href="../css/cartTable.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/responsiveslides.min.js"></script>
<script type="text/javascript" src="../js/memenu.js"></script>
<script type="text/javascript" src="../js/simpleCart.min.js"></script>

</head>
<body>
	<!--插入head-->
	<jsp:include page="head.jsp"></jsp:include>
	<!--显示订单-->
	<center><div class="content">
			<table cellspacing="0" >
				<tr>
					<th colspan="8"><strong>已购买商品</strong></th>
				</tr>
				<tr>
					<th>商品图片</th>
					<th>商品名</th>
					<th>数量</th>
					<th>价格</th>
					<th>总价</th>
					<th>购买时间</th>
					<th>订单状态</th>
					<th>操作</th>
				</tr>
				<!--
					这个是项目原本的代码
				<%
					List<Order> orderList = Order.getOrderList((String)request.getSession().getAttribute("uid"));
					for(Order o:orderList){
					    Product p =Product.getProductInfo(o.getProduct());

				%>
               -->
               
				<tr>
					<td><img src="<%=p.getPath()%>"
						class="img-responsive" alt=""></td>
					<td><%=p.getName()%></td>
					<td><%=o.getQuantity()%></td>
					<td><%=p.getPrice()%></td>
					<td><%=p.getPrice()*o.getQuantity()%></td>
					<td><%=o.getStartTime()%></td>
					<td><a href="jsp/alreadyBuy.jsp?"><%=o.getStates()%></a></td>
					<!--需要代码根据order的id是否已签收和是否已进行过评价判断，如果状态是已签收+未评价才能进行跳转到comment.jsp-->
					<%--暂未实现--%>
					<td><a
						href="jsp/alreadyBuy.jsp?oid=<%=o.getId() %>&pname=<%=p.getName() %>&path=<%p.getPath() %>&pid=<%p.getId() %>"
						onclick="return confirmComment()" disabled="true">评价</a></td>
				</tr>
				<%}%>
			</table>
	</div></center>
</body>
</html>