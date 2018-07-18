<%@ page import="java.util.Set" %>
<%@ page import="java.util.List" %>
<%@ page import="vo.Message" %>
<%@ page import="javafx.util.Pair" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	if(session.getAttribute("uid")==null){
	    out.println("<body><script>");
	    out.println("alert('请先登录！')");
	    out.println("window.location.href='../login_registe.jsp'");
		out.println("</script></body>");
	    return;
	}else if (((String)session.getAttribute("uid")).equals(request.getParameter("to"))){
		out.println("<body><script>");
		out.println("alert('请不要跟自己通信！')");
		out.println("window.history.back(-1)");
		out.println("</script></body>");
		return;
	}
%>
<%
	List<Pair<String,String>> contacter = Message.getRelatedUser((String)session.getAttribute("uid"));
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
		<style>
			[newMessage=true]::after{
				content: 'new';
				position: absolute;
				right: 0;
				top: 40%;
			}
		</style>
	</head>

	<body lang="zh">
		<img style="width:100%;height:100%" src="images/聊天背景.JPG">
		<div class="abs cover contaniner">
			<div class="abs cover pnl">
				<div class="top pnl-head"><p id="head-title">客服1号</p></div>
				<div class="abs cover pnl-body" id="pnlBody">
					<div class="abs cover pnl-left">
						<div class="abs cover pnl-msgs scroll" id="show">
							<div class="msg min time" id="histStart">加载历史消息</div>
							<div class="pnl-list" id="hists">
								<!-- 历史消息 -->
							</div>
							<div class="pnl-list" id="msgs">
								<%--<div class="msg robot">--%>
									<%--<div class="msg-left" worker="客服1号">--%>
										<%--<div class="msg-host photo" style="background-image: url(images/1.jpg)"></div>--%>
										<%--<div class="msg-ball" >你好，我是只能打字的聊天机器人</div>--%>
									<%--</div>--%>
								<%--</div>--%>
								<%--<div class="msg guest">--%>
									<%--<div class="msg-right">--%>
										<%--<div class="msg-host photo" style="background-image: url(images/10.JPG)"></div>--%>
										<%--<div class="msg-ball" >你好</div>--%>
									<%--</div>--%>
								<%--</div>--%>
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
                        <label class="chat03_title_t">最近联系</label>
                    </div>
                    <div class="chat03_content">
                        <ul>
							<%

								for(Pair c: contacter){
							%>
                            <li name="contacter" id="<%=c.getKey()%>" onclick="changeMain(this)" contact="c<%=c.getKey()%>"
									<%if(c.getValue().equals("0")){
									    out.print("newMessage='true' ");
									}%>>
                             
                                <a href="javascript:;">
                                    <img src="images/4.JPG"></a><a href="javascript:;" class="chat03_name"><%=c.getKey()%></a>
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
		var toContacter
		<%if(request.getParameter("to")!=null){ %>
        	toContacter = '${param.to}'
		<%}%>

		var contacterList = document.querySelector('.chat03_content > ul')
		window.onload=function () {
		    createWebsocket()
			var chooseCont
			if(toContacter!=null && contacterList.querySelector('[contact=c'+toContacter+']')==null) {
                var html = '<li name="contacter" id={id} name={id} onclick="changeMain(this)">\n' +
                    '                             \n' +
                    '                                <a href="javascript:;">\n' +
                    '                                    <img src="images/4.JPG"></a><a href="javascript:;" class="chat03_name">{id}</a>\n' +
                    '                            </li>'
				html = html.replace(/{id}/g,toContacter)
                contacterList.innerHTML = html+contacterList.innerHTML
                chooseCont = contacterList.firstElementChild
			}else if(toContacter==null) {
		        chooseCont = contacterList.firstElementChild
			}else {
		        chooseCont = contacterList.querySelector('[contact=c'+toContacter+']')
			}

            chooseCont.className = 'choosed'
			chooseCont.removeAttribute('newMessage')
			document.querySelector('#head-title').innerText = chooseCont.id
            var http = new XMLHttpRequest()
            http.onreadystatechange = function (data) {
                if (http.readyState == 4) {// 4 = "loaded"
                    if (http.status == 200) {// 200 = OK
                        // ...our code here...
						localMessage[chooseCont.id]=[]
						var data = JSON.parse(http.responseText)
						data.forEach(function (t) {
						    appendMessage(t,'#msgs')
							localMessage[chooseCont.id].push(t)
						})
						changeState(chooseCont.id)
                    }
                }
            }
            http.open('get', 'message'+'?from=' + chooseCont.id + '&type=0')
            http.send()
        }

        function appendMessage(mes,con) {
			var chooseCont = document.querySelector('.choosed')
			var contacter = chooseCont.id
			var msgList = document.querySelector(con)
			var msgContainer = document.createElement('div')
			if(mes.send===contacter){
			    msgContainer.class = 'msg robot'
				msgContainer.innerHTML = '<div class="msg-left" worker="客服1号">\n' +
                    '\t\t\t\t\t\t\t\t\t\t<div class="msg-host photo" style="background-image: url(images/1.jpg)"></div>\n' +
                    '\t\t\t\t\t\t\t\t\t\t<div class="msg-ball" ></div>\n' +
                    '\t\t\t\t\t\t\t\t\t</div>'
				msgContainer.querySelector('.msg-left').setAttribute('worker',mes.send)
				msgContainer.querySelector('.msg-ball').innerText = mes.content
			}else {
			    msgContainer.class = 'msg guest'
                msgContainer.innerHTML = '<div class="msg-right">\n' +
                    '\t\t\t\t\t\t\t\t\t\t<div class="msg-host photo" style="background-image: url(images/10.JPG)"></div>\n' +
                    '\t\t\t\t\t\t\t\t\t\t<div class="msg-ball" >你好</div>\n' +
                    '\t\t\t\t\t\t\t\t\t</div>'
                msgContainer.querySelector('.msg-ball').innerText = mes.content

			}
			msgList.appendChild(msgContainer)

        }

        var ws;
		var uid = '${sessionScope.uid}'
		var localMessage = {}
        function createWebsocket() {
			ws = new WebSocket('ws://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/websocket/'+uid+'/0')
            ws.onopen = function () {
                console.log("WebSocket连接发生成功");
            }
            ws.onmessage = function (data) {
                mes = data.data
                mes = JSON.parse(mes)
                if(mes.send==document.querySelector('.choosed').id) {
                    appendMessage(mes,'#msgs')
                    localMessage[mes.send].push(mes)
					changeState(document.querySelector('.choosed').id)
                }

				else if(contacterList.querySelector('[contact=c'+mes.send+']')==null){
                    var html = '<li name="contacter" >\n' +
                        '                             \n' +
                        '                                <a href="javascript:;">\n' +
                        '                                    <img src="images/4.JPG"></a><a href="javascript:;" class="chat03_name"></a>\n' +
                        '                            </li>'
					contacterList.innerHTML = html+contacterList.innerHTML
					var newCont = contacterList.firstElementChild
					newCont.id = mes.send
					newCont.setAttribute('contact','c'+mes.send)
					newCont.setAttribute('newMessage','true')
					newCont.querySelector('.chat03_name').innerText = mes.send
                }else {
				    if(localMessage[mes.send]!=undefined) {
                        localMessage[mes.send].push(mes)
					}
                    contacterList.querySelector('[contact=c'+mes.send+']').setAttribute('newMessage','true')
                }
            }
            window.onbeforeunload = function () {
                ws.close()
            }
        }

        function SendMsg() {
			var content = document.querySelector('#text').value
            document.querySelector('#text').value=''
			r ={}
			r.to = document.querySelector('#head-title').innerText
			r.content = content
			r.send=uid
			appendMessage(r,'#msgs')
            localMessage[r.to].push(r)
			r = JSON.stringify(r)
			ws.send(r)
        }

        function changeMain(e) {
			if(e.id!=document.querySelector('#head-title').innerText){
			    document.querySelector('#head-title').innerText = e.id
                document.querySelector('.choosed').removeAttribute('class')
                e.className = 'choosed'
                document.querySelector('#msgs').innerHTML = ''
			    if(!(e.id in localMessage)) {
			        localMessage[e.id] = []
                    var http = new XMLHttpRequest()
                    http.onreadystatechange = function (data) {
                        if (http.readyState == 4) {// 4 = "loaded"
                            if (http.status == 200) {// 200 = OK
                                // ...our code here...
                                var data = JSON.parse(http.responseText)
                                data.forEach(function (t) {
                                    appendMessage(t,'#msgs')
									localMessage[e.id].push(t)

                                })
                            }
                        }
                    }
                    http.open('get', 'message' + '?from=' + e.id + '&type=0')
                    http.send()
                }else {
			        localMessage[e.id].forEach(function (t) {
                        appendMessage(t,'#msgs')
					})
				}
				e.removeAttribute('newMessage')
				changeState(e.id)
			}
        }
        function changeState(from) {
            var http = new XMLHttpRequest()
            var from = document.querySelector('#head-title').innerText
            http.open('get', 'message' + '?from=' + from + '&type=1')
            http.send()
        }

        document.querySelector('#histStart').addEventListener('click',function () {
			var to = document.querySelector('#head-title').innerText
			$.ajax({
				url:'message',
				data:{
				    type:3,
					to:to
				},
				method:'get',
				success:function (data) {
					data = JSON.parse(data)
					if(localMessage[to]===undefined){
					    localMessage[to] = []
					}
					for(var i=Object.keys(data).length-1;i>=0;i--){
					    localMessage[to].unshift(data[i])
					}
					localMessage[to].forEach(function (t) {
					    appendMessage(t,'#msgs')
					})
                }
			})
        })
	</script>
	</body>

</html>