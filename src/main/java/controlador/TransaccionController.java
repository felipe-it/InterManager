/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import modelo.Transaccion;
import modelo.TransaccionDAO;

public class TransaccionController {

    private TransaccionDAO dao = new TransaccionDAO();

    public boolean crearTransaccion(LocalDate fecha, int proveedorId, int clienteId, int metodoPagoId,
                         int productoId, double montoTotal, double porcentajeProveedor, 
                         double porcentajeCliente, int usuarioId) {
        Transaccion t = new Transaccion(0, fecha, proveedorId, clienteId, metodoPagoId,
                                        productoId, montoTotal, porcentajeProveedor,
                                        porcentajeCliente, usuarioId);
        return dao.crear(t);
        
    }

    public List<Transaccion> listarPorUsuario(int usuarioId) {
        return dao.listarPorUsuario(usuarioId);
    }
    
    public boolean eliminar(int id, int usuarioId) {
        
        return dao.eliminar(id, usuarioId);
        
    }

    public boolean actualizar(int id, LocalDate fecha, int proveedorId, int clienteId,
                              int metodoPagoId, int productoId, double montoTotal,
                              double porcentajeProveedor, double porcentajeCliente,
                              int usuarioId) {
        Transaccion t = new Transaccion(id, fecha, proveedorId, clienteId, metodoPagoId,
                                        productoId, montoTotal, porcentajeProveedor,
                                        porcentajeCliente, usuarioId);
        return dao.actualizar(t);
    }

       
    /*public double ingresosTotales(int usuarioId) {
    return dao.obtenerIngresosTotalesProveedores(usuarioId);
}*/

    public double ingresosPorProveedores(int usuarioId) {
        
    List<Transaccion> transacciones = dao.listarPorUsuario(usuarioId);
    double total = 0;

    for (Transaccion t : transacciones) {
        double comisionProveedor = t.getMonto() * (t.getPorcentajeProveedor() / 100.0);
        total += comisionProveedor;
    }

    return total;
}
     
    public double ingresosPorClientes(int usuarioId) {
    List<Transaccion> transacciones = dao.listarPorUsuario(usuarioId);
    double total = 0;
    for (Transaccion t : transacciones) {
        double comisionCliente = t.getMonto() * (t.getPorcentajeCliente() / 100.0);
        total += comisionCliente;
    }

    return total;
}
    
    public double cantidadTransaccionnes(int usuarioId) {
    List<Transaccion> transacciones = dao.listarPorUsuario(usuarioId);
    double total = 0;

    for (Transaccion t : transacciones) {
        
        total ++;
    }

    return total;
}
    
    public int cantidadTransacciones(int usuarioId) {
    return dao.listarPorUsuario(usuarioId).size();
}
    
    public double comisionTotalPorcentaje(Transaccion t) {
    return t.getPorcentajeProveedor() + t.getPorcentajeCliente();
}
    public double comisionTotalNeta(Transaccion t) {        
    return (t.getPorcentajeProveedor()*t.getMonto()/100) + (t.getPorcentajeCliente()*t.getMonto()/100);
}
    // para generar grafico de barrar con las ganancias de comisiones de ultimos 30 días.
    public Map<LocalDate, Double> ingresosUltimos30Dias(int usuarioId) {   
        
        return dao.ingresosUltimos30Dias(usuarioId);        
         
    }
    
}

