<%--
  Created by IntelliJ IDEA.
  User: set
  Date: 23.04.17
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ page import="com.laba3.dao.UserDao " %>--%>
<%--<%@ page import="com.laba3.dao.UserDaoImp" %>--%>
<%--<%@ page import="com.laba3.ConnectBase" %>--%>
<%--<%@ page import="com.laba3.pojo.User" %>--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Вход</title>

</head>
<body>
<c:if test="${empty userId}">
    <%--<div>Войдите в систему</div>--%>
    <c:redirect url="/login"/>
</c:if>
</body>
</html>