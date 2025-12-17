/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import modelo.Producto;
import modelo.ProductoDAO;

import java.util.List;
import modelo.Transaccion;

/**
 *
 * @author felip
 */
public class ProductoController {
    
    private ProductoDAO productoDAO;
    private TransaccionController transaccionController;

    public ProductoController() {
        this.productoDAO = new ProductoDAO();
        this.transaccionController = new TransaccionController();
    }

    public boolean crear(String nombre, int usuarioId) {
        
      Producto producto = new Producto(nombre, true, usuarioId);
      return productoDAO.crearProducto(producto);
      
    }

    public List<Producto> listarPorUsuario(int usuarioId) {
        return productoDAO.obtenerProductosPorUsuario(usuarioId);
    } 

    public boolean actualizar(int id, String nuevoNombre) {
        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre(nuevoNombre);
        
        return productoDAO.actualizarProducto(producto);
    }

    public boolean eliminar(int id) {
        
        if (!productoDAO.tieneTransacciones(id)) {
            return productoDAO.eliminarProducto(id);
        } else {
            System.out.println("No se puede eliminar el producto, tiene transacciones asociadas.");
            return false;
        }
    }
    
    public boolean marcarComoInactivo(int productoId, int usuarioId) {
    return productoDAO.marcarComoInactivo(productoId, usuarioId);
}
    
    public int contarTransacciones(int productoId, int usuarioId) {
        List<Transaccion> transacciones = transaccionController.listarPorUsuario(usuarioId);
        
            return (int) transacciones.stream()
                .filter(t -> t.getProductoId() == productoId)
                .count();
     }

    public double sumarMontoTransacciones(int productoId, int usuarioId) {
        List<Transaccion> transacciones = transaccionController.listarPorUsuario(usuarioId);
        
        return transacciones.stream()
                .filter(t -> t.getProductoId() == productoId)
                .mapToDouble(Transaccion::getMonto)
                .sum();
        
    }  
       
    public double obtenerIngresosTotales(int productoId, int usuarioId) {
        List<Transaccion> transacciones = transaccionController.listarPorUsuario(usuarioId);                
        
        return transacciones.stream()
                .filter(t -> t.getProductoId() == productoId)
                .mapToDouble(t -> t.getMonto() * (t.getPorcentajeProveedor() / 100 + t.getPorcentajeCliente() / 100))
                .sum();
        } 
    
}
