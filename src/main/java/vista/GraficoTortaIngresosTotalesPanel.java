/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ClienteController;
import controlador.ProductoController;
import controlador.ProveedorController;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import modelo.Cliente;
import modelo.Producto;
import modelo.Proveedor;

/**
 *
 * @author felip
 */
public class GraficoTortaIngresosTotalesPanel extends JPanel {
    private Map<String, Double> datos;

    public GraficoTortaIngresosTotalesPanel(List<Proveedor> proveedores, ProveedorController proveedorController, int usuarioId) {
        this.datos = new HashMap<>();
        for (Proveedor p : proveedores) {
            double ingresos = proveedorController.obtenerIngresosTotales(p.getId(), usuarioId);
            if (ingresos > 0) {
                datos.put(p.getNombre(), ingresos);
            }
        }
    }

    public GraficoTortaIngresosTotalesPanel(List<Cliente> clientes, ClienteController clienteController, int usuarioId) {
        this.datos = new HashMap<>();
        for (Cliente c : clientes) {
            double ingresos = clienteController.obtenerIngresosTotales(c.getId(), usuarioId);
            if (ingresos > 0) {
                datos.put(c.getNombre(), ingresos);
            }
        }
    }

    public GraficoTortaIngresosTotalesPanel(List<Producto> productos, ProductoController productoController, int usuarioId) {
        this.datos = new HashMap<>();
        for (Producto producto : productos) {
            double ingresos = productoController.obtenerIngresosTotales(producto.getId(), usuarioId);
            if (ingresos > 0) {
                datos.put(producto.getNombre(), ingresos);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (datos.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double total = datos.values().stream().mapToDouble(Double::doubleValue).sum();

        int x = 50, y = 30, width = 250, height = 250;
        int startAngle = 0;

        Color[] colores = {
            new Color(102, 204, 255), new Color(255, 153, 102), new Color(153, 255, 153),
            new Color(255, 255, 102), new Color(204, 153, 255), new Color(255, 102, 153),
            new Color(153, 204, 255), new Color(255, 204, 102)
        };

        int colorIndex = 0;
        for (Map.Entry<String, Double> entry : datos.entrySet()) {
            String nombre = entry.getKey();
            double valor = entry.getValue();

            int arcAngle = (int) Math.round(valor / total * 360);

            g2.setColor(colores[colorIndex % colores.length]);
            g2.fillArc(x, y, width, height, startAngle, arcAngle);

            g2.fillRect(x + width + 30, y + colorIndex * 20, 15, 15);
            g2.setColor(Color.BLACK);
            int porcentaje = (int) Math.round((valor * 100.0) / total);
            String texto = nombre + " " + porcentaje + "%"; //+ " ($" + String.format("%.0f", valor) + ")"
            g2.drawString(texto, x + width + 50, y + 13 + colorIndex * 20);

            startAngle += arcAngle;
            colorIndex++;
        }
    }
}

