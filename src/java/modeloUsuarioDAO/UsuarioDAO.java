/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloUsuarioDAO;

/**
 *
 * @author dougl
 */







import modeloUsuarioVO.UsuarioVO;
import util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import interfaces.Crud;

public class UsuarioDAO implements Crud {

    private Connection conexion;
    private PreparedStatement puente;
    private ResultSet mensajero;
    private boolean operacion = false;

    private String idUsuario, nombreUsuario, correo, contrasena, rol;

    public UsuarioDAO(UsuarioVO usuVO) {
        try {
            Conexion con = new Conexion();
            conexion = con.obtenerconexion();

            idUsuario = usuVO.getIdUsuario();
            nombreUsuario = usuVO.getNombreUsuario();
            correo = usuVO.getCorreo();
            contrasena = usuVO.getContrasena();
            rol = usuVO.getRol();

        } catch (Exception e) {
            System.out.println("Error DAO: " + e.toString());
        }
    }

    // ---------------- AGREGAR USUARIO ----------------
    @Override
    public boolean agregarRegistro() {
        try {
            String sql = "INSERT INTO usuarios(nombre_usuario, correo, contrasena, rol) VALUES (?, ?, ?, ?)";
            puente = conexion.prepareStatement(sql);
            puente.setString(1, nombreUsuario);
            puente.setString(2, correo);
            puente.setString(3, contrasena);
            puente.setString(4, rol);

            puente.executeUpdate();
            operacion = true;

        } catch (Exception e) {
            System.out.println("Error insertar usuario: " + e.toString());
        }
        return operacion;
    }

    // ---------------- ACTUALIZAR USUARIO ----------------
    @Override
    public boolean actualizarRegistro() {
        try {
            String sql = "UPDATE usuarios SET nombre_usuario=?, correo=?, contrasena=?, rol=? WHERE id=?";
            puente = conexion.prepareStatement(sql);
            puente.setString(1, nombreUsuario);
            puente.setString(2, correo);
            puente.setString(3, contrasena);
            puente.setString(4, rol);
            puente.setString(5, idUsuario);

            puente.executeUpdate();
            operacion = true;

        } catch (Exception e) {
            System.out.println("Error actualizar usuario: " + e.toString());
        }
        return operacion;
    }

    // ---------------- ELIMINAR USUARIO ----------------
    @Override
    public boolean eliminarRegistro() {
        try {
            String sql = "DELETE FROM usuarios WHERE id=?";
            puente = conexion.prepareStatement(sql);
            puente.setString(1, idUsuario);

            puente.executeUpdate();
            operacion = true;

        } catch (Exception e) {
            System.out.println("Error eliminar usuario: " + e.toString());
        }
        return operacion;
    }

    // ---------------- LISTAR POR ID ----------------
    public UsuarioVO consultarPorId(String id) {
        UsuarioVO usuVO = null;
        try {
            String sql = "SELECT * FROM usuarios WHERE id=?";
            puente = conexion.prepareStatement(sql);
            puente.setString(1, id);

            mensajero = puente.executeQuery();
            if (mensajero.next()) {
                usuVO = new UsuarioVO(
                        mensajero.getString("id"),
                        mensajero.getString("nombre_usuario"),
                        mensajero.getString("correo"),
                        mensajero.getString("contrasena"),
                        mensajero.getString("rol")
                );
            }

        } catch (Exception e) {
            System.out.println("Error consultar ID: " + e.toString());
        }
        return usuVO;
    }
}
