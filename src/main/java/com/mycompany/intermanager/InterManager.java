/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.intermanager;

import controlador.TransaccionController;
import controlador.UsuarioController;
import java.sql.Connection;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import modelo.Usuario;
import utilidades.ConexionBDInterManager;
import utilidades.CrearTablas;
import utilidades.SesionUsuario;

/**
 *
 * @author felip
 */
public class InterManager {

    public static void main(String[] args) {
        
        ConexionBDInterManager.obtenerConexion();
        CrearTablas.crearEstructuraInicial();
        
        //UsuarioController usuarioController = new UsuarioController();
        
        System.out.println(UIManager.getLookAndFeel().getName());

        // Test 1: Registramos un usuario nuevo
        /*String nombre = "Test";
        String email = "test@test.com";
        String contraseña = "123456";

        boolean registroExitoso = usuarioController.registrarUsuario(nombre, email, contraseña);
        if (registroExitoso) {
            System.out.println("Registro exitoso.");
        } else {
            System.out.println("El usuario ya existe o hubo un error.");
        }

        // Test 2: Iniciamos sesión con un usuario ya registrado
        Usuario usuario = usuarioController.iniciarSesion(email, contraseña);
        if (usuario != null) {
            System.out.println("Login exitoso. Bienvenido, " + usuario.getNombre());                        
            
        } else {
            System.out.println("Login fallido. Credenciales incorrectas.");
        }*/
        // Test para comprobar ingresos por parte de los Proveedores
        TransaccionController controller = new TransaccionController();
        double ingresosProveedores = controller.ingresosPorProveedores(2);
        System.out.println("Ingresos por comisiones de proveedores: $" + ingresosProveedores);
        // Test para comprobar ingresos por parte de los Clientes
        double ingresosClientes = controller.ingresosPorClientes(2);
        System.out.println("Ingresos por comisiones de clientes: $" + ingresosClientes);
        
        
        System.out.println("Cantidad de Transacciones: " + controller.cantidadTransacciones(2));
        
        controller.ingresosUltimos30Dias(5);
                
        
    
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               
                vista.LoginFrame login = new vista.LoginFrame();
                login.setLocationRelativeTo(null); 
                login.setVisible(true);
                  
            }
            });
        
        
    }
}