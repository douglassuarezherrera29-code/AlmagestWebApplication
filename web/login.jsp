<%-- 
    Document   : Login
    Created on : 2/12/2025, 09:38:58 PM
    Author     : dougl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Inicio de Sesión</title>
</head>
<body>

<h2>Login Almagest</h2>

<form action="LoginServlet" method="POST">
    <label>Nombre de usuario:</label><br>
    <input type="text" name="usuario" required><br><br>

    <label>Contraseña:</label><br>
    <input type="password" name="password" required><br><br>

    <button type="submit">Ingresar</button>
</form>

<% 
    String mensaje = (String) request.getAttribute("mensaje");
    if (mensaje != null) {
        out.println("<p style='color:red;'>" + mensaje + "</p>");
    }
%>

</body>
</html>
