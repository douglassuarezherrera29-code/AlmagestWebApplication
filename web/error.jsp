<%-- 
    Document   : error
    Created on : 2/12/2025, 10:17:54 PM
    Author     : dougl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Error</title></head>
<body>

<h2>Error</h2>
<p style="color:red;"><%= request.getAttribute("mensaje") %></p>

<a href="ClientesServlet">Volver</a>

</body>
</html>
