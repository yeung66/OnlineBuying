<%@ page language="java" 
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	#update input{
		outline: medium;
		border: none;
	}
</style>
</head>
<jsp:include page="head.jsp"/>
<body>
<br /><br />		
<div align="center">
	<div style="color: green;font-family:'STXingkai';"><h1>我的信息</h1></div>
	<div id="update" style="width:350px;font-size: larger;">
		<form id="update" action="updateInfo" method="post">
				<label for="id">用户名：</label> 
				<input id="id" name="id" type="text" value="" /><br/>
				<label for="add">地址：&nbsp;&nbsp;&nbsp;
				</label> <input id="add" name="add" type="text" value="" /><br/>
				<label for="tel">手机号： </label> <input id="tel"
					name="tel" type="text" value="" /><br/>
      <label for="sex">性别：&nbsp;&nbsp;&nbsp; </label> <input id="sex"
					name="sex" type="text" value=""/><br/>
			<input  type="submit" value="确认修改" onclick="updateInfo()"/>
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
				},
				url : 'updateInfoServlet',
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