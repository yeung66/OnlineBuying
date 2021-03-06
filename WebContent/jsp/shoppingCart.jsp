<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" import="java.util.*"%>
<%@ page import="vo.ShoppingCart" %>
<%@ page import="java.util.List" %>
<%@ page import="DAO.ShoppingCartDAO" %><%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
    String uid = (String)request.getSession().getAttribute("uid");
	List<ShoppingCart> list = ShoppingCartDAO.searchFromSQL(uid);
	double total=0.0;
	int i=0;
 %>
<!DOCTYPE html>
<html>
<head>
<title>购物车</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/another_style.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/memenu.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/cartTable.css" rel="stylesheet" type="text/css" media="all" />
<%--<script type="text/javascript" src="js/jquery.min.js"></script>--%>
<script type="text/javascript" src="js/responsiveslides.min.js"></script>
<%--<script type="text/javascript" src="js/bootstrap.min.js"></script>--%>
	<%--<script src="js/jquery-3.1.1.min.js"></script>--%>
<script type="text/javascript">
	function confirmBuy() {
		return confirm("确定支付吗？");
	}
	function confirmDelete() {
		return confirm("确认删除订单吗");
	}
	/*checkbox全选*/
    $(function(){
        function initTableCheckbox() {
            var $thr = $('table thead tr').not(".tableHead");
            /*“全选/反选”复选框*/
            var $checkAllTh=$thr.first();
            var $tbr = $('table tbody tr');
            var $checkAll = $thr.find('input');
            $checkAll.click(function(event){
                /*将所有行的选中状态设成全选框的选中状态*/
                $tbr.find('input').prop('checked',$(this).prop('checked'));
                /*并调整所有选中行的CSS样式*/
                if ($(this).prop('checked')) {
                    $tbr.find('input').parent().parent().addClass('warning');
                } else{
                    $tbr.find('input').parent().parent().removeClass('warning');
                }
                /*阻止向上冒泡，以防再次触发点击操作*/
                event.stopPropagation();
            });
            /*点击全选框所在单元格时也触发全选框的点击操作*/
            $checkAllTh.click(function(){
                $(this).find('input').click();
            });

            /*点击每一行的选中复选框时*/
            $tbr.find('input').click(function(event){
                /*调整选中行的CSS样式*/
                $(this).parent().parent().toggleClass('warning');
                /*如果已经被选中行的行数等于表格的数据行数，将全选框设为选中状态，否则设为未选中状态*/
                $checkAll.prop('checked',$tbr.find('input:checked').length == $tbr.length ? true : false);
                /*阻止向上冒泡，以防再次触发点击操作*/
                event.stopPropagation();

            });
        }
        initTableCheckbox();
    });
</script>
</head>
<body>
<!--插入head-->
<jsp:include page="head.jsp"></jsp:include>
	<div class="content">
		<form action="BuyCartServlet" method="post">
			<center><table cellspacing="0" >
				<thead>
					<tr class="tableHead">
						<th style="padding:20px;font-size: 14px;font-weight: 600;" colspan="8">购物车</th>
					</tr>
					<tr>
						<th></th>
						<th>商品图片</th>
						<th>商品名</th>
						<th>数量</th>
						<th>价格</th>
						<th>总价</th>
						<th>加入时间</th>
						<th>操作</th>
					</tr>
				</thead>
                <tbody>
				    <%
						for (ShoppingCart cart : list){
						    i++;
						    int gid = cart.getProduct().getId();
						    int number = cart.getProduct().getNum();
						    total+= cart.getNum()*cart.getProduct().getPrice();
					%>
                	<tr>
						<td><input type="checkbox" name="checkItem" value="<%=cart.getProduct().getId()%>"/></td>
                		<td><img width="50px" height="70px" style="padding-top:10px;padding-bottom: 10px;" 
                			src="<%=cart.getProduct().getPath()%>"></td>

                		<!--点击名字跳转到商品详情页，在a里面添加链接-->
                		<td class="cartName"><a href="jsp/product_detail.jsp?gid=<%=cart.getProduct().getId()%>"><%=cart.getProduct().getName()%></a></td>
                		<td class="cartAmount"><%=cart.getNum()%></td>
                		<td class="cartSingle"><%=cart.getProduct().getPrice()%></td>
                		<td class="cartTotal"><%=cart.getNum()*cart.getProduct().getPrice()%></td>
                		<td>
                			<div class="cartTime">
                				<p><%=cart.getStartTime()%></p>
                			</div>
                		</td>
                		<td>
                			<a href="DeleteCartServlet?gid=<%=gid%>&number=<%=number%>" onclick="return confirmDelete()">删除</a>
                		</td>
                	</tr>
				<%
					}
				%>
                </tbody>
			</table></center>
			<!--进行计算-->

		<div class="payForCart"><p>购物车商品总价为<%=total%></p>
			<input type="submit" class="to-buy"
				onclick="return confirmBuy()"value=" &nbsp;&nbsp;&nbsp;支付&nbsp;&nbsp;&nbsp;"></input>
	    </div>
		</form>
	</div>
	
</body>
</html>