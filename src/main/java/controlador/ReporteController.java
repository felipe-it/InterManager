/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author felip
 */
public class ReporteController {
    
    private TransaccionController transaccionController;

    public ReporteController() {
        
        this.transaccionController = new TransaccionController();
        
    }
    //metodos que utilizaremos para obtener los Datos Generales
    public double ingresosTotales(int usuarioId) {
        return transaccionController.ingresosPorProveedores(usuarioId) + transaccionController.ingresosPorClientes(usuarioId);
    }

    public double ingresosPorProveedores(int usuarioId) {
        return transaccionController.ingresosPorProveedores(usuarioId); 
    }

    public double ingresosPorClientes(int usuarioId) {
        return transaccionController.ingresosPorClientes(usuarioId);
    }

    public int cantidadTransacciones(int usuarioId) {
          
        return transaccionController.cantidadTransacciones(usuarioId);
    } 
    
}
