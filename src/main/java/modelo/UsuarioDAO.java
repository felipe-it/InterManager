/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import utilidades.ConexionBDInterManager;
import utilidades.GeneradorDeHash;


public class UsuarioDAO { 

    public boolean registrar(Usuario usuario) {
        String sql = "INSERT INTO Usuario(nombre, email, contraseña) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBDInterManager.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, GeneradorDeHash.encriptarTexto(usuario.getContraseña()));
            stmt.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
            
        }
    }

    public Usuario login(String email, String contraseña) {
        String sql = "SELECT * FROM Usuario WHERE email = ? AND contraseña = ?";
        try (Connection conn = ConexionBDInterManager.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email); 
            stmt.setString(2, GeneradorDeHash.encriptarTexto(contraseña));
            ResultSet rs = stmt.executeQuery(); 
            
            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("contraseña")
                );  
            }  
        } catch (SQLException e) {
            
            System.err.println("Error en login: " + e.getMessage()); 
            
        }
        return null; 
        
    }

    public boolean existeEmail(String email) {
            
        String sql = "SELECT id FROM Usuario WHERE email = ?";
        try (Connection conn = ConexionBDInterManager.obtenerConexion();
                    
           PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
            
       } catch (SQLException e) {
            System.err.println("Error al verificar email: " + e.getMessage());
            return false;
             
       }
    } 
    
    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE Usuario SET nombre = ?, email = ?, contraseña = ? WHERE id = ?";

        try (Connection conn = ConexionBDInterManager.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, GeneradorDeHash.encriptarTexto(usuario.getContraseña()));           
            stmt.setInt(4, usuario.getId());            
  
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
        
    }

    public boolean eliminarUsuario(int id) {
        String sql = "DELETE FROM Usuario WHERE id = ?";

        try (Connection conn = ConexionBDInterManager.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);            

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
            
        } 
   }
    
    
}

