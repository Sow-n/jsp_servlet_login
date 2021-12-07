<%-- 
    Document   : login_page
    Created on : Dec 2, 2021, 9:49:19 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <div align="center">
            <h1>Login Form</h1>
            <form action="UserControllerServlet" method="post">
                <input type="hidden" name="command" value="LOGIN"/>
                <table style="with: 100%">
                    <tr>
                        <td></td>
                        <td style="color: red;">${errorMessage}</td>
                    </tr>
                    <tr>
                        <td>UserName</td>
                        <td><input type="text" name="username" /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="password" /></td>
                    </tr>

                </table>
                <input type="submit" value="LOGIN"/>
            </form>
        </div>
    </body>
</html>
