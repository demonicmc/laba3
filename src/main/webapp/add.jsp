<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>add user</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <%--<script src="js/2.5.3-crypto-md5.js"></script>--%>

    <script>
        function checkPars() {
            var login = $("#login").val();
            var password = $("#password").val();
            var email = $("#email").val();

            if (login == ''  || password    == '' || cpassword == '') {
                alert("Please fill all fields...!!!!!!");
            } else {
                document.getElementById('add').submit();
            }
        }

    </script>
</head>
<body>
    <form action="/register" id="add" method="post">
        login<input type="text" id="login" name="login" /><br/>
        password<input type="password" id="password" name="password" /><br/>
        mail<input type="email" name="email" id="email" /><br/>
        <input type="submit" value="добавить" id="addUser" name="action" onclick="checkPars()" />
    </form>
</body>
</html>
