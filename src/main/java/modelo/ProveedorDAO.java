/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;
import utilidades.ConexionBDInterManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {

    public boolean crearProveedor(Proveedor proveedor) {
        String sql = "INSERT INTO Proveedor (nombre, email, telefono, activo, usuarioId) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionBDInterManager.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, proveedor.getNombre());
            stmt.setString(2, proveedor.getEmail());
            stmt.setString(3, proveedor.getTelefono());
            stmt.setBoolean(4, proveedor.isActivo());
            stmt.setInt(5, proveedor.getUsuarioId());
            stmt.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            System.err.println("Error al crear proveedor: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarProveedor(Proveedor proveedor) {
        String sql = "UPDATE Proveedor SET nombre = ?, email = ?, telefono = ?, activo = ? WHERE id = ? AND usuarioId = ?";

        try (Connection conn = ConexionBDInterManager.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, proveedor.getNombre());
            stmt.setString(2, proveedor.getEmail());
            stmt.setString(3, proveedor.getTelefono());
            stmt.setBoolean(4, proveedor.isActivo());
            stmt.setInt(5, proveedor.getId());
            stmt.setInt(6, proveedor.getUsuarioId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar proveedor: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarProveedor(int id, int usuarioId) {
        String sql = "DELETE FROM Proveedor WHERE id = ? AND usuarioId = ?";
        try (Connection conn = ConexionBDInterManager.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setInt(2, usuarioId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar proveedor: " + e.getMessage());
            return false;
        }
    }

    public List<Proveedor> obtenerProveedoresPorUsuario(int usuarioId) {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM Proveedor WHERE usuarioId = ?";

        try (Connection conn = ConexionBDInterManager.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Proveedor proveedor = new Proveedor(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getBoolean("activo"),
                    rs.getInt("usuarioId")
                );
                lista.add(proveedor);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar proveedores: " + e.getMessage());
        }

        return lista;
    }
    
    public boolean marcarComoInactivo(int proveedorId, int usuarioId) {
        
    String sql = "UPDATE proveedor SET activo = 0 WHERE id = ? AND usuarioId = ?";
    
    try (Connection conn = ConexionBDInterManager.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
         
        stmt.setInt(1, proveedorId);
        stmt.setInt(2, usuarioId);

        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        
        e.printStackTrace();
        return false;
        
    }
    
   }
    
    
}


