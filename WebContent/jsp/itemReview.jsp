<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="vo.*"%>
<%@page import="java.util.*"%>
<%@ page import="DAO.CommentDAO" %>
<%@ page import="DAO.OrderDAO" %>
<%@ page import="DAO.ProductDAO" %>
<%
	int pid = Integer.valueOf( request.getParameter("pid"));
	List<Comment> comlist = CommentDAO.getCommentList(pid);
	Product p = ProductDAO.getProductInfo(pid);
	int i = 1;
	Order o;
%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>查看商品评价</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link href="../css/itemReview.css" rel="stylesheet">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
            crossorigin="anonymous">
    </head>
    <script src='js/jquery.min.js'></script>
    <script>
        document.getElementsByTagName('body').height=window.innerHeight;
        function closeDetails(obj){
        	document.getElementById(obj).style.display="none";
        }
        function openDetails(obj){
        	var details=document.getElementsByClassName("chatright");
        	for(var i=0;i<details.length;i++){
        		details[i].style.display="none";
        	}
        	document.getElementById(obj).style.display="block";
        }
    </script>
    <body >
    	<!--插入head-->
    	<jsp:include page="head.jsp"></jsp:include>
    	<!--查看评价-->
    	<div class="containerRe">
    		<div class="chatbox">
    			<div class="chatleft">
    				<div class="top">
    					<h1>查看评价</h1>
    				</div>
    				<ul>
    					<!--
    					        改的时候去掉一个重复的，用循环，图片用Q版分数图片（吧），定下来图片之后改地址
    					        按照循环修改detail-x的数值
    					-->
					<%
						for (Comment com : comlist) {
							o = OrderDAO.getOrderDetail(pid, com.getPurchaser());
					%>
					<li class="score" onclick="javascript:openDetails('detail-<%=i%>')">
					<img class="level" src="images/score-<%=com.getScore()%>.jpg">
						<div class="bubble">
							<h2>
								<strong><%=com.getScore()%>分</strong>
							</h2>
							<h3>
								评价时间：<%=com.getComDate()%>&nbsp;&nbsp;&nbsp;&nbsp;订单号：<%=o.getId()%></h3>
							<h4>
								<%
									String contentReduced;
										if (com.getContent().length() > 10)
											contentReduced = com.getContent().substring(0, 9)+"...";
										else
											contentReduced = com.getContent();
								%>
								商品名：<%=p.getName()%>&nbsp;&nbsp;&nbsp;&nbsp;评价：<%=contentReduced%></h4>
						</div></li>
					<%
						i++;
						}
					%>
				</ul>
			</div>
			<!--
                	改的时候去掉一个重复的，然后使用循环，修改detail-x
                -->
			<%
				i = 1;
				for (Comment com : comlist) {
					o = OrderDAO.getOrderDetail(pid, com.getPurchaser());

			%>
			<div class="chatright" id="detail-<%=i%>">
				<div class="top">
					<span class="close close-button"
						onclick="javascript:closeDetails('detail-<%=i%>')">&times</span>
					<div class="user">
						<img class="level" src="images/score-<%=com.getScore()%>.jpg">
						<div class="details">
							<h2>
								<strong>评价详情</strong>
							</h2>
							<h3>
								商品名：<%=p.getName()%></h3>
						</div>
					</div>
				</div>
				<div class="center">
					<p>
						<b>分数：<%=com.getScore()%>分
						</b> <br />订单号：<%=o.getId()%>
						<br />评价时间：<%=com.getComDate()%>
						<br />单价：<%=p.getPrice()%>元 <br />购买数量：<%=o.getQuantity()%>
						<br />总价：<%=p.getPrice() * o.getQuantity()%>元 <br />评价：<%=com.getContent()%>
					</p>
				</div>
			</div>
			<%
				i=i+1;
				}
			%>
		</div>
	</div>
</body>
</html>