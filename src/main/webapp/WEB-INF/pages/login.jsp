<%--
  Created by IntelliJ IDEA.
  User: 赖小燚
  Date: 2021/11/25
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <%
        request.setAttribute("path",request.getContextPath());
    %>
    <form action="${requestScope.path}/user/login" method="post">
        <input type="text" name="username" value="louis"/><br/>
        <input type="text" name="password" value="123456"/><br/>
        <input type="submit" value="登录"/><br/>
    </form>
</body>
</html>
