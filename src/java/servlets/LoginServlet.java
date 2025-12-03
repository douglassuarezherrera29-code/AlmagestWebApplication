/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import util.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        try {
            Conexion con = new Conexion();
            Connection cn = con.obtenerconexion();

            String sql = "SELECT * FROM usuarios WHERE nombre_usuario=? AND contraseña=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                request.setAttribute("user", rs.getString("nombre_usuario"));
                request.getRequestDispatcher("inicio.jsp").forward(request, response);
            } else {
                request.setAttribute("mensaje", "Usuario o contraseña incorrectos");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            System.out.println("Error Login: " + e.toString());
            request.setAttribute("mensaje", "Error interno del servidor");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
