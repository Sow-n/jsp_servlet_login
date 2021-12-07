<%-- 
    Document   : show_result_page
    Created on : Dec 2, 2021, 9:59:21 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <body>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            
            String userid = (String) session.getAttribute("user");

            // neu khong co chuyen ve trang login
            if (userid == null) {
                request.setAttribute("errorMessage", "Access denied");
                request.getRequestDispatcher("./login_page.jsp").forward(request, response);
            }
        %>
        <h1>Xin chao ${sessionScope.user}</h1>

        <div align="center">
            <h1>Result Page</h1>
            <div>
                <!--<input class="btn_add" type="submit" value="MORE" onclick="window.location.href = 'current_date.jsp'; return false"/>-->
                
                <a href="current_date.jsp">More</a>
                    
                    <c:url var="logoutLink" value="UserControllerSevlet">
                        <c:param name="command" value="LOGOUT"/>
                    </c:url>
                    <a href="${logoutLink}" style="color: red" 
                       onclick="if (!(confirm('Sure?')))
                                   return false">LOGOUT</a>
            </div>

        </div>
    </body>
</html>
