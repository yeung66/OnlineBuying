<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="util.*" %>
<%@ page import="vo.User" %>
<% 
	String uid = (String)session.getAttribute("uid");
	User u = User.getUser(uid);
	String pwd = u.getPwd();
	String info = u.getInfo();
	String add = u.getAddr();
	String tel = u.getTel();
	String sex = u.getSex();
	String money = u.getMoney();
	String sex_str1, sex_str2, sex2;
	if(sex.equals("1")){
		sex_str1 = "男";
		sex_str2 = "女";
		sex2 = "0";
	}
	else{
		sex_str1 = "女";
		sex_str2 = "男";
		sex2 = "1";
	}
	String right = u.getRight();
	Double money = u.getMoney();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery.min.js"></script>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
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
	<div class="well">
		<div align="center">
<table id="update" class="table table-striped table-bordered table-hover  table-condensed" style="text-align: center;">
  <tbody id="goodTab">
  	<tr><td><label for="id">用户名</label></td><td> <input data-toggle="tooltip" title="不可修改" id="id" name="id" type="text" onkeyup="showSubmit()"
						value="<%=uid %>" readonly /></td></tr>
    <tr><td><label for="pwd">密码</label></td><td> <input data-toggle="tooltip" title="点击修改" id="pwd" onkeyup="showSubmit()"
						name="pwd" type="password" value="<%=pwd %>" /></td></tr>
    <tr><td><label for="add">地址&nbsp;&nbsp;&nbsp;
					</label> </td><td><input data-toggle="tooltip" title="点击修改" id="add" name="add" type="text" onkeyup="showSubmit()" value="<%=add %>" /></td></tr>
    <tr><td><label
						for="tel">手机号 </label></td><td> <input data-toggle="tooltip" title="点击修改" id="tel" onkeyup="showSubmit()" name="tel" type="text"
						value="<%=tel %>" /></td></tr>
    <tr><td><label for="sex">性别&nbsp;&nbsp;&nbsp;
				</label> </td><td><select data-toggle="tooltip" title="点击修改" name="sex" id="sex">
							<option value="<%=sex %>" selected="selected"><%=sex_str1 %></option>
							<option value="<%=sex2%>"><%=sex_str2 %></option>

						</select> </td></tr>
						<tr><td><label
						for="money">余额 </label></td><td> <input  id="money"  name="money" type="text"
						value="<%=money %>" readonly /></td></tr>
				
  </tbody>
</table>
<input style="display: none;" type="button" id="submit" value="确认修改" onclick="updateInfo()" />

		</div>
	</div>
	<div align="center" id="addGoods">
		<img src="images/close.png" style="float: right;width: 30px;height: 30px;cursor: pointer;" id="closeAdd"/>
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
		
		function showSubmit(){
			$("#submit").show();
		}
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
			$("#submit").hide();
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
		$("#closeAdd").click(function(){
			$("#addGoods").hide(500);
		})
		$(function () { $("[data-toggle='tooltip']").tooltip(); });
		
	</script>
</body>
</html>