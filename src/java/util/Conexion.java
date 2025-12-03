/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dougl
 */
public class Conexion {
    
    // 1. Declarar variables y objetos
    private String driver, user, password, dataBase,urlBd,port;
    private Connection conexion; 
    
    // 2. Asignar Valor
    public Conexion() {
        driver = "com.mysql.jdbc.Driver";
        user="root";
        password="";
        dataBase ="almagest";
        port ="3306";
        urlBd="jdbc:mysql://localhost:"+port+"/"+dataBase;
        
        // 3. Conecto
        try {
            Class.forName(driver).newInstance();
            conexion = DriverManager.getConnection(urlBd, user,password);
            System.out.println("Conexion ok!");
        } catch (Exception e) {
            System.out.println("Error al conectarse!" +e.toString());
        }
    }
    public Connection obtenerconexion() { 
        return conexion;
    }
    public Connection cerrarconexion () throws SQLException {
        conexion.close () ;
        conexion = null;
        return conexion;
        
        
    }
    public static void main(String[] args) {
      new Conexion();
    }
        
    }
