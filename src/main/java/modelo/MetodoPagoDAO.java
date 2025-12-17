/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import utilidades.ConexionBDInterManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MetodoPagoDAO {

    public List<MetodoPago> listarTodos() {
        List<MetodoPago> lista = new ArrayList<>();
        String sql = "SELECT id, nombre FROM metodopago";

        try (Connection conn = ConexionBDInterManager.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                
                MetodoPago mp = new MetodoPago();
                mp.setId(rs.getInt("id"));
                mp.setNombre(rs.getString("nombre"));
                lista.add(mp);
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Método para si crear los metodos de pago predeterminados: efectivo, tarjeta, transferencia, otros
    public void insertarSiNoExiste(String nombre) {
        
        String sql = "INSERT INTO metodopago (nombre) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM metodopago WHERE nombre = ?)";

        try (Connection conn = ConexionBDInterManager.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setString(2, nombre);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
      }
  }
}

