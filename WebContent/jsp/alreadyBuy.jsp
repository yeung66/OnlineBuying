<!--
<%@page import="com.czy.bean.AlreadyBuy"%>
<%@page import="com.czy.dao.AlreadyBuyDao"%>
<%@page import="com.czy.bean.Goods"%>
<%@page import="com.czy.dao.GoodsDao"%>
<%@page import="com.czy.service.GoodsService"%>
<%@page import="com.czy.bean.ShoppingCart"%>
<%@page import="java.util.List"%>
<%@page import="com.czy.factory.DAOFactory"%>
<%@page import="com.czy.dao.ShoppingCartDao"%>
<%@ page language="java" pageEncoding="utf-8"%>

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
<link href="../css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="../css/another_style.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="../css/memenu.css" rel="stylesheet" type="text/css" media="all" />
<link href="../css/cartTable.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/responsiveslides.min.js"></script>
<script type="text/javascript" src="../js/memenu.js"></script>
<script type="text/javascript" src="../js/simpleCart.min.js"></script>

</head>
<body>
	<!--插入head-->
	<jsp:include page="head.jsp"></jsp:include>
	<!--显示订单-->
	<center><div class="content">
			<table cellspacing="0" >
				<tr>
					<th colspan="8"><strong>已购买商品</strong></th>
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
				<!--
					这个是项目原本的代码
				<%
					String strUid = (String) session.getAttribute("uid");
					int uid = 0;
					if (strUid != null) {
						uid = Integer.parseInt(strUid);
					}
					AlreadyBuyDao dao = DAOFactory.getAlreadyBuyServiceInstance();
					List<AlreadyBuy> abList = dao.getAllBuyGoods(uid);
					if (abList != null & abList.size() > 0) {
						GoodsDao goodsDao = DAOFactory.getGoodsServiceInstance();
						Goods goods;
						AlreadyBuy ab;
						int gid;
						int number;
						String buyTime;
						String photoPath;
						float price;
						float totalPrice;
						for (int i = 0; i < abList.size(); i++) {
							ab = abList.get(i);
							gid = ab.getGid();
							number = ab.getNumber();
							buyTime = ab.getBuyTime();
							goods = goodsDao.queryById(gid);
							photoPath = "images/" + goods.getPhoto().split("&")[0];
							price = goods.getPrice();
							totalPrice = number * price + goods.getCarriage();
				%>
               -->
               
				<tr>
					<td><img src="https://mir-s3-cdn-cf.behance.net/project_modules/fs/f8795a43541669.57f38b1b5e4ac.jpg"
						class="img-responsive" alt=""></td>
					<td>linear lamp</td>
					<td>1</td>
					<td>100元</td>
					<td>100元</td>
					<td>2018/7/3 15:34</td>
					<td><a href="jsp/alreadyBuy.jsp?">例：未发货</td>
					<!--需要代码根据order的id是否已签收和是否已进行过评价判断，如果状态是已签收+未评价才能进行跳转到comment.jsp-->
					<td><a
						href="jsp/alreadyBuy.jsp?oid=<%=oid%>
						onclick="return confirmComment()">评价</a></td>
				</tr>
			</table>
	</div></center>
</body>
</html>