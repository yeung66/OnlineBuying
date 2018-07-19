<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ page import="util.*"%>
<%@ page import="vo.Product" %>
<%@ page import="DAO.ProductDAO" %>
<%
	String uid = (String) session.getAttribute("uid");
	String rights = (String) session.getAttribute("type");
	if(uid==null || !rights.equals("2")){
	    response.setCharacterEncoding("utf-8");
	    response.getWriter().write("请以管理员身份登录再操作！");
	    return;
	}
	List<Product> plist = ProductDAO.getNotExamineProducts();
	int i = 0;
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>管理员审核商品界面</title>
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
<script src="../js/jquery.min.js"></script>

</head>

<body data-type="widgets">
	<script src="../js/theme.js"></script>
	<div class="am-g tpl-g">
		<!-- 头部 -->
		<header>
			<!-- 商家logo -->
			<!--<div class="am-fl tpl-header-logo">
                <a href="javascript:;"><img src="img/logo.png" alt=""></a>
            </div>-->
			<!-- 右侧内容 -->
			<div class="tpl-header-fluid">
				<!-- 侧边切换 -->
				<!--<div class="am-fl tpl-header-switch-button am-icon-list">
                    <span>

                </span>
                </div>-->
				<!-- 搜索 -->
				<!--<div class="am-fl tpl-header-search">
                    <form class="tpl-header-search-form" action="javascript:;">
                        <button class="tpl-header-search-btn am-icon-search"></button>
                        <input class="tpl-header-search-box" type="text" placeholder="搜索内容...">
                    </form>
                </div>-->
				<!-- 其它功能-->
				<div class="am-fr tpl-header-navbar">
					<ul>
						<!-- 账号设置-->
						<li><a href="javascript:;" class="tpl-user-panel-action-link">
								<!--<span class="am-icon-pencil"></span>--> 账号设置
						</a></li>




						




						<!-- 退出登陆 -->
						<li class="am-text-sm"><a href="javascript:;"> <span
								class="am-icon-sign-out"></span> 退出
						</a></li>
					</ul>
				</div>
			</div>

		</header>

		


		<!-- 内容区域 -->
		<!--
        	作者：sandysandrawu@163.com
        	时间：2018-07-05
        	描述：tpl-content-wrapper调整左右margin
        -->

		<div class="tpl-content-wrapper">
			<div class="row-content am-cf">
				<div class="row">
					<div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
						<div class="widget am-cf">
							<div class="widget-head am-cf">
								<div class="widget-title  am-cf">商品审核列表</div>


							</div>
							<div class="widget-body  am-fr">

								<div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
									<div class="am-form-group">
										<div class="am-btn-toolbar">
											<div class="am-btn-group am-btn-group-xs">
												<!--<button type="button"
													class="am-btn am-btn-default am-btn-success">
													<span class="am-icon-plus"></span> 新增
												</button>-->
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
												<th>商品类型</th>
												<th>商品库存</th>
												<th>商家名称</th>
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
												<td><img src="../<%=p.getPath() %>" class="tpl-table-line-img"
													alt=""></td>
												<td class="am-text-middle"><%=p.getName() %></td>
												<td class="am-text-middle"><%=p.getPrice() %></td>
												<td class="am-text-middle"><%=p.getType() %></td>
												<td class="am-text-middle"><%=p.getNum() %></td>
												<td class="am-text-middle"><%=p.getScore() %></td>
												<td class="am-text-middle">
													<div class="tpl-table-black-operation">
														
														<select name="status">
                                                       	<option value ="pass">通过</option>
                                                       	<option value ="fail">不通过</option>
                                                       </select>
                                                       
														<a id="<%=p.getId()%>" type="confirmLink"
															class="tpl-table-black-operation-del"> <i
															></i> 提交
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
	</div>
	</div>
	<script src="../js/amazeui.min.js"></script>
	<script src="../js/app.js"></script>
	<script>
		var links = document.querySelectorAll('[type="confirmLink"]')
		links.forEach(function (link) {
		    link.onclick=function () {
				$.ajax({
					data:{
					    status:link.previousElementSibling.value,
					    id:link.id,
					},
					url:'../adminProduct',
					success:function (data) {
						if(data=='success'){
						    alert('审核成功');
						}else {
						    alert('审核失败');
						}
						window.location.reload();
                    }
				})
            }
		})
	</script>

</body>
</html>