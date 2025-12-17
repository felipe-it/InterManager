/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author felip
 */
public class Producto {   
    
    private int id;
    private String nombre;
    private boolean activo;
    private int usuarioId;

    public Producto() {}

    public Producto(int id, String nombre, boolean activo, int usuarioId) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
        this.usuarioId = usuarioId;
    }

    public Producto(String nombre, boolean activo, int usuarioId) {
        this.nombre = nombre;
        this.activo = activo;
        this.usuarioId = usuarioId;
    }
    // Getters - Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public boolean isActivo() { 
        return activo; 
    }
    public void setActivo(boolean activo) { 
        this.activo = activo; 
    }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    @Override
    public String toString() {
        return nombre;
    }
    
    
    
}

