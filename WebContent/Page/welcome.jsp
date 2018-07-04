<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
<form action="SearchServlet" method="post">
			<input type="text" id="search" name="search" placeholder="search" 
				style="width: 150px; height: 30px" /> <input type="submit" 
				value="搜索" style="width: 60px; height: 30px" id="go" alt="Search"
				title="Search" />
				<br/>
			<div id="txtHint"></div>
</form>
<div>
<c:forEach items="${searchs}" var="search" varStatus="st"  >
    
<div>${st.count}.${search}</div>
    
</c:forEach>
</div>

</body>
</html>