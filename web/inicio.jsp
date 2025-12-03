<%-- 
    Document   : inicio
    Created on : 2/12/2025, 09:49:10 PM
    Author     : dougl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Inicio</title>
</head>
<body>
    <h2>Bienvenido al sistema Almagest</h2>

    <p>Usuario: <%
    String user = (String) request.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
</p>

    <a href="login.jsp">Cerrar sesión</a>
</body>
</html>
