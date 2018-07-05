<!--
	描述：评论界面
-->
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

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
        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet">
        <link href="../css/star-rating.min.css" media="all" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="../js/jquery.min.js"></script>
        <script type="text/javascript" src="../js/responsiveslides.min.js"></script>
        <script type="text/javascript" src="../js/memenu.js"></script>
        <script type="text/javascript" src="../js/simpleCart.min.js"></script>
        <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
        <script src="path/to/js/star-rating.min.js" type="text/javascript"></script>
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
	            <!--显示信息的表格，需要把订单号、商品名、图片链接传输过来，然后在td里面添加代码<%=xxx.get(i).getyyy%>-->
		        <table class="commentTable">
			        <tr>
			            <th class="comA"></th>
			            <th class="comB"></th>
			        </tr>
			        <tr>
				        <td class="comA">订单号</td>
				        <td class="comB">xxx</td>
			        </tr>
			        <tr>
			            <td class="comA">商品名</td>
			            <td class="comB">xxxx</td>
			        </tr>
			        <tr>
			            <td class="comA">图片</td>
			            <td class="comB"><img width="100px" height="100px" src="https://mir-s3-cdn-cf.behance.net/project_modules/fs/f8795a43541669.57f38b1b5e4ac.jpg"
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
	                <!--textarea里面输入评论，可以给它添加name进行使用-->
                    <textarea cols="50" rows="6" placeholder="在这里输入评价……" maxlength="200"></textarea><br>
                    <input type="submit" class="buttons" value="确定"  name="submit" />
                    <input type="reset" class="buttons" value="重置"  name="reset" />
                </center>
		    </div>
	    </form> 
 	</body>
</html>