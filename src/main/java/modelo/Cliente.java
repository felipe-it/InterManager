/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class Cliente {
    
    private int id;
    private String nombre;
    private String email;
    private String telefono;
    private boolean activo;
    private int usuarioId;

    public Cliente(int id, String nombre, String email, String telefono, boolean activo, int usuarioId) {
        
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.activo = activo;
        this.usuarioId = usuarioId;
    }

    public Cliente(String nombre, String email, String telefono, boolean activo, int usuarioId) {
        this(0, nombre, email, telefono, activo, usuarioId);
    }

    // Establecemos getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    
    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
    
    /*@Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombre=" + nombre + ", email=" + email + ", telefono=" + telefono + ", activo=" + activo + ", usuarioId=" + usuarioId + '}';
    }*/   
    @Override
    public String toString() {
        return nombre;
    }

    
       
    
}

