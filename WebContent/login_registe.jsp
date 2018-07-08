<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/login.css" />

<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/jquery.easing.1.3.js"></script>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {
		//打开会员登录  
		$("#Login_start_").click(function() {
			$("#alert-list").hide();
			$("#regist_container").hide();
			$("#_close").show();
			$("#_start").animate({
				left : '350px',
				height : '520px',
				width : '400px'
			}, 500, function() {
				$("#login_container").show(500);
				$("#_close").animate({
					height : '40px',
					width : '40px'
				}, 500);
			});
		});
		//打开会员注册
		$("#Regist_start_").click(function() {
			$("#login_container").hide();
			$("#_close").show();
			$("#_start").animate({
				left : '350px',
				height : '520px',
				width : '400px'
			}, 500, function() {
				$("#regist_container").show(500);
				$("#_close").animate({
					height : '40px',
					width : '40px'
				}, 500);
			});
		});
		//关闭
		$("#_close").click(function() {

			$("#_close").animate({
				height : '0px',
				width : '0px'
			}, 500, function() {
				$("#_close").hide(500);
				$("#login_container").hide(500);
				$("#regist_container").hide(500);
				$("#_start").animate({
					left : '0px',
					height : '0px',
					width : '0px'
				}, 500);
			});
		});
		//去 注册
		$("#toRegist").click(function() {
			$("#login_container").hide(500);
			$("#regist_container").show(500);
		});
		//去 登录
		$("#toLogin").click(function() {
			$("#alert-list").hide();
			$("#regist_container").hide(500);
			$("#login_container").show(500);
		});
	});
</script>

</head>
<body background="images/back2.jpg"
	style="background-repeat: no-repeat; background-size: 15000px 34500px;">

	<nav id="daohanglan">
		<span id="dengluzhuce">
			<ul class="top ">
				<li><a id="Login_start_" class="btn btn-danger"
					style="width: 100px; height: 40px; border-radius: 10px;">登陆</a></li>
				<li><a id="Regist_start_" class="btn btn-success"
					style="width: 100px; height: 40px; border-radius: 10px;">注册</a></li>
			</ul>
		</span> 

		<form style="display: inline; position: relative; left: 140px;">
			<input type="text" id="s" placeholder="search" class="swap_value"
				style="width: 150px; height: 30px" /> <input type="button"
				value="搜索" style="width: 60px; height: 30px" id="go" alt="Search"
				title="Search" />
		</form>

	</nav>


	<!-- 会员登录  -->
	<!--<div id='Login_start' style="position: absolute;" >-->
	<div id='_start'>
		<div id='_close' style="display: none;">
			<span class="glyphicon glyphicon-remove"></span>
		</div>
		<br />
		<!--登录框-->
		<form action="LoginServlet?method=login" method="post">
			<div id="login_container" style="background-color: #FFFFFF">
				<div id="lab1">
					<span id="lab_login">用户登录</span> <span id="lab_toRegist">
						&emsp;还没有账号&nbsp; <span id='toRegist'
						style="color: #EB9316; cursor: pointer;">立即注册</span>
					</span>
				</div>
				<div style="width: 330px;">
					<span id="lab_type1">账号登陆</span>
				</div>
				<div id="form_container1">
					<br /> <input type="text" class="form-control" placeholder="用户名"
						id="login_number" name="id" /> <input type="password"
						class="form-control" placeholder="密码" id="login_password"
						name="password" /> <input type="button" class="btn btn-success"
						value="登录" id="login_btn" onclick="login()" />
				</div>
			</div>
			<!-- 会员注册 -->
		</form>
		<form action="LoginServlet?method=register" method="post"
			name="register">
			<div id='regist_container'
				style="display: none; background-color: #FFFFFF">
				<div id="lab1">
					<span id="lab_login">用户注册</span> <span id="lab_toLogin">
						&emsp;已有账号&nbsp; <span id='toLogin'
						style="color: #EB9316; cursor: pointer;">立即登录</span>
					</span>
				</div>
				<div id="form_container2" style="padding-top: 25px;">
					<input type="text" class="form-control" placeholder="账号"
						id="regist_id" name="uid" onkeyup="testUid()" /> <input
						type="text" class="form-control" placeholder="手机号" id="regist_tel"
						name="tel" onkeyup="testTel()" /> <input type="text"
						class="form-control" placeholder="地址" id="regist_add" name="add"/> 
						<input type="password" class="form-control" placeholder="密码"
						id="regist_password" name="password" onkeyup="testpwd()" />
						<div style="color: black; margin-left: 0px">
						性别：<select name="sex" id="regist_sex">
							<option value="1" selected="selected">男</option>
							<option value="0">女</option>
						</select>
					</div>
					<div style="color: black; margin-left: 0px">
						账户类型：<select name="staus" id="regist_right">
							<option value="0" selected="selected">普通用户</option>
							<option value="1">商家</option>
						</select>
					</div>
					<input type="button" onclick="register1()" value="注册"
						class="btn btn-success" id="regist_btn" />
				</div>

			</div>
			<div class="row">
				<div id="alert-list" class="alert alert-warning"
					style="display: none; margin-left: 10px; margin-right: 10px; text-align: left; color: red">
					<ul>
						<li id="for-uid-illegal" style="display: none">用户名长度应为3-12位</li>
						<li id="for-uid-dup" style="display: none">用户名或账号已被占用</li>
						<li id="for-pwd" style="display: none">密码长度应为3-12位</li>
						<li id="for-tel" style="display: none">请输入合法电话号码</li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		function login() {

			$.ajax({
				data : {
					'id' : $("#login_number").val(),
					'password' : $("#login_password").val(),
					'method' : 'login'					
				},
				url : 'login',//servlet
				type:'post',
				success : function(data) {
					if (data.toString() == 'fail') {
						alert('登陆失败');
						window.location.reload();
					} else if (data.toString() == 'success') {
					    alert('登录成功')
						window.location = 'index.jsp';
					}

				},
				error : function() {
					alert('请重试！');
					window.location.reload();
				}
			})
		}
	</script>
	<script type="text/javascript">
		function register1() {

			$.ajax({
				data : {
					'id' : $("#regist_id").val(),
					'password' : $("#regist_password").val(),
					'tel' : $("#regist_tel").val(),
					'add' : $("#regist_add").val(),
					'sex' : $("#regist_sex").val(),
					'right' : $("#regist_right").val(),

					'method' : 'registe'
				},
				url : 'RegisterServlet',//servlet
				success : function(data) {
					if (data.toString() == 'fail') {
						alert('注册失败');
						window.location.reload();
					} else if (data.toString() == 'success') {
					    alert('注册成功');
						window.location = 'Welcome.jsp';
					}

				},
				error : function() {
					alert('请重试！');
					window.location.reload();
				}
			})
		}
	</script>
	<script>
		function testUid() {
			var aim_test = $("input[name='uid']").val();
			if (aim_test.length > 12 || aim_test.length < 3) {
				$("#alert-list").show();
				$("#for-uid-illegal").show();
			} else {
				$("#for-uid-illegal").hide();
				$("#alert-list").hide();
			}
			$.ajax({
				url : 'checkUid',
				data : {
					'uid' : $("input[name='uid']").val(),
					'name' : $("input[name='name']").val(),

				},
				success : function(data) {
					if (data.toString() == 'fail_4') {
						$("#alert-list").show();
						$("#for-uid-dup").show();
					} else if (data.toString() == 'success_2') {
						$("#for-uid-dup").hide();
						$("#alert-list").hide();
					}
				}
			})
		}
		function testpwd() {
			var aim_test = $("#regist_password").val();
			if (aim_test.length > 12 || aim_test.length < 3) {
				$("#alert-list").show();
				$("#for-pwd").show();
			} else {
				$("#alert-list").hide();
				$("#for-pwd").hide();

			}
		}
		function testTel() {
			var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
			var aim_test = $("#regist_tel").val();
			if (!myreg.test(aim_test)) {
				$("#alert-list").show();
				$("#for-tel").show();
			} else {
				$("#alert-list").hide();
				$("#for-tel").hide();
			}
		}
	</script>
</body>

</html>
