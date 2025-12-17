/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

/**
 *
 * @author felip
 */
import controlador.ClienteController;
import controlador.ProductoController;
import controlador.ProveedorController;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import modelo.Proveedor;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import modelo.Cliente;
import modelo.Producto;

public class GraficoTortaTransaccionesPanel extends JPanel {
    private Map<String, Integer> datos;

    public GraficoTortaTransaccionesPanel(List<Proveedor> proveedores, ProveedorController proveedorController, int usuarioId) {
        this.datos = new HashMap<>();

        for (Proveedor p : proveedores) {
            int cantidad = proveedorController.contarTransacciones(p.getId(), usuarioId);
            if (cantidad > 0) {
                datos.put(p.getNombre(), cantidad);
            }
        }
    }
    
    public GraficoTortaTransaccionesPanel(List<Cliente> clientes, ClienteController clienteController, int usuarioId) {
        this.datos = new HashMap<>();

        for (Cliente c : clientes) {
            int cantidad = clienteController.contarTransacciones(c.getId(), usuarioId);
            if (cantidad > 0) {
                datos.put(c.getNombre(), cantidad);
            }
        }
    }
    
    public GraficoTortaTransaccionesPanel(List<Producto> productos, ProductoController productoController, int usuarioId) {
        this.datos = new HashMap<>();

        for (Producto producto : productos) {
            int cantidad = productoController.contarTransacciones(producto.getId(), usuarioId);
            if (cantidad > 0) {
                datos.put(producto.getNombre(), cantidad);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (datos.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int total = datos.values().stream().mapToInt(Integer::intValue).sum();

        int x = 50, y = 30, width = 250, height = 250;
        int startAngle = 0;

        Color[] colores = {
            new Color(102, 204, 255), new Color(255, 153, 102), new Color(153, 255, 153),
            new Color(255, 255, 102), new Color(204, 153, 255), new Color(255, 102, 153),
            new Color(153, 204, 255), new Color(255, 204, 102)
        };

        int colorIndex = 0;
        for (Map.Entry<String, Integer> entry : datos.entrySet()) {
            String nombre = entry.getKey();
            int valor = entry.getValue();

            int arcAngle = (int) Math.round((double) valor / total * 360);

            g2.setColor(colores[colorIndex % colores.length]);
            g2.fillArc(x, y, width, height, startAngle, arcAngle);                                   

            // Leyenda
            g2.fillRect(x + width + 30, y + colorIndex * 20, 15, 15);
            g2.setColor(Color.BLACK);
            int porcentaje = (int)Math.round((entry.getValue() * 100.0) / total);
            g2.drawString(nombre + " " + porcentaje + "%" + " (" + valor + ")", x + width + 50, y + 13 + colorIndex * 20);

            startAngle += arcAngle;
            colorIndex++;
        }
    }
}
