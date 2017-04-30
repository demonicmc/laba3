<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 28.04.17
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="/css/form.css" %>
    </style>
    <!--<script type="text/javascript" src="jquery-1.3.2.min.js"></script>-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <%--<script src="js/2.5.3-crypto-md5.js"></script>--%>

    <script>
        $(document).ready(function() {
            $("#register").click(function() {
                var login = $("#login").val();
//                var email = $("#email").val();
                var password = $("#password").val();
                var password2 = $("#password2").val();
                var email = $("#email").val();
                console.log(password);
                console.log(password2);
                console.log(login);
                if (login == ''  || password    == '' || password2 == '' || email == '') {
                    alert("Please fill all fields...!!!!!!");
                } else if (!jQuery.isEmptyObject(password) && (password.length) < 4)  {
                    alert("Password should atleast 4 character in length...!!!!!!");
                } else if (password.localeCompare(password2) != 0) {
                    alert("Your passwords don't match. Try again?");
                } else {
                    $.post("/register", {
                        login: login,
                        password:password,
//                        password2:password2,
                        email: email
                    }, function(data) {
                        if (data == 'You have Successfully Registered.....') {
//                            $("form")[0].reset();
                        }
//                        alert(data);
                        window.location.replace("/");
                    });
                }
            });
        });
    </script>
</head>
<body>
    <form action="/login.jsp"
          id="registration" method="post" accept-charset="utf-8" name="registration">
        <div class="main">
            <div class="textbox">
                Введите логин:<input type="text" maxlength="20" name="login" id="login"
                                     required  title="Для заполнения используйте буквы" />
            </div>
            <div class="textbox">
                Введите пароль:<input type="password" id="password" minlength ="4" maxlength= "8"
                                      name="password" required  />
            </div>

            <div class="textbox">
                Повторите пароль:
                <input type="password" id="password2" minlength ="4" maxlength ="8" name="password2"
                       required  /> <br />
            </div>
            <div class="textbox">
                email:<br />
                <input type="email" id="email" name="email" required />
            </div>
        </div>
        <input type="submit" name="add" id="register" value="Регистрация"
               class="button"  />
    </form>
</body>
</html>
