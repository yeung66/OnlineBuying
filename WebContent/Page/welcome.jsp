<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
<%@page import="segmenter.*" %>	
<%@page import="util.*" %>
<%@page import="server.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>

			<input type="text" id="search" name="search" placeholder="search" 
				style="width: 150px; height: 30px" /> 
				<input type="button" 
				value="搜索" style="width: 60px; height: 30px" id="go" alt="Search"
				title="Search" onclick="Search()"/>
				<br/>
			<div id="txtHint"></div>

<div>


</div>
</body>
<script type="text/javascript">
function Search(){
	alert('请重试！');
		 $.ajax({
			 data : {
					'search' : $("#search").val(),
					'method':'Search'
				},  dataType: 'JSON',
			     async : false ,

		     url : '/SearchServlet',
		     success: function(data){
		    	 alert('请重试！');
		    	 for(var i=0;i<data.length;i++){
		    		 document.write( data[i].name);
		    	 }
		    },
				error : function() {
					alert('请重试！');
					window.location.reload();
				}
		   })
}
</script>
</html>