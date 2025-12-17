/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.MetodoPago;
import modelo.MetodoPagoDAO;
import java.util.List;

public class MetodoPagoController {
    
    private MetodoPagoDAO dao = new MetodoPagoDAO();

    //recibimos los metodos de pago
    public List<MetodoPago> listar() {
        
        return dao.listarTodos(); 
        
    }
}
