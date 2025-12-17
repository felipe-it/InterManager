/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import utilidades.ConexionBDInterManager;

public class TransaccionDAO {

    public boolean crear(Transaccion transaccion) {
        
        String sql = "INSERT INTO transaccion (fecha, proveedorId, clienteId, metodopagoId, productoId, montoTotal, porcentajeProveedor, porcentajeCliente, usuarioId) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionBDInterManager.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(transaccion.getFecha()));
            stmt.setInt(2, transaccion.getProveedorId());
            stmt.setInt(3, transaccion.getClienteId());
            stmt.setInt(4, transaccion.getMetodoPagoId());
            stmt.setInt(5, transaccion.getProductoId());
            stmt.setDouble(6, transaccion.getMonto());
            stmt.setDouble(7, transaccion.getPorcentajeProveedor());
            stmt.setDouble(8, transaccion.getPorcentajeCliente());
            stmt.setInt(9, transaccion.getUsuarioId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            
            e.printStackTrace();
            return false;
            
        }
    }

    public List<Transaccion> listarPorUsuario(int usuarioId) {
    List<Transaccion> lista = new ArrayList<>();

    String sql = "SELECT t.id, t.fecha, t.montoTotal, t.porcentajeProveedor, t.porcentajeCliente, " +
                 "t.proveedorId, t.clienteId, t.productoId, t.metodopagoId, " +
                 "prod.nombre AS nombreProducto, " +
                 "prov.nombre AS nombreProveedor, " +
                 "cli.nombre AS nombreCliente, " +
                 "mp.nombre AS nombreMetodoPago " +
                 "FROM transaccion t " +
                 "JOIN producto prod ON t.productoId = prod.id " +
                 "JOIN proveedor prov ON t.proveedorId = prov.id " +
                 "JOIN cliente cli ON t.clienteId = cli.id " +
                 "JOIN metodopago mp ON t.metodopagoId = mp.id " +
                 "WHERE t.usuarioId = ?";

    try (Connection conn = ConexionBDInterManager.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, usuarioId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            
            Transaccion t = new Transaccion();
            t.setId(rs.getInt("id"));
            t.setFecha(rs.getDate("fecha").toLocalDate());
            t.setMonto(rs.getDouble("montoTotal"));
            t.setPorcentajeProveedor(rs.getDouble("porcentajeProveedor"));
            t.setPorcentajeCliente(rs.getDouble("porcentajeCliente"));
            
            t.setProveedorId(rs.getInt("proveedorId"));
            t.setClienteId(rs.getInt("clienteId"));
            t.setProductoId(rs.getInt("productoId"));
            t.setMetodoPagoId(rs.getInt("metodopagoId"));
            
            t.setNombreProducto(rs.getString("nombreProducto"));
            t.setNombreProveedor(rs.getString("nombreProveedor"));
            t.setNombreCliente(rs.getString("nombreCliente"));
            t.setNombreMetodoPago(rs.getString("nombreMetodoPago"));

            lista.add(t);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lista;
}



    public boolean actualizar(Transaccion transaccion) {
        
        String sql = "UPDATE transaccion SET fecha=?, proveedorId=?, clienteId=?, metodopagoId=?, productoId=?, montoTotal=?, porcentajeProveedor=?, porcentajeCliente=? " +
                     "WHERE id=? AND usuarioId=?";
        try (Connection conn = ConexionBDInterManager.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(transaccion.getFecha()));
            stmt.setInt(2, transaccion.getProveedorId());
            stmt.setInt(3, transaccion.getClienteId());
            stmt.setInt(4, transaccion.getMetodoPagoId());
            stmt.setInt(5, transaccion.getProductoId());
            stmt.setDouble(6, transaccion.getMonto());
            stmt.setDouble(7, transaccion.getPorcentajeProveedor());
            stmt.setDouble(8, transaccion.getPorcentajeCliente());
            stmt.setInt(9, transaccion.getId());
            stmt.setInt(10, transaccion.getUsuarioId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id, int usuarioId) {
        
        String sql = "DELETE FROM transaccion WHERE id=? AND usuarioId=?";
        try (Connection conn = ConexionBDInterManager.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setInt(2, usuarioId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
            
       }
  }
    
    /*public double obtenerIngresosTotalesProveedores(int usuarioId) {
    String sql = "SELECT SUM((montoTotal * porcentajeProveedor / 100) AS total " +
                 "FROM transaccion WHERE usuarioId = ?";
    try (Connection conn = ConexionBDInterManager.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, usuarioId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getDouble("total");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}*/
    
    public Map<LocalDate, Double> ingresosUltimos30Dias(int usuarioId) {
    Map<LocalDate, Double> ingresosPorDia = new LinkedHashMap<>();

    LocalDate hoy = LocalDate.now();
    LocalDate hace30Dias = hoy.minusDays(29); 

    String sql = "SELECT fecha, SUM(montoTotal * (porcentajeProveedor + porcentajeCliente) / 100.0) AS ingresos " +
                 "FROM transaccion " + 
                 "WHERE usuarioId = ? AND fecha BETWEEN ? AND ? " +
                 "GROUP BY fecha " + 
                 "ORDER BY fecha";

    try (Connection conn = ConexionBDInterManager.obtenerConexion();
       PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, usuarioId); 
        stmt.setDate(2, java.sql.Date.valueOf(hace30Dias));
        stmt.setDate(3, java.sql.Date.valueOf(hoy));
        ResultSet rs = stmt.executeQuery();

        
        for (int i = 0; i < 30; i++) {
            LocalDate fecha = hace30Dias.plusDays(i);
            ingresosPorDia.put(fecha, 0.0);
        } 

        while (rs.next()) {
            LocalDate fecha = rs.getDate("fecha").toLocalDate();
            double ingreso = rs.getDouble("ingresos");
            ingresosPorDia.put(fecha, ingreso);
            System.out.println(fecha);
            System.out.println(ingreso);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return ingresosPorDia;
}
    
}

