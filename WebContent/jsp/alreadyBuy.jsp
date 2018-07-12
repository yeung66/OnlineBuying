<%@ page import="java.util.List"%>
<%@ page import="vo.Order"%>
<%@ page import="vo.Product"%>

<%@ page language="java" pageEncoding="utf-8"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
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
<%--<script src="../js/jquery-3.1.1.min.js">--%>


</head>
<body>
	<!--插入head-->
	<jsp:include page="head.jsp"></jsp:include>
	<!--显示订单-->
	<center>
		<div class="content">
			<table cellspacing="0">
				<tr>
					<th style="padding: 20px; font-size: 14px; font-weight: 600;"
						colspan="8">已购买商品</th>
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
					int i = 1;
					List<Order> orderList = Order.getOrderList((String) request.getSession().getAttribute("uid"));
					if (orderList != null) {
						for (Order o : orderList) {
							Product p = Product.getProductInfo(o.getProduct());
				%>
				<tr>
					<td><img src="<%=p.getPath()%>" width="70px" height="70px"></td>
					<td><a href="jsp/goodsDescribe.jsp?gid=<%=p.getId()%>"><%=p.getName()%></a></td>
					<td><%=o.getQuantity()%></td>
					<td><%=p.getPrice()%></td>
					<td><%=p.getPrice() * o.getQuantity()%></td>
					<td><%=o.getStartTime()%></td>
					<td><a href="jsp/alreadyBuy.jsp#popup-<%=i%>"
						class="stateButton"><%=o.getStatus()%></a></td>
					<!--需要代码根据order的id是否已签收和是否已进行过评价判断，如果状态是已签收+未评价才能进行跳转到comment.jsp-->
					<%--暂未实现--%>
					<%
						if (o.getStates().equals("4")) {
					%>
					<td><a
						href="jsp/comment.jsp?oid=<%=o.getId()%>&pname=<%=p.getName()%>&path=<%=p.getPath()%>&pid=<%=p.getId()%>">评价</a></td>
					<%
						} else {
					%>
					<td>无可进行的操作</td>
					<%
						}
					%>
				</tr>
				<%
					i++;
						}
					} else {

					}
				%>
			</table>
		</div>
	</center>
	<%
		i = 1;
		for (Order o : orderList) {
			String status = o.getStatus();
			int id = o.getId();
			boolean op = true;
	%>
	<div class="popup" id="popup-<%=i%>">
		<div class="popup-inner">
			<div class="popup__text">
				<h1>可用操作</h1>
				<div class="radioCon">
					<div class="radioText">执行操作：</div>
					<div class="radioBlock">
						<%
							if (status.equals("已发货")) {
						%>
						<div class="radio">
							<label><input type="radio" name="optionsRadios"
								id="state-1" value="shou">确认收货</label>
						</div>
						<%
							} else if (status.equals("已签收")) {
						%>
						<div class="radio">
							<label><input type="radio" name="optionsRadios"
								id="state-2" value="tui">退货</label>
						</div>
						<%
							} else if (status.equals("未发货")) {
						%>
						<div class="radio">
							<label><input type="radio" name="optionsRadios"
								id="state-3" value="qv">取消订单</label>
						</div>
						<%
							} else {
									op = false;
								}
						%>
					</div>
				</div>
			</div>
			<a class="popup__close" href="jsp/alreadyBuy.jsp#">X</a>
			<div class="submitChoice">
				<form action="CustomerAlterOrderServlet" id="myform">
					<input type="hidden" name="operation" value="" id="operation">
					<input type="hidden" name="id" value="<%=id%>">
					<%
						if (op == true) {
					%>
					<input type="button" value="提交修改" class="blackButton"
						onclick="tijiao()"/>
					<%
						} else {
					%>
					<input type="text" value="无可用操作"/>
					<%
						}
					%>
				</form>
			</div>

		</div>
	</div>
	<%
		i++;
		}
	%>
	<script>
		function tijiao() {
			var operation = $('input:radio[name="optionsRadios"]:checked').val();
			var form = document.getElementById('myform');
			$("#operation").val(operation);
			form.submit();
		}
	</script>
</body>
</html>