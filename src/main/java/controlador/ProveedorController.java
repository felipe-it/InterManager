/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Proveedor;
import modelo.ProveedorDAO;
import java.util.List;
import modelo.Transaccion;

public class ProveedorController {
    
    private ProveedorDAO proveedorDAO;
    private TransaccionController transaccionController;
    
    public ProveedorController() { //definimos constructor
        proveedorDAO = new ProveedorDAO();
        transaccionController = new TransaccionController();
    }    

    public boolean crear(String nombre, String email, String telefono, int usuarioId) {
        Proveedor proveedor = new Proveedor(nombre, email, telefono, true, usuarioId);
        return proveedorDAO.crearProveedor(proveedor);
        }

    public boolean actualizar(int id, String nombre, String email, String telefono, boolean activo, int usuarioId) {
        Proveedor proveedor = new Proveedor(id, nombre, email, telefono, activo, usuarioId);
        return proveedorDAO.actualizarProveedor(proveedor);
    }

    public boolean eliminar(int id, int usuarioId) {
        return proveedorDAO.eliminarProveedor(id, usuarioId);
    }

    public List<Proveedor> listarPorUsuario(int usuarioId) {
        
        return proveedorDAO.obtenerProveedoresPorUsuario(usuarioId);
        
    }
    
    public boolean marcarComoInactivo(int proveedorId, int usuarioId) {
        
    return proveedorDAO.marcarComoInactivo(proveedorId, usuarioId);
}
        
    public int contarTransacciones(int proveedorId, int usuarioId) {
        List<Transaccion> transacciones = transaccionController.listarPorUsuario(usuarioId);
        
        return (int) transacciones.stream()
                .filter(t -> t.getProveedorId() == proveedorId)
                .count();
     }
    
    
    public double obtenerIngresosTotales(int proveedorId, int usuarioId) {
        List<Transaccion> transacciones = transaccionController.listarPorUsuario(usuarioId);                        
        return transacciones.stream()   
                .filter(t -> t.getProveedorId() == proveedorId)
                .mapToDouble(t -> t.getMonto() * (t.getPorcentajeProveedor() / 100))
                .sum(); 
    }

    public double sumarMontoVentas(int proveedorId, int usuarioId) {
        List<Transaccion> transacciones = transaccionController.listarPorUsuario(usuarioId);
        
        return transacciones.stream()
                .filter(t -> t.getProveedorId() == proveedorId)
                .mapToDouble(Transaccion::getMonto)
                .sum();
    }
       
    
    
}
    


