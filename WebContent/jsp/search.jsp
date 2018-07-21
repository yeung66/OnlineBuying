<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<style>
#sachCon {

	margin-left:100px;


}
#search {
	border: none;
	background-color: lightgoldenrodyellow;
	position: relative;
	top:-17px;
	left:0px;
	outline: medium;

}
#searchbtn {
	position: relative;
	top:0px;
	left:0px;
	outline: medium;
}</style>
</head>
<body>
<jsp:include page="head.jsp"/>
<div id="sachCon">
<form action="SearchServlet" method="post" style="display:inline;">
			<input type="text" id="search" name="search" placeholder="搜索你感兴趣的" 
				style="width: 300px; height: 50px" /> 
				<input type="image" src="images/search.jpg" id="searchbtn"
				 style="width: 50px; height: 50px" id="go" 
				title="Search" />
</form>
</div>
</body>

</html>