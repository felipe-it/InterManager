/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Usuario;
import modelo.UsuarioDAO;
import utilidades.SesionUsuario;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        
        usuarioDAO = new UsuarioDAO();
        
    }

    public boolean registrarUsuario(String nombre, String email, String contraseña) {
        
        if (usuarioDAO.existeEmail(email)) {
            System.err.println("El email ya está registrado.");
            return false;
        }
        
        Usuario nuevo = new Usuario(nombre, email, contraseña);
        
        return usuarioDAO.registrar(nuevo);        
    }

    public Usuario iniciarSesion(String email, String contraseña) {
        return usuarioDAO.login(email, contraseña);
    }
    
    public boolean actualizar(int id, String nombre, String email, String contraseña) {
        
        Usuario usuario = new Usuario(id, nombre, email, contraseña);
        return usuarioDAO.actualizarUsuario(usuario);
        
    }
    
    public boolean eliminar(int id) {
               
        return usuarioDAO.eliminarUsuario(id);
        
    }
    
}

