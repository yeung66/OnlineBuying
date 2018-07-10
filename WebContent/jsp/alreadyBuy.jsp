<!--
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
               
				<tr>
					<td><img src="https://mir-s3-cdn-cf.behance.net/project_modules/fs/f8795a43541669.57f38b1b5e4ac.jpg"
						width="70px" height="70px"></td>
					<!--这里添加一下商品详情页的链接 href-->
					<td><a href="">linear lamp</a></td>
					<td>1</td>
					<td>100元</td>
					<td>100元</td>
					<td>2018/7/3 15:34</td>
					<td><a href="#popup" class="stateButton">例：未发货</td>
					<!--需要代码根据order的id是否已签收和是否已进行过评价判断，如果状态是已签收+未评价才能进行跳转到comment.jsp-->
					<td><a
						href="jsp/alreadyBuy.jsp?oid=<%=oid%>">评价</a></td>
					<!--
						判断，如果不能评价：
						<td>无可进行的操作</td>
                    -->
				</tr>
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
			<a class="popup__close" href="#">X</a>
			<div class"submitChoice">
				<!--没有写form，可以改成form然后type=“submit”-->
				<input type="button" value="提交修改" class="blackButton" onclick=""></input>
			</div>
			
		</div>
	</div>
</body>
</html>