/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Cliente;
import modelo.ClienteDAO;


import java.util.List;
import modelo.Transaccion;


public class ClienteController {
    private ClienteDAO clienteDAO;
    private TransaccionController transaccionController;
    

    public ClienteController() {
        clienteDAO = new ClienteDAO();
        transaccionController = new TransaccionController();
         
    }
    
    public List<Cliente> listarPorUsuario(int usuarioId) {
        return clienteDAO.obtenerClientesPorUsuario(usuarioId);
    }

    public boolean crear(String nombre, String email, String telefono, int usuarioId) {
        
        Cliente cliente = new Cliente(nombre, email, telefono, true, usuarioId);
        return clienteDAO.crearCliente(cliente);
    }

    public boolean actualizar(int id, String nombre, String email, String telefono, boolean activo, int usuarioId) {
        
        Cliente cliente = new Cliente(id, nombre, email, telefono, activo, usuarioId);
        return clienteDAO.actualizarCliente(cliente);
        
    }

    public boolean eliminar(int id, int usuarioId) {
        return clienteDAO.eliminarCliente(id, usuarioId);
    }

    
      
    // metodo para macrar Inactivo al cliente
    public boolean marcarComoInactivo(int clienteId, int usuarioId) {
        
    return clienteDAO.marcarComoInactivo(clienteId, usuarioId);    
      }
     
    public int contarTransacciones(int clienteId, int usuarioId) {
        List<Transaccion> transacciones = transaccionController.listarPorUsuario(usuarioId);
        
            return (int) transacciones.stream()
                .filter(t -> t.getClienteId() == clienteId)
                .count();
     }

    public double sumarMontoCompras(int clienteId, int usuarioId) {
        List<Transaccion> transacciones = transaccionController.listarPorUsuario(usuarioId);
        
        return transacciones.stream()
                .filter(t -> t.getClienteId() == clienteId)
                .mapToDouble(Transaccion::getMonto)
                .sum();
        
    }  
       
    public double obtenerIngresosTotales(int clienteId, int usuarioId) {
        List<Transaccion> transacciones = transaccionController.listarPorUsuario(usuarioId);                
        
        return transacciones.stream()
                .filter(t -> t.getClienteId() == clienteId)
                .mapToDouble(t -> t.getMonto() * (t.getPorcentajeCliente() / 100))
                .sum();
        } 
     
    }

