<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 24.04.17
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table border="1">
        <c:forEach items="${requestScope.users}" var="user">
            <tr>
                <td><c:out value="${user.id}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
