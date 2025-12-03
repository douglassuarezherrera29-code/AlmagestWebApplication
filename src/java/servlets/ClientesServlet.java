package servlets;

import util.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ClientesServlet")
public class ClientesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            accion = "listar";
        }

        switch (accion) {

            case "editar":
                mostrarFormularioEditar(request, response);
                break;

            case "eliminar":
                eliminar(request, response);
                break;

            default:
                listar(request, response);
                break;
        }
    }

    // ---------- LISTAR ----------
    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Conexion con = null;
        try {
            con = new Conexion();
            Connection cn = con.obtenerconexion();

            PreparedStatement ps = cn.prepareStatement("SELECT * FROM clientes");
            ResultSet rs = ps.executeQuery();

            ArrayList<HashMap<String, String>> lista = new ArrayList<>();

            while (rs.next()) {
                HashMap<String, String> c = new HashMap<>();
                c.put("id", String.valueOf(rs.getInt("id")));
                c.put("nombre", rs.getString("nombre"));
                c.put("correo", rs.getString("correo"));
                c.put("telefono", rs.getString("telefono"));
                lista.add(c);
            }

            request.setAttribute("clientes", lista);
            request.getRequestDispatcher("clientes.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            error(request, response, "Error al listar clientes");
        }
    }

    // ---------- MOSTRAR FORMULARIO EDITAR ----------
    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");

        Conexion con = null;
        try {
            con = new Conexion();
            Connection cn = con.obtenerconexion();

            PreparedStatement ps = cn.prepareStatement("SELECT * FROM clientes WHERE id=?");
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                request.setAttribute("id", rs.getInt("id"));
                request.setAttribute("nombre", rs.getString("nombre"));
                request.setAttribute("correo", rs.getString("correo"));
                request.setAttribute("telefono", rs.getString("telefono"));

                request.getRequestDispatcher("clientes_editar.jsp").forward(request, response);
            } else {
                error(request, response, "Cliente no encontrado");
            }

        } catch (Exception e) {
            e.printStackTrace();
            error(request, response, "Error al cargar datos del cliente");
        }
    }

    // ---------- ELIMINAR ----------
    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String id = request.getParameter("id");

        Conexion con = null;

        try {
            con = new Conexion();
            Connection cn = con.obtenerconexion();

            PreparedStatement ps = cn.prepareStatement("DELETE FROM clientes WHERE id=?");
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();

            response.sendRedirect("ClientesServlet");

        } catch (Exception e) {
            e.printStackTrace();
            error(request, response, "No se pudo eliminar el cliente");
        }
    }

    // ---------- POST (INSERTAR / ACTUALIZAR) ----------
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            accion = "insertar";
        }

        switch (accion) {
            case "actualizar":
                actualizar(request, response);
                break;

            default:
                insertar(request, response);
                break;
        }
    }

    // ---------- INSERTAR ----------
    private void insertar(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");

        // VALIDACIONES
        if (nombre == null || nombre.trim().isEmpty()) {
            error(request, response, "El nombre es obligatorio");
            return;
        }

        Conexion con = null;
        try {
            con = new Conexion();
            Connection cn = con.obtenerconexion();

            PreparedStatement ps = cn.prepareStatement(
                    "INSERT INTO clientes(nombre, correo, telefono) VALUES (?,?,?)"
            );
            ps.setString(1, nombre);
            ps.setString(2, correo);
            ps.setString(3, telefono);
            ps.executeUpdate();

            response.sendRedirect("ClientesServlet");

        } catch (Exception e) {
            e.printStackTrace();
            error(request, response, "Error al registrar cliente");
        }
    }

    // ---------- ACTUALIZAR ----------
    private void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");

        // VALIDACIONES
        if (nombre == null || nombre.trim().isEmpty()) {
            error(request, response, "El nombre no puede estar vacío");
            return;
        }

        Conexion con = null;
        try {
            con = new Conexion();
            Connection cn = con.obtenerconexion();

            PreparedStatement ps = cn.prepareStatement(
                    "UPDATE clientes SET nombre=?, correo=?, telefono=? WHERE id=?"
            );

            ps.setString(1, nombre);
            ps.setString(2, correo);
            ps.setString(3, telefono);
            ps.setInt(4, Integer.parseInt(id));

            ps.executeUpdate();

            response.sendRedirect("ClientesServlet");

        } catch (Exception e) {
            e.printStackTrace();
            error(request, response, "Error al actualizar cliente");
        }
    }

    // ---------- ERROR ----------
    private void error(HttpServletRequest request, HttpServletResponse response, String mensaje)
            throws ServletException, IOException {

        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
}
