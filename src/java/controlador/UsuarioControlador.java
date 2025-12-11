/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modeloUsuarioVO.UsuarioVO;
import modeloUsuarioDAO.UsuarioDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UsuarioControlador")
public class UsuarioControlador extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre_usuario");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        String rol = request.getParameter("rol");

        UsuarioVO usuVO = new UsuarioVO(id, nombre, correo, contrasena, rol);
        UsuarioDAO usuDAO = new UsuarioDAO(usuVO);

        switch (accion) {
            case "agregar":
                if (usuDAO.agregarRegistro()) {
                    request.setAttribute("mensaje", "Usuario registrado correctamente");
                }
                break;

            case "actualizar":
                if (usuDAO.actualizarRegistro()) {
                    request.setAttribute("mensaje", "Usuario actualizado");
                }
                break;

            case "eliminar":
                if (usuDAO.eliminarRegistro()) {
                    request.setAttribute("mensaje", "Usuario eliminado");
                }
                break;
        }

        request.getRequestDispatcher("usuarios.jsp").forward(request, response);
    }
}
