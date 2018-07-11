<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.min.js"></script>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
        <title>添加商品</title>
        <style>
        	#update input {
	outline: medium;
	border: none;
	background-color:gainsboro;
}
.fileinput-button {
            position: relative;
            display: inline-block;
            overflow: hidden;
            line-height: 20px;
            
        }

        .fileinput-button input{
            position: absolute;
            left: 0px;
            top: 0px;
            opacity: 0;
            -ms-filter: 'alpha(opacity=0)';
           
        }

        </style>
        <script>
        	function Add() {
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
						alert('提交失败');
						window.location.reload();
					} else if (data.toString() == 'success') {
						alert('提交成功');
						window.location.reload();
					}

				},
				error : function() {
					alert('请重试！');
					window.location.reload();
				}
			})
		}
        	function showSubmit(){
			$("#submit").show();
		}
        	$(function () { $("[data-toggle='tooltip']").tooltip(); });
        </script>
    </head>
    <jsp:include page="head.jsp" />
    <body>
    	<div class="well">
		<div align="center">
<table id="update" class="table table-striped table-bordered table-hover  table-condensed" style="text-align: center;">
  <tbody id="goodTab">
  	<tr><td><label for="name">商品名</label></td><td> <input data-toggle="tooltip" title="点击输入" id="name" name="name" type="text" onkeyup="showSubmit()"
						value=""  /></td></tr>
    <tr><td><label for="num">数量</label></td><td> <input data-toggle="tooltip" title="点击输入" id="num" name="num" onkeyup="showSubmit()"
						name="pwd" type="text" value="" /></td></tr>
    <tr><td><label for="price">价格
					</label> </td><td><input data-toggle="tooltip" title="点击输入" id="price" name="price" type="text" onkeyup="showSubmit()" value="" /></td></tr>
    				
  </tbody>
</table>
<span class="btn btn-success fileinput-button">
            <span style="font-size: 20px;color: #000000;">上传图片</span>
            <input data-toggle="tooltip" title="点击上传" id="path"  name="path" type="file" value="" />
        </span>
        <br />
        <br />
<input style="display: none;color: #000000;" type="button" id="submit" value="提交" onclick="Add()" />

		</div>
	</div>
    	
 	</body>
 	
</html>