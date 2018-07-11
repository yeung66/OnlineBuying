<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆注册</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/login.css" />

<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/jquery.easing.1.3.js"></script>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {
		//打开会员登录  
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
		//去注册
		$("#toRegist").click(function() {
			$("#login_container").hide(500);
			$("#regist_container").show(500);
		});
		//去登录
		$("#toLogin").click(function() {
			$("#alert-list").hide();
			$("#regist_container").hide(500);
			$("#login_container").show(500);
		});
	});
</script>

</head>
<body >

<jsp:include page="jsp/head.jsp"/>
	

<div style="width: 100%;height:130px"></div>
	<!-- 会员登录  -->
	<!--<div id='Login_start' style="position: absolute;" >-->
	
	<div style="background-image:url(images/login_bac5.jpg) ;width:100%;height:850px;background-size: 100%;">
		
		<br /><br /><br /><br /><br /><br /><br />
		<div style="position: relative;margin-left: 200px;">
			<span style="font-family:华文行楷;font-size: 48px;"><br /><br /><br /><br /><br /><br />
				<p style="font-size: larger;">专注服务于</p>
				<br />
				<p style="font-size: larger;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;每一位顾客</p>
			</span>
		</div>
		<div id='_start' style="position:relative;
	margin-left:1050px;background-color: gray;margin-top: -250px;">
		<div id='_close' style="display: none;">
			<span class="glyphicon glyphicon-remove"></span>
		</div>
		<br />
		<!--登录框-->
		<form action="LoginServlet?method=login" method="post">
			<div id="login_container" style="background-color: #FFFFFF;">
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
						name="password" /> <input type="button" style="width:330px" class="btn"
						value="登录" id="login_btn" onclick="login()" />
				</div>
			</div>
			<!-- 会员注册 -->
		</form>
		<form action="LoginServlet?method=register" method="post"
			name="register">
			<div id='regist_container'
				style=" background-color: #FFFFFF">
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
						class="btn" id="regist_btn" style="position: relative;margin-top: 6px;"/>
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
