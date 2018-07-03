<%@ page import="util.Database" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: 杨浩麟
  Date: 2018/7/3
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <%
    Database db=new Database();
    Connection c = db.getConnect();
    Statement st=c.createStatement();
    ResultSet rs = st.executeQuery("select * from users");
    rs.next();
    out.print(rs.getString(1));
  %>
  </body>
</html>
