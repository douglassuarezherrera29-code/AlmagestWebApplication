<%-- 
    Document   : clientes_editar
    Created on : 2/12/2025, 10:17:04 PM
    Author     : dougl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Cliente</title>
</head>
<body>

<h2>Editar Cliente</h2>

<form action="ClientesServlet" method="POST">
    <input type="hidden" name="accion" value="actualizar">
    <input type="hidden" name="id" value="<%= request.getAttribute("id") %>">

    Nombre: <input type="text" name="nombre" value="<%= request.getAttribute("nombre") %>" required><br><br>
    Correo: <input type="email" name="correo" value="<%= request.getAttribute("correo") %>"><br><br>
    Teléfono: <input type="text" name="telefono" value="<%= request.getAttribute("telefono") %>"><br><br>

    <button type="submit">Actualizar</button>
</form>

<br>
<a href="ClientesServlet">Cancelar</a>

</body>
</html>
