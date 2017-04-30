<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 26.04.17
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>admin</title>
</head>
<body>

<c:import url="/jsp/navigate.jsp"/>
<%--<%@include file="/jsp/navigate.jsp" %>--%>

    <form action="/add.jsp" method="post">
        <input type="submit" name="add" value="Добавить нового пользователя" />
    </form>
<%--<form action="/register" method="post">--%>
    <%--<input type="submit" name="delete" value="Удалить" />--%>
<%--</form>--%>

    <table border="1">
        <tr>
            <th>id</th>
            <th>login</th>
            <th>password</th>
            <th>mail</th>
            <th>role_id</th>
        </tr>

        <c:forEach items="${requestScope.users}" var="admin">
            <tr>
                <td><c:out value="${admin.id}"></c:out></td>
                <td><c:out value="${admin.login}"></c:out></td>
                <td><c:out value="${admin.password}"></c:out></td>
                <td><c:out value="${admin.mail}"></c:out></td>
                <td><c:out value="${admin.role_id}"></c:out></td>
                <td>
                     <form method="post" action="/delete">
                            <button type="submit" value="${admin.id}" name="delete">Удалить</button>
                     </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>