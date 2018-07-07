<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
#update input {
	outline: medium;
	border: none;
}

#addGoodsbtn {
	position: relative;
	left: 50px;
	top: 40px;
}

.btn {
	outline: medium;
	cursor: pointer !important;
	cursor: hand;
	background: url(bg_btn.gif);
	background-position: bottom;
	background-color: #E1F1D4;
	text-align: center;
	height: 30px;
	font-size: 12px;
	width: 100px;
	padding: 0px !important;
	border: 1px solid #54A9DF;
	color: #005500
}

#addGoods {
	position: absolute;
	left: 850px;
	top: 150px;
	width: 400px;
	height: 300px;
	background-color: lightyellow;
	display: none;
}
</style>
</head>
<jsp:include page="head.jsp" />
<body>
	<br />
	<br />
	<div>
		<div id="addGoodsbtn">
			<button class="btn" onclick="showAdd()">添加商品</button>
		</div>
		<div align="center">
			<div style="color: green; font-family: 'STXingkai';">
				<h1>我的信息</h1>
			</div>
			<div id="update" style="width: 350px; font-size: larger;">
				<form id="update" action="updateInfo" method="post">
					<label for="id">用户名：</label> <input id="id" name="id" type="text"
						value="" /><br /> <label for="pwd">密码：</label> <input id="pwd"
						name="pwd" type="password" value="" /><br /> <label for="add">地址：&nbsp;&nbsp;&nbsp;
					</label> <input id="add" name="add" type="text" value="" /><br /> <label
						for="tel">手机号： </label> <input id="tel" name="tel" type="text"
						value="" /><br /> <label for="sex">性别：&nbsp;&nbsp;&nbsp;
					</label> <input id="sex" name="sex" type="text" value="" /><br /> 
					<label for="info">个人信息：</label> <input id="info" name="info" type="text"
						value="" /><br />
					<input
						type="button" value="确认修改" onclick="updateInfo()" />
				</form>
			</div>
		</div>
	</div>
	<div align="center" id="addGoods">
		<br />
		<div style="color: green; font-family: 'STXingkai';">
			<h1>添加商品</h1>
		</div>
		<div id="update" style="width: 350px; font-size: larger;">
			<form action="AddProductServlet" method="post">
				<label for="name">商品名：</label> <input id="name" name="name"
					type="text" value="" /><br /> <label for="price">价格：&nbsp;&nbsp;&nbsp;
				</label> <input id="price" name="price" type="text" value="" /><br /> <label
					for="num">数量：&nbsp;&nbsp;&nbsp; </label> <input id="num" name="num"
					type="text" value="" /><br /> <label for="path">选择商品图片：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</label> <input name="path" type="file" /><br /> <input type="submit"
					value="确认添加" onclick="shutAdd()" />
			</form>
		</div>
	</div>
	<script>
		function updateInfo() {

			$.ajax({
				data : {
					'id' : $("#id").val(),
					'tel' : $("#tel").val(),
					'add' : $("#add").val(),
					'sex' : $("#sex").val(),
					'pwd' : $("#pwd").val(),
					'info' : $("#info").val(),
				},
				url : 'ChangeUserServlet',
				success : function(data) {
					if (data.toString() == 'fail') {
						alert('修改失败');
						window.location.reload();
					} else if (data.toString() == 'success') {
						alert('修改成功');
						window.location.reload();
					}

				},
				error : function() {
					alert('请重试！');
					window.location.reload();
				}
			})
		}
		function showAdd() {
			$("#addGoods").show(500);
		}
		function shutAdd() {
			$("#addGoods").hide(500);
			$.ajax({
				data : {
					'name' : $("#name").val(),
					'price' : $("#price").val(),
					'num' : $("#num").val(),
					'path' : $("#path").val(),
				},
				url : 'addProduct',
				success : function(data) {
					if (data.toString() == 'fail') {
						alert('修改失败');
						window.location.reload();
					} else if (data.toString() == 'success') {
						alert('修改成功');
						window.location.reload();
					}

				},
				error : function() {
					alert('请重试！');
					window.location.reload();
				}
			})
		}
	</script>
</body>
</html>