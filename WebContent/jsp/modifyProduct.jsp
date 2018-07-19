<!--
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
-->

<%@ page import="util.*"%>
<%@ page import="vo.Product"%>
<%@ page import="DAO.ProductDAO" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	int pid = Integer.parseInt(request.getParameter("pid"));
	Product p = ProductDAO.getProductInfo(pid);
	int number = p.getNum();
	String name = p.getName();
	String described = p.getInformation();
	Double price = p.getPrice();
	String type = p.getType();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>修改商品</title>
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/png" href="i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <script src="../js/echarts.min.js"></script>
    <link rel="stylesheet" href="../css/amazeui.min.css" />
    <link rel="stylesheet" href="../css/amazeui.datatables.min.css" />
    <link rel="stylesheet" href="../css/app.css">
    <script src="../js/jquery.min.js"></script>
    </head>
    <body>
    	<body data-type="widgets">
    <script src="../js/theme.js"></script>
    <div class="am-g tpl-g">
        <!-- 头部 -->
        <header>
           
            <!-- 右侧内容 -->
            <div class="tpl-header-fluid">

                <!-- 其它功能-->
                <div class="am-fr tpl-header-navbar">
               
                        <!-- 退出 -->
                        <li class="am-text-sm">
                            <a href="javascript:;">
                                <span class="am-icon-sign-out"></span> 退出
                            </a>
                        </li>
                    
                </div>
            </div>

        </header>
        <!-- 风格切换 -->
        <!--<div class="tpl-skiner">
            <div class="tpl-skiner-toggle am-icon-cog">
            </div>
            <div class="tpl-skiner-content">
                <div class="tpl-skiner-content-title">
                    选择主题
                </div>
                <div class="tpl-skiner-content-bar">
                    <span class="skiner-color skiner-white" data-color="theme-white"></span>
                    <span class="skiner-color skiner-black" data-color="theme-black"></span>
                </div>
            </div>
        </div>-->


        <!-- 内容区域 -->
        <div class="tpl-content-wrapper">
            <div class="row-content am-cf">
                <div class="row">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="widget am-cf">
                            <div class="widget-head am-cf">
                                <div class="widget-title am-fl">修改商品</div>
                               
                            </div>
                            <div class="widget-body am-fr">


                                <form class="am-form tpl-form-border-form tpl-form-border-br" action="<%=basePath%>/AlterProductServlet" method="post">
                                	<input type="hidden" name="pid" value="<%=pid%>">
                                    <div class="am-form-group">
                                        <label for="user-name" class="am-u-sm-3 am-form-label">商品名称 <span class="tpl-form-line-small-title">Name</span></label>
                                        <div class="am-u-sm-9">
                                            <input type="text" class="tpl-form-input" id="user-name" value="<%=name %>" name="name">                                           
                                        </div>
                                    </div>
                                    
                                    <div class="am-form-group">
                                        <label for="user-name" class="am-u-sm-3 am-form-label">商品价格 <span class="tpl-form-line-small-title">Price</span></label>
                                        <div class="am-u-sm-9">

                                            <input type="text" class="tpl-form-input" id="user-name" value="<%=price %>" name="price">
                                        </div>
                                    </div>
                                    
                                    <div class="am-form-group">
                                        <label for="user-name" class="am-u-sm-3 am-form-label">商品类型 <span class="tpl-form-line-small-title">Type</span></label>
                                        <div class="am-u-sm-9">

                                            <input type="text" class="tpl-form-input" id="user-name" value="<%=p %>" name="type">
                                        </div>
                                    </div>
                                    
                                    <div class="am-form-group">
                                        <label for="user-name" class="am-u-sm-3 am-form-label">商品类型 <span class="tpl-form-line-small-title">Type</span></label>
                                        <div class="am-u-sm-9">

                                            <input type="text" class="tpl-form-input" id="user-name" value="<%=type %>" name="type">                                           
                                        </div>
                                    </div>



                                    <div class="am-form-group">
                                        <label class="am-u-sm-3 am-form-label">库存 <span class="tpl-form-line-small-title">Number</span></label>
                                        <div class="am-u-sm-9">

                                            <input type="text" value="<%=number %>" name="num">
                                        </div>
                                    </div>


 

                                    <div class="am-form-group">
                                        <label for="user-intro" class="am-u-sm-3 am-form-label">商品描述</label>
                                        <div class="am-u-sm-9">

                                            <textarea class="" rows="10" id="user-intro" value="" name="info"><%if(p.getInformation()!=null) out.println(p.getInformation());%></textarea>


                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <div class="am-u-sm-9 am-u-sm-push-3">

                                            <button type="submit" class="am-btn am-btn-primary tpl-btn-bg-color-success ">提交</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

               


            </div>
        </div>
    </div>
    </div>
    <script src="../js/amazeui.min.js"></script>
    <script src="../js/amazeui.datatables.min.js"></script>
    <script src="../js/dataTables.responsive.min.js"></script>
    <script src="../js/app.js"></script>


 	</body>
</html>