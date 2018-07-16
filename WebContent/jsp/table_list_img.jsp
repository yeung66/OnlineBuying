<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ page import="util.*"%>
<%@ page import="vo.Product" %>
<%
	String uid = (String) session.getAttribute("uid");
	List<Product> plist = Product.getProductList(uid);
	int i = 0;
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>商家商品管理界面</title>
<meta name="description" content="这是一个 index 页面">
<meta name="keywords" content="index">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="i/favicon.png">
<link rel="apple-touch-icon-precomposed" href="i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet" href="../css/amazeui.min.css" />
<link rel="stylesheet" href="../css/amazeui.datatables.min.css" />
<link rel="stylesheet" href="../css/app.css">
<%--<script src="../js/jquery.min.js"></script>--%>

</head>

<body data-type="widgets">
	<script src="../js/theme.js"></script>
	<!--插入head-->
	<jsp:include page="head.jsp"></jsp:include>
	<%--<div class="am-g tpl-g">--%>
		<!-- 头部 -->
		<%--<header>--%>
			<%--<!-- 商家logo -->--%>
			<%--<!--<div class="am-fl tpl-header-logo">--%>
                <%--<a href="javascript:;"><img src="img/logo.png" alt=""></a>--%>
            <%--</div>-->--%>
			<%--<!-- 右侧内容 -->--%>
			<%--<div class="tpl-header-fluid">--%>
				<%--<!-- 侧边切换 -->--%>
				<%--<!--<div class="am-fl tpl-header-switch-button am-icon-list">--%>
                    <%--<span>--%>

                <%--</span>--%>
                <%--</div>-->--%>
				<%--<!-- 搜索 -->--%>
				<%--<!--<div class="am-fl tpl-header-search">--%>
                    <%--<form class="tpl-header-search-form" action="javascript:;">--%>
                        <%--<button class="tpl-header-search-btn am-icon-search"></button>--%>
                        <%--<input class="tpl-header-search-box" type="text" placeholder="搜索内容...">--%>
                    <%--</form>--%>
                <%--</div>-->--%>
				<%--<!-- 其它功能-->--%>
				<%--<div class="am-fr tpl-header-navbar">--%>
					<%--<ul>--%>
						<%--<!-- 账号设置-->--%>
						<%--<li><a href="javascript:;" class="tpl-user-panel-action-link">--%>
								<%--<!--<span class="am-icon-pencil"></span>--> 账号设置--%>
						<%--</a></li>--%>




						<%--<!--<li class="tpl-dropdown-menu-messages">--%>
                                    <%--<a href="javascript:;" class="tpl-dropdown-menu-messages-item am-cf">--%>
                                        <%--<div class="menu-messages-ico">--%>
                                            <%--<img src="img/user02.png" alt="">--%>
                                        <%--</div>--%>
                                        <%--<div class="menu-messages-time">--%>
                                            <%--5天前--%>
                                        <%--</div>--%>
                                        <%--<div class="menu-messages-content">--%>
                                            <%--<div class="menu-messages-content-title">--%>
                                                <%--<i class="am-icon-circle-o am-text-warning"></i>--%>
                                                <%--<span>禁言小张</span>--%>
                                            <%--</div>--%>
                                            <%--<div class="am-text-truncate"> 为了能最准确的传达所描述的问题， 建议你在反馈时附上演示，方便我们理解。 </div>--%>
                                            <%--<div class="menu-messages-content-time">2016-09-16 上午 09:23</div>--%>
                                        <%--</div>--%>
                                    <%--</a>--%>
                                <%--</li>--%>
                                <%--<li class="tpl-dropdown-menu-messages">--%>
                                    <%--<a href="javascript:;" class="tpl-dropdown-menu-messages-item am-cf">--%>
                                        <%--<i class="am-icon-circle-o"></i> 进入列表…--%>
                                    <%--</a>--%>
                                <%--</li>--%>
                            <%--</ul>--%>
                        <%--</li>-->--%>




						<%--<!-- 退出登陆 -->--%>
						<%--<li class="am-text-sm"><a href="javascript:;"> <span--%>
								<%--class="am-icon-sign-out"></span> 退出--%>
						<%--</a></li>--%>
					<%--</ul>--%>
				<%--</div>--%>
			<%--</div>--%>

		<%--</header>--%>

		<!-- 侧边导航栏 -->
		<!--<div class="left-sidebar">-->
		<!-- 商家信息 -->
		<!--<div class="tpl-sidebar-user-panel">
                <div class="tpl-user-panel-slide-toggleable">
                    商家头像
                    	<div class="tpl-user-panel-profile-picture">
                        <img src="assets/img/user04.png" alt="">
                    </div>
                    
                    <span class="user-panel-logged-in-text">
              <i class="am-icon-circle-o am-text-success tpl-user-panel-status-icon"></i>
              商家名称
          </span>
                    <a href="javascript:;" class="tpl-user-panel-action-link"> <span class="am-icon-pencil"></span> 账号设置</a>
                </div>
            </div>
            -->


		<!-- 内容区域 -->
		<!--
        	作者：sandysandrawu@163.com
        	时间：2018-07-05
        	描述：tpl-content-wrapper调整左右margin
        -->

		<div class="tpl-content-wrapper" style="padding-top: 180px">
			<div class="row-content am-cf">
				<div class="row">
					<div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
						<div class="widget am-cf">
							<div class="widget-body  am-fr">
								<div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
									<div class="am-form-group">
										<div class="am-btn-toolbar">
											<div class="am-btn-group am-btn-group-xs">
												<button type="button"
													class="am-btn am-btn-default am-btn-success" onclick="window.location.href='jsp/addProduct.jsp'">
													<span class="am-icon-plus"></span>新增

												</button>
												<!--<button type="button" class="am-btn am-btn-default am-btn-secondary"><span class="am-icon-save"></span> 保存</button>
                                                <button type="button" class="am-btn am-btn-default am-btn-warning"><span class="am-icon-archive"></span> 审核</button>
                                                <button type="button" class="am-btn am-btn-default am-btn-danger"><span class="am-icon-trash-o"></span> 删除</button>-->
											</div>
										</div>
									</div>
								</div>



								<div class="am-u-sm-12">
									<table width="100%"
										class="am-table am-table-compact am-table-striped tpl-table-black ">
										<thead>
											<tr>
												<th>商品图片</th>
												<th>商品名称</th>
												<th>商品价格</th>
												<th>商品库存</th>
												<th>商品状态</th>
												<th>商品评分</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<%for(Product p: plist){
													if(i % 2 == 0){%>
													<tr class="gradeX">
													<%}else{ %>
													<tr class="even gradeC">
												<%}%>
												<td><img src="<%=p.getPath() %>" class="tpl-table-line-img"
													alt=""></td>
												<td class="am-text-middle"><%=p.getName() %></td>
												<td class="am-text-middle"><%=p.getPrice() %></td>
												<td class="am-text-middle"><%=p.getNum() %></td>
												<td class="am-text-middle"><%=p.getStatus() %></td>
												<td class="am-text-middle"><%=p.getScore() %></td>
												<td class="am-text-middle">
													<div class="tpl-table-black-operation">
														<a href="jsp/modifyProduct.jsp?pid=<%=p.getId()%>"> <i class="am-icon-pencil"></i>
															编辑
														</a> <a href="DeleteProductServlet?id=<%=p.getId()%>"

															class="tpl-table-black-operation-del"> <i
															class="am-icon-trash"></i> 删除
														</a>
													</div>
												</td>
											</tr>
											<%} %>


										</tbody>
									</table>
								</div>
								<!--<div class="am-u-lg-12 am-cf">

                                    <div class="am-fr">
                                        <ul class="am-pagination tpl-pagination">
                                            <li class="am-disabled"><a href="#">«</a></li>
                                            <li class="am-active"><a href="#">1</a></li>
                                            <li><a href="#">2</a></li>
                                            <li><a href="#">3</a></li>
                                            <li><a href="#">4</a></li>
                                            <li><a href="#">5</a></li>
                                            <li><a href="#">»</a></li>
                                        </ul>
                                    </div>
                                </div>-->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	<%--</div>--%>
	<%--</div>--%>
	<script src="js/amazeui.min.js"></script>
	<script src="js/app.js"></script>

</body>
</html>