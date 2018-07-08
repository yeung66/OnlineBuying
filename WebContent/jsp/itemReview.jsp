<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
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
    <body class="box">
    	<!--插入head-->
    	<jsp:include page="head.jsp"></jsp:include>
    	<!--查看评价-->
    	<div class="container">
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
    					<li class="score" onclick="javascript:openDetails('detail-1')">
    						<img class="level" 
    							src="https://mir-s3-cdn-cf.behance.net/project_modules/1400/cebec057174185.59cb88804182b.jpg">
    							<div class="bubble">
    								<h2><strong>xx分</strong></h2>
    								<h3>评价时间：2018/07/05&nbsp;&nbsp;&nbsp;&nbsp;订单号：111111</h3>
    								<h4>商品名：xxx&nbsp;&nbsp;&nbsp;&nbsp;评价：截取评价的十个字xxxx</h4>
    							</div>
    					</li>
    					<li class="score" onclick="javascript:openDetails('detail-2')">
    						<img class="level" 
    							src="https://mir-s3-cdn-cf.behance.net/project_modules/1400/b41c0433443247.56ab253f5ee1f.jpg">
    							<div class="bubble">
    								<h2><strong>xx分</strong></h2>
    								<h3>评价时间：2018/07/05&nbsp;&nbsp;&nbsp;&nbsp;订单号：123456</h3>
    								<h4>商品名：xxx&nbsp;&nbsp;&nbsp;&nbsp;评价：截取评价的十个字xxxx</h4>
    							</div>
    					</li>
    				</ul>
    			</div>
    			<!--
                	改的时候去掉一个重复的，然后使用循环，修改detail-x
                -->
    			<div class="chatright" id="detail-1">
    				<div class="top">
    					<span class="close close-button" onclick="javascript:closeDetails('detail-1')">&times</span>
    					<div class="user">
    						<img class="level" src="https://mir-s3-cdn-cf.behance.net/project_modules/1400/cebec057174185.59cb88804182b.jpg">
    						<div class="details">
    							<h2><strong>评价详情</strong></h2>
    							<h3>商品名：xxx</h3>
    						</div>
    					</div>
    				</div>
    				<div class="center">
    					<p><b>分数：xx分</b>
    						</br>订单号：111111
    						</br>评价时间：2018/07/05
    						</br>单价：100元
    						</br>购买数量：1
    						</br>总价：100元
    						</br>评价：默认好评</p>
    				</div>
    			</div>
    			<div class="chatright" id="detail-2">
    				<div class="top">
    					<span class="close close-button" onclick="javascript:closeDetails('detail-2')">&times</span>
    					<div class="user">
    						<img class="level" src="https://mir-s3-cdn-cf.behance.net/project_modules/1400/b41c0433443247.56ab253f5ee1f.jpg">
    						<div class="details">
    							<h2><strong>评价详情</strong></h2>
    							<h3>商品名：xxx</h3>
    						</div>
    					</div>
    				</div>
    				<div class="center">
    					<p><b>分数：xx分</b>
    						</br>订单号：123456
    						</br>评价时间：2018/07/05
    						</br>单价：100元
    						</br>购买数量：1
    						</br>总价：100元
    						</br>评价：默认好评</p>
    				</div>
    			</div>
    		</div>
    	</div>
    </body>
</html>