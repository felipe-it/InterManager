/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.time.LocalDate;

public class Transaccion {
    private int id;
    private LocalDate fecha;
    private int proveedorId;
    private int clienteId;
    private int metodoPagoId;
    private int productoId;
    private double montoTotal;
    private double porcentajeProveedor;
    private double porcentajeCliente;
    private int usuarioId;
    
    private String nombreProducto;
    private String nombreProveedor;
    private String nombreCliente;
    private String nombreMetodoPago;

    public Transaccion() {}

    public Transaccion(int id, LocalDate fecha, int proveedorId, int clienteId, int metodoPagoId, int productoId, double monto, double porcentajeProveedor, double porcentajeCliente, int usuarioId) {
       
        this.id = id;
        this.fecha = fecha;
        this.proveedorId = proveedorId;
        this.clienteId = clienteId;
        this.metodoPagoId = metodoPagoId;
        this.productoId = productoId;
        this.montoTotal = monto;
        this.porcentajeProveedor = porcentajeProveedor;
        this.porcentajeCliente = porcentajeCliente;
        this.usuarioId = usuarioId;
    }

    // Desarrollo Constructor sin id para nuevas transacciones.
    public Transaccion(LocalDate fecha, int proveedorId, int clienteId, int metodoPagoId, int productoId, double monto, double porcentajeProveedor, double porcentajeCliente, int usuarioId) {
        this(0, fecha, proveedorId, clienteId, metodoPagoId, productoId, monto, porcentajeProveedor, porcentajeCliente, usuarioId);
    }
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(int proveedorId) {
        this.proveedorId = proveedorId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getMetodoPagoId() {
        return metodoPagoId;
    }

    public void setMetodoPagoId(int metodoPagoId) {
        this.metodoPagoId = metodoPagoId;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public double getMonto() {
        return montoTotal;
    }

    public void setMonto(double monto) {
        this.montoTotal = monto;
    }

    public double getPorcentajeProveedor() {
        return porcentajeProveedor;
    }

    public void setPorcentajeProveedor(double porcentajeProveedor) {
        this.porcentajeProveedor = porcentajeProveedor;
    }

    public double getPorcentajeCliente() {
        return porcentajeCliente;
    }

    public void setPorcentajeCliente(double porcentajeCliente) {
        this.porcentajeCliente = porcentajeCliente;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreMetodoPago() {
        return nombreMetodoPago;
    }

    public void setNombreMetodoPago(String nombreMetodoPago) {
        this.nombreMetodoPago = nombreMetodoPago;
    }
    
    

    @Override
    public String toString() {
        return "Transaccion{" + "id=" + id + ", fecha=" + fecha + ", proveedorId=" + proveedorId + ", clienteId=" + clienteId + ", metodoPagoId=" + metodoPagoId + ", productoId=" + productoId + ", monto=" + montoTotal + ", porcentajeProveedor=" + porcentajeProveedor + ", porcentajeCliente=" + porcentajeCliente + ", usuarioId=" + usuarioId + '}';
    }
}

