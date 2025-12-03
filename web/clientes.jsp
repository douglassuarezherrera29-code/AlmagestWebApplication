<%-- 
    Document   : clientes
    Created on : 2/12/2025, 10:12:08 PM
    Author     : dougl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Clientes - CRUD</title>
</head>
<body>

<h2>Registrar Cliente</h2>

<form action="ClientesServlet" method="POST">
    <input type="hidden" name="accion" value="insertar">

    Nombre: <input type="text" name="nombre" required><br><br>
    Correo: <input type="email" name="correo"><br><br>
    Teléfono: <input type="text" name="telefono"><br><br>

    <button type="submit">Guardar</button>
</form>

<hr>

<h2>Listado de Clientes</h2>

<table border="1" cellpadding="8">
    <tr>
        <th>ID</th><th>Nombre</th><th>Correo</th><th>Teléfono</th><th>Acciones</th>
    </tr>

<%
    java.util.List lista = (java.util.List) request.getAttribute("clientes");
    if (lista != null) {
        for (Object o : lista) {
            java.util.Map c = (java.util.Map) o;
%>

<tr>
    <td><%= c.get("id") %></td>
    <td><%= c.get("nombre") %></td>
    <td><%= c.get("correo") %></td>
    <td><%= c.get("telefono") %></td>
    <td>
        <a href="ClientesServlet?accion=editar&id=<%= c.get("id") %>">Editar</a> |
        <a href="ClientesServlet?accion=eliminar&id=<%= c.get("id") %>"
           onclick="return confirm('¿Eliminar cliente?');">Eliminar</a>
    </td>
</tr>

<%
        }
    }
%>
</table>

<br>
<a href="inicio.jsp">Volver</a>

</body>
</html>
