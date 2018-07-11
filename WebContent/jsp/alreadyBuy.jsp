<%@ page import="java.util.List" %>
<%@ page import="vo.Order" %>
<%@ page import="vo.Product" %>

<%@ page language="java" pageEncoding="utf-8"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>


<!DOCTYPE html>
<html>
<head>
<title>已购买</title>
<!--
	<base href="<%=basePath%>">
-->

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="../css/another_style.css" rel="stylesheet" type="text/css" media="all" />
<link href="../css/memenu.css" rel="stylesheet" type="text/css" media="all" />
<link href="../css/cartTable.css" rel="stylesheet" type="text/css" media="all" />
<link href="../css/popup.css" rel="stylesheet" type="text/css" media="all"/>
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">  
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/responsiveslides.min.js"></script>
<script type="text/javascript" src="../js/memenu.js"></script>
	<script src="../js/jquery-3.1.1.min.js">
<script>
	
</script>
</head>
<body>
	<!--插入head-->
	<jsp:include page="head.jsp"></jsp:include>
	<!--显示订单-->
	<center><div class="content">
			<table cellspacing="0" >
				<tr>
					<th style="padding:20px;font-size: 14px;font-weight: 600;" colspan="8">已购买商品</th>
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

				<%
					List<Order> orderList = Order.getOrderList((String)request.getSession().getAttribute("uid"));
					if(orderList!=null){
						for(Order o:orderList){
							Product p =Product.getProductInfo(o.getProduct());

				%>
				<tr>
					<td><img src="<%=p.getPath()%>"
							 width="70px" height="70px"></td>
					<td><a href="jsp/goodsDescribe.jsp?gid=<%=p.getId()%>"><%=p.getName()%></a></td>
					<td><%=o.getQuantity()%></td>
					<td><%=p.getPrice()%></td>
					<td><%=p.getPrice()*o.getQuantity()%></td>
					<td><%=o.getStartTime()%></td>
					<td><a href="jsp/alreadyBuy.jsp#popup" class="stateButton"><%=o.getStates()%></a></td>
					<!--需要代码根据order的id是否已签收和是否已进行过评价判断，如果状态是已签收+未评价才能进行跳转到comment.jsp-->
					<%--暂未实现--%>
					<td><a

						href="jsp/comment.jsp?oid=<%=o.getId() %>&pname=<%=p.getName() %>&path=<%=p.getPath() %>&pid=<%=p.getId() %>">评价</a></td>
					<!--
						判断，如果不能评价：
						<td>无可进行的操作</td>
                    -->
				</tr>
				<%}}else {

				}%>
			</table>
	</div></center>
	<div class="popup" id="popup">
		<div class="popup-inner">
			<div class="popup__text">
				<h1>修改订单状态</h1>
				<div class="radioCon">
					<div class="radioText">状态修改为：</div>
					<div class="radioBlock">
						<div class="radio">
							<label><input type="radio"  name="optionsRadios" id="state-0" value="state-0" checked>已发货</label>
						</div>
						<div class="radio">
							<label><input type="radio"  name="optionsRadios" id="state-1" value="state-1">确认收货</label>
						</div>
						<div class="radio">
							<label><input type="radio"  name="optionsRadios" id="state-2" value="state-2">退货</label>
						</div>
						<div class="radio">
							<label><input type="radio"  name="optionsRadios" id="state-3" value="state-3">取消订单</label>
						</div>
						<!--
                        	我忘了还有什么选项……需要的就加判断然后显示……一次最多4个选项，不然弹出框样式会挂掉
                        	修改x值作为顺序
                        	<div class="radio">
							    <label><input type="radio"  name="optionsRadios" id="state-x" value="state-x">xx订单</label>
					     	</div>
                        -->
					</div>
				</div>
			</div>
			<a class="popup__close" href="jsp/alreadyBuy.jsp#">X</a>
			<div class="submitChoice">
				<!--没有写form，可以改成form然后type=“submit”-->
				<input type="button" value="提交修改" class="blackButton" onclick=""/>
			</div>
			
		</div>
	</div>
</body>
</html>