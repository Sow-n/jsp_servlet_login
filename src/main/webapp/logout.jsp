
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        // Xoa session 
        session.setAttribute("user", null);
        session.setAttribute("pass", null);
        // Lay session hien tai
        String userid = (String) session.getAttribute("user");
        // Neu session null
        if (userid == null) {
            request.setAttribute("errorMessage", "Logged out");
            // Quay ve
            request.getRequestDispatcher("./login_page.jsp").forward(request, response);
        }
    %>
    <body>
        <h1>Hello World!</h1><%=userid%>
    </body>
</html>
