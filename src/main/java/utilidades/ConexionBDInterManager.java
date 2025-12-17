/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Clase que se va a utilizar para la conexión a base_de_datos_intermanager.db
public class ConexionBDInterManager {
    private static final String URL = "jdbc:sqlite:base_de_datos_intermanager.db";

    public static Connection obtenerConexion() {
        
        try {
            
            return DriverManager.getConnection(URL);
            
        } catch (SQLException e) {
            
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            return null;
            
        }   
   }
}
