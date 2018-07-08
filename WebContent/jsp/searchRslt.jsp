<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

    <style type="text/css">
        table
        {
            border-collapse: collapse;
            margin: 0 auto;
            text-align: center;
            
        }
        table td, table th
        {
            
            color: #666;
            height: 30px;
            color: black;
        }
        table thead th
        {
            background-color: greenyellow;
            width: 100px;
        }
        table tr:nth-child(odd)
        {
            background: #fff;
            
        }
        table tr:nth-child(even)
        {
            background:ivory;
            
        }
    </style>

</head>
<body>
<jsp:include page="head.jsp"/>
<script type = "text/javascript">
    window.onload = function()
    {
        var e=${search};
        for (var i=0;i<e.length;i++)
        {
            var s=${search};
            var str=JSON.stringify(s);
            var result= JSON.parse(str+"");
            var para0=document.createElement("td");
            var para2=document.createElement("a");
            para2.href="jsp/goodsDescribed.jsp?gid="+result[i].id;
            var node0=document.createTextNode(result[i].name);
            para2.appendChild(node0);
            para0.appendChild(para2);
            var element=document.getElementById("goodsTab");
            element.appendChild(para0);
            var para0=document.createElement("td");
            var node0=document.createTextNode(result[i].owner);
            para0.appendChild(node0);
            var element=document.getElementById("goodsTab");
            element.appendChild(para0);
            var para0=document.createElement("td");
            var node0=document.createTextNode(result[i].price);
            para0.appendChild(node0);
            var element=document.getElementById("goodsTab");
            element.appendChild(para0);
            var para0=document.createElement("td");
            var node0=document.createTextNode(result[i].num);
            para0.appendChild(node0);
            var element=document.getElementById("goodsTab");
            element.appendChild(para0);
            var para0=document.createElement("td");
            var img=new Image();

            img.src=result[i].path;

            img.width=200;
            img.height=100;

            para0.appendChild(img);
            var element=document.getElementById("goodsTab");
            element.appendChild(para0);
            var para0=document.createElement("td");
            var node0=document.createTextNode(result[i].score);
            para0.appendChild(node0);
            var element=document.getElementById("goodsTab");
            element.appendChild(para0);
            var para0=document.createElement("tr");
            var element=document.getElementById("goodsTab");
            element.appendChild(para0);
        }
    }
</script>
<table align="center" width="600" border="0" height="180" id="goodsTab"
         cellpadding="1" cellspacing="1">
    <tr bgcolor="white" >
        <td align="center" colspan="7" style="color: green;">
            <h2>商品信息</h2>
        </td>
    </tr>
    <tr align="center" >
        <td style="color: green;"><b>商品名</b></td>
        <td style="color: green;"><b>商家</b></td>
        <td style="color: green;"><b>价格</b></td>
        <td style="color: green;"><b>剩余数量</b></td>
        <td style="color: green;"><b>图片</b></td>
        <td style="color: green;"><b>评分</b></td>

    </tr>
</table> 
</body>
</html>