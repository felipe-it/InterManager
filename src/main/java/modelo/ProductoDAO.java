
package modelo;
import utilidades.ConexionBDInterManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felip
 */
public class ProductoDAO {
    
    public boolean crearProducto(Producto producto) {
        
        String sql = "INSERT INTO Producto(nombre, activo, usuarioId) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBDInterManager.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombre());
            stmt.setBoolean(2, producto.isActivo());
            stmt.setInt(3, producto.getUsuarioId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al crear producto: " + e.getMessage());
            return false;
        }
    }

    public List<Producto> obtenerProductosPorUsuario(int usuarioId) {
        
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM Producto WHERE usuarioId = ?";

        try (Connection conn = ConexionBDInterManager.obtenerConexion();
           PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getBoolean("activo"),
                    rs.getInt("usuarioId")
                );
                
                productos.add(producto);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
        }
        return productos;
    }

    public boolean actualizarProducto(Producto producto) {
        
        String sql = "UPDATE Producto SET nombre = ? WHERE id = ?";

        try (Connection conn = ConexionBDInterManager.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setInt(2, producto.getId());
            stmt.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
            
            return false;
        }
    }

    public boolean eliminarProducto(int id) {
        String sql = "DELETE FROM Producto WHERE id = ?";

        try (Connection conn = ConexionBDInterManager.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }
    
    public boolean marcarComoInactivo(int productoId, int usuarioId) {
    String sql = "UPDATE producto SET activo = 0 WHERE id = ? AND usuarioId = ?";
    try (Connection conn = ConexionBDInterManager.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
         
        stmt.setInt(1, productoId);
        stmt.setInt(2, usuarioId);
        return stmt.executeUpdate() > 0;
        
    } catch (SQLException e) {
        
        e.printStackTrace();
        return false;
        
    }
} 

    public boolean tieneTransacciones(int productoId) {
        String sql = "SELECT COUNT(*) FROM Transaccion WHERE productoId = ?";

        try (Connection conn = ConexionBDInterManager.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productoId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.err.println("Error al verificar transacciones del producto: " + e.getMessage());
           }

        return false;
    }
    
    
    
}

