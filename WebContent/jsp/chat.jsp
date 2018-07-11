<%@ page import="java.util.Set" %>
<%@ page import="java.util.List" %>
<%@ page import="vo.Message" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	List<String> contacter = Message.getRelatedUser((String)session.getAttribute("uid"));
%>
<!DOCTYPE html>
<html>

	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="format-detection" content="telephone=no">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
		<base target="_blank">
		<title>客服</title>
		<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="css/chat.css">
		<link rel="stylesheet" href="css/chat1.css">
		<script src="js/jquery-3.1.1.min.js"></script>
		
	</head>

	<body lang="zh">
		<img style="width:100%;height:100%" src="images/聊天背景.JPG">
		<div class="abs cover contaniner">
			<div class="abs cover pnl">
				<div class="top pnl-head"><p>客服1号</p></div>
				<div class="abs cover pnl-body" id="pnlBody">
					<div class="abs cover pnl-left">
						<div class="abs cover pnl-msgs scroll" id="show">
							<div class="msg min time" id="histStart">加载历史消息</div>
							<div class="pnl-list" id="hists">
								<!-- 历史消息 -->
							</div>
							<div class="pnl-list" id="msgs">
								<div class="msg robot">
									<div class="msg-left" worker="客服1号">
										<div class="msg-host photo" style="background-image: url(images/1.jpg)"></div>
										<div class="msg-ball" title="今天 17:52:06">你好，我是只能打字的聊天机器人</div>
									</div>
								</div>
								<div class="msg guest">
									<div class="msg-right">
										<div class="msg-host photo" style="background-image: url(images/10.JPG)"></div>
										<div class="msg-ball" title="今天 17:52:06">你好</div>
									</div>
								</div>
							</div>
							
						</div>
						<div class="abs bottom pnl-text">
							
							<div class="abs cover pnl-input">
								<textarea class="scroll" id="text" wrap="hard" placeholder="在此输入文字信息..."></textarea>
								<div class="abs atcom-pnl scroll hide" id="atcomPnl">
									<ul class="atcom" id="atcom"></ul>
								</div>
							</div>
							<div class="abs br pnl-btn" id="submit" style="background-color: rgb(32, 196, 202); color: rgb(255, 255, 255);" onclick="SendMsg()">发送</div>
							
						</div>
					</div>
					<div class="abs right pnl-right">
								<div class="chat03">
                    <div class="chat03_title">
                        <label class="chat03_title_t">商家列表</label>
                    </div>
                    <div class="chat03_content">
                        <ul>
							<%

								for(String c: contacter){
							%>
                            <li name="contacter" id="<%=c%>">
                             
                                <a href="javascript:;">
                                    <img src="images/4.JPG"></a><a href="javascript:;" class="chat03_name"><%=c%></a>
                            </li>

							<%
								}
							%>
                            <%--<li class="choosed">--%>
                                <%----%>
                                <%--<a href="javascript:;">--%>
                                    <%--<img src="images/1.jpg"></a><a href="javascript:;" class="chat03_name">客服1号</a>--%>
                            <%--</li>--%>
                            <%----%>
                            <%--<li>--%>
                                <%----%>
                                <%--<a href="javascript:;">--%>
                                    <%--<img src="images/4.JPG"></a><a href="javascript:;" class="chat03_name">吴敬</a>--%>
                            <%--</li>--%>
                            <%----%>
                            <%--<li>--%>
                                <%----%>
                                <%--<a href="javascript:;">--%>
                                    <%--<img src="images/4.JPG"></a><a href="javascript:;" class="chat03_name">张珊珊</a>--%>
                            <%--</li>--%>
                            
                           
                        </ul>
                    </div>
                </div>
						
					</div>
				</div>
			</div>
		</div>
	<script>
		var contacterList = document.querySelector('.chat03_content > ul')
		window.onload=function () {
            var chooseCont = contacterList.firstElementChild
            chooseCont.class = 'choosed'
            var http = new XMLHttpRequest()
            http.onreadystatechange = function (data) {
                if (http.readyState == 4) {// 4 = "loaded"
                    if (http.status == 200) {// 200 = OK
                        // ...our code here...
						var data = JSON.parse(http.responseText)
						console.log(1)
                    }
                }
            }
            http.open('get', 'message'+'from=' + chooseCont.id + '&type=0')
            http.send()
        }
	</script>
	</body>

</html>