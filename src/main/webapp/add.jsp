<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>add user</title>
</head>
<body>
<%=request.getAttribute("is_adding")%>
<form action="/listUser.jsp" method="post">
    id<input type="text" name="id" /><br/>
    login<input type="text" name="login" /><br/>
    password<input type="password" name="password" /><br/>
    mail<input type="text" name="mail" /><br/>
    role_id<input type="text" name="role_id" />
    <input type="submit" value="добавить" name="add" />
</form>
</body>
</html>
