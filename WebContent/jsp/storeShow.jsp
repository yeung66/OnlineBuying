<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="vo.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery.min.js"></script>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!--script src="js/bootstrap.min.js"></script-->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商家店铺</title>
<style>
#storeInfo {
	background-image: url(images/storeBac.png);
	width: 70%;
	height: 350px;
	font-size: larger;
}
</style>
</head>
<body>
	<jsp:include page="head.jsp"/>
	<script type="text/javascript">
		window.onload = function() {
			var e = ${storeGoood}
			;
			for (var i = 0; i < e.length; i++) {
				var s = ${storeGoood}
				;
				var str = JSON.stringify(s);
				var result = JSON.parse(str + "");
				var para0 = document.createElement("td");
				var para2 = document.createElement("a");
				para2.href = "jsp/product_detail.jsp?gid=" + result[i].id;
				var node0 = document.createTextNode(result[i].name);
				para2.appendChild(node0);
				para0.appendChild(para2);
				var element = document.getElementById("goodsTab");
				element.appendChild(para0);
				var para0 = document.createElement("td");
				var node0 = document.createTextNode(result[i].price);
				para0.appendChild(node0);
				var element = document.getElementById("goodsTab");
				element.appendChild(para0);
				var para0 = document.createElement("td");
				var node0 = document.createTextNode(result[i].num);
				para0.appendChild(node0);
				var element = document.getElementById("goodsTab");
				element.appendChild(para0);
				var para0 = document.createElement("td");
				var img = new Image();
				img.src = result[i].path;
				img.width = 200;
				img.height = 100;
				para0.appendChild(img);
				var element = document.getElementById("goodsTab");
				element.appendChild(para0);
				var para0 = document.createElement("td");
				var node0 = document.createTextNode(result[i].score);
				para0.appendChild(node0);
				var element = document.getElementById("goodsTab");
				element.appendChild(para0);
				var para0 = document.createElement("tr");
				var element = document.getElementById("goodsTab");
				element.appendChild(para0);
			}
		}
	</script>
	<div align="center" style="margin-top: 180px;">
		<div align="center">
			<div align="center" id="storeInfo">
				<br />
				<br /> <img src="images/storePto1.jpg" class="img-circle"
					style="height: 150px; width: 150px;"><br />
				<%
					int pid = Integer.parseInt(request.getParameter("pid"));
					String id = Product.getProductInfo(pid).getOwner();
					String tel = User.getUser(id).getTel();
				%>
				<span class="text-primary"><h1><%=id%></h1></span> <span
					class="text-primary"><h2>Tel:<%=tel%></h2></span>
			</div>
		</div>
		<div style="width: 70%;">
			<table class="table table-striped">
				<thead>
					<th>商品名</th>
					<th>价格</th>
					<th>剩余数量</th>
					<th>图片</th>
					<th>评分</th>
				</thead>
				<tbody id="goodsTab">

				</tbody>
			</table>
		</div>
	</div>
</body>
</html>