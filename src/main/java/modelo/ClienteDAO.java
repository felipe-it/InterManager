/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import utilidades.ConexionBDInterManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public boolean crearCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente (nombre, email, telefono, activo, usuarioId) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBDInterManager.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefono());
            stmt.setBoolean(4, cliente.isActivo());
            stmt.setInt(5, cliente.getUsuarioId());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error al crear cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE Cliente SET nombre = ?, email = ?, telefono = ?, activo = ? WHERE id = ? AND usuarioId = ?";
        try (Connection conn = ConexionBDInterManager.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefono());
            stmt.setBoolean(4, cliente.isActivo());
            stmt.setInt(5, cliente.getId());
            stmt.setInt(6, cliente.getUsuarioId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarCliente(int id, int usuarioId) {
        String sql = "DELETE FROM Cliente WHERE id = ? AND usuarioId = ?";

        try (Connection conn = ConexionBDInterManager.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setInt(2, usuarioId);
            
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }

    public List<Cliente> obtenerClientesPorUsuario(int usuarioId) {
        
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cliente WHERE usuarioId = ?";

        try (Connection conn = ConexionBDInterManager.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();

             while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getBoolean("activo"),
                    rs.getInt("usuarioId")
                );
                lista.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar clientes: " + e.getMessage());
        }
        return lista;
    }
    
    public boolean marcarComoInactivo(int clienteId, int usuarioId) {
        
    String sql = "UPDATE cliente SET activo = 0 WHERE id = ? AND usuarioId = ?";
    try (Connection conn = ConexionBDInterManager.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
         
        stmt.setInt(1, clienteId);
        stmt.setInt(2, usuarioId);

        return stmt.executeUpdate() > 0;
        
    } catch (SQLException e) {
        
        e.printStackTrace();
        return false;
        
    }
    
}
    
}

