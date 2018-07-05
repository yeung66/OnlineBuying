<!--
	描述：评论界面
-->
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="util.*" %>
<% 
	int oid = Integer.parseInt(request.getParameter("oid")); 
	String pname = request.getParameter("pname");
	String path = request.getParameter("path");
	int pid = Integer.parseInt(request.getParameter("pid")); 
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>进行评价</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <!--这里添加css和js包，可能会有路径问题-->
        <link href="../css/bootstrap.css" rel="stylesheet" type="text/css"
	    media="all" />
        <link href="../css/another_style.css" rel="stylesheet" type="text/css"
	    media="all" />
        <link href="../css/memenu.css" rel="stylesheet" type="text/css" media="all" /> 
        <link href="../css/commentStyle.css" rel="stylesheet" type="text/css" media="all" />
        <script type="text/javascript" src="../js/jquery.min.js"></script>
        <script type="text/javascript" src="../js/responsiveslides.min.js"></script>
        <script type="text/javascript" src="../js/memenu.js"></script>
    </head>
    <body>
        <!--插入head-->
        <jsp:include page="head.jsp"></jsp:include>
     	<!--
     	        进行评价。
     	        在订单页点击评论，判断订单状态处于已收货状态则跳转到此界面。
          	这里设置了action为comment，可以修改
        -->
	    <form class="commentForm" action="comment" method="get" >
	        <div class="leftControl">
	            <!--显示信息的表格，需要把订单号、商品名、图片链接传输过来，然后在td里面添加代码-->
		        <table class="commentTable">
			        <tr>
						<th colspan="2"><b><center>商品评分</center></b></th>
			        </tr>
			        <tr>
				        <td class="comA">订单号</td>
				        <td class="comB"><%=oid %></td>
			        </tr>
			        <tr>
			            <td class="comA">商品名</td>
			            <td class="comB"><%=pname %></td>
			        </tr>
			        <tr>
			            <td class="comA">图片</td>
			            <td class="comB"><img width="50px" height="50px" src=<%=path %>
						></td>
			        </tr>
			        <tr>
				        <td class="comA">评分</td>
				        <!--后台添加获取radio值、评论值的代码-->
				        <td class="comB"> <div class="rating">
                            <input type="radio" id="star5" name="rating" value="5" hidden/>
                            <label for="star5"></label>
                            <input type="radio" id="star4" name="rating" value="4" hidden/>
                            <label for="star4"></label>
                            <input type="radio" id="star3" name="rating" value="3" hidden/>
                            <label for="star3"></label>
                            <input type="radio" id="star2" name="rating" value="2" hidden/>
                            <label for="star2"></label>
                            <input type="radio" id="star1" name="rating" value="1" hidden/>
                            <label for="star1"></label>
                        </div></td>
			        </tr>
		        </table>
	            <center>
	            	<input type="hidden" value="<%=oid %>" name="oid"></input>
	            	<input type="hidden" value="<%=pname %>" name="pname"></input>
	            	<input type="hidden" value="<%=path%>" name="path"></input>
	            	<input type="hidden" value="<%=pid %>" name="pid"></input>
	                <!--textarea里面输入评论，可以给它添加name进行使用-->
                    <textarea cols="50" rows="6" placeholder="在这里输入评价……" maxlength="200" name="content" ></textarea><br>
                    <input type="submit" class="buttons" value="确定"  name="submit" />
                    <input type="reset" class="buttons" value="重置"  name="reset" />
                </center>
		    </div>
	    </form> 
 	</body>
</html>