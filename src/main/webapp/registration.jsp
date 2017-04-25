<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 24.04.17
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page errorPage="error.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="form.css">
    <title>Регистрация</title>
</head>
<body>
<form action="/loggin" method="post" accept-charset="utf-8" name="registration">
    <div class="main">
        <div class="textbox">
            Введите логин:<input type="text" maxlength="20" name="login"
                                 required pattern="[A-Za-zА-Яа-яЁё0-9]+$" title="Для заполнения используйте буквы" />
        </div>
        <div class="textbox">
            Введите пароль:<input type="password" minlength ="4" maxlength= "8"
                                  name="password" required pattern="[A-Za-zА-Яа-яЁё0-9]+$" />
        </div>

        <div class="textbox">
            Повторите пароль:
            <input type="password" minlength ="4" maxlength ="8" name="password"
                   required pattern="[A-Za-zА-Яа-яЁё0-9]+$" /> <br />
        </div>
        <div class="textbox">
            email:<br />
            <input type="email" name="email" required />
        </div>
    </div>
    <input type="submit" name="add" value="Регистрация"  class="button" />
</form>
</body>
</html>
