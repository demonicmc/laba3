<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 27.04.17
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <fieldset>
        <h3>Вы зашли, как ${sessionScope.userLogin}</h3>
        <form action="/login" method="post">
            <button type="submit" value="exit" name="action">Выйти из текущего пользователя</button>
        </form>
        <a class="block_button" tabindex="1" href="${pageContext.request.contextPath}/listUser">Перейти к списку пользователей</a>
        <a class="block_button" tabindex="1" href="${pageContext.request.contextPath}/admin">Перейти к панели администратора</a>

    </fieldset>

</body>
</html>
