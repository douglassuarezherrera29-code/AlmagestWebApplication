<%-- 
    Document   : usuarios_editar
    Created on : 10/12/2025, 09:11:59 PM
    Author     : dougl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Usuario</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script>
        // Validación cliente básica antes de enviar
        function validarFormulario() {
            const nombre = document.getElementById('nombre_usuario').value.trim();
            const correo = document.getElementById('correo').value.trim();
            const contrasena = document.getElementById('contrasena').value;
            const rol = document.getElementById('rol').value;

            // Nombre obligatorio
            if (!nombre) {
                alert('El nombre de usuario es obligatorio.');
                document.getElementById('nombre_usuario').focus();
                return false;
            }

            // Email si está presente, validar formato básico
            if (correo) {
                const re = /^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$/;
                if (!re.test(correo)) {
                    alert('Ingrese un correo electrónico válido.');
                    document.getElementById('correo').focus();
                    return false;
                }
            }

            // Si quiere cambiar contraseña, mínimo 6 caracteres (opcional)
            if (contrasena && contrasena.length > 0 && contrasena.length < 6) {
                alert('La contraseña debe tener al menos 6 caracteres.');
                document.getElementById('contrasena').focus();
                return false;
            }

            // Rol obligatorio
            if (!rol) {
                alert('Seleccione un rol.');
                document.getElementById('rol').focus();
                return false;
            }

            return true;
        }
    </script>
</head>
<body class="bg-light">
<div class="container py-4">

    <h2 class="mb-4">Editar Usuario</h2>

    <%-- Mensaje opcional desde el controlador --%>
    <%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
    %>
        <div class="alert alert-info"><%= mensaje %></div>
    <% } %>

    <%-- Cargar valores enviados por el controlador (UsuarioControlador debe setear estos atributos) --%>
    <%
        String id = (String) request.getAttribute("id");
        String nombre = (String) request.getAttribute("nombre_usuario");
        String correo = (String) request.getAttribute("correo");
        String rol = (String) request.getAttribute("rol");
        // Por seguridad no mostramos la contraseña actual; opcionalmente se puede dejar el campo vacío para cambiarla.
    %>

    <div class="card">
        <div class="card-body">
            <form action="UsuarioControlador" method="post" onsubmit="return validarFormulario();">
                <input type="hidden" name="accion" value="actualizar">
                <input type="hidden" name="id" value="<%= (id != null) ? id : "" %>">

                <div class="mb-3">
                    <label class="form-label">Nombre de usuario <span class="text-danger">*</span></label>
                    <input id="nombre_usuario" name="nombre_usuario" type="text" class="form-control"
                           value="<%= (nombre != null) ? nombre : "" %>" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Correo</label>
                    <input id="correo" name="correo" type="email" class="form-control"
                           value="<%= (correo != null) ? correo : "" %>">
                </div>

                <div class="mb-3">
                    <label class="form-label">Contraseña (dejar vacío si no desea cambiarla)</label>
                    <input id="contrasena" name="contrasena" type="password" class="form-control" placeholder="Nueva contraseña (opcional)">
                </div>

                <div class="mb-3">
                    <label class="form-label">Rol <span class="text-danger">*</span></label>
                    <select id="rol" name="rol" class="form-control" required>
                        <option value="">-- Seleccione --</option>
                        <option value="admin" <%= "admin".equals(rol) ? "selected" : "" %>>Administrador</option>
                        <option value="empleado" <%= "empleado".equals(rol) ? "selected" : "" %>>Empleado</option>
                        <option value="cliente" <%= "cliente".equals(rol) ? "selected" : "" %>>Cliente</option>
                    </select>
                </div>

                <div class="d-flex gap-2">
                    <button type="submit" class="btn btn-primary">Actualizar</button>
                    <a href="usuarios.jsp" class="btn btn-secondary">Cancelar</a>
                </div>
            </form>
        </div>
    </div>

</div>
</body>
</html>
