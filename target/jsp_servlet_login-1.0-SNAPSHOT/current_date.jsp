<%-- 
    Document   : current_date
    Created on : Dec 6, 2021, 11:04:52 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Current Date Page</title>
    </head>

    <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
       
        Date today = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String ddMMyyyy = dateFormat.format(today);

        String userid = (String) session.getAttribute("user");
        // neu khong co chuyen ve trang login
        if (userid == null) {
            request.setAttribute("errorMessage", "Access denied");
            request.getRequestDispatcher("./login_page.jsp").forward(request, response);
        }
    %>
    <body>

        <!--<input class="btn_add" type="submit" value="BACK" onclick="window.location.href = 'show_result_page.jsp'; return false"/>-->

        <div align="center">
            <h1>Current Date</h1>
            <h3> Hôm nay là ngày: <%= ddMMyyyy%></h3>
            <a href="./show_result_page.jsp">Back</a>
        </div>
    </body>
</html>
