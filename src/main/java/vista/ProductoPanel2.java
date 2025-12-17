/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ProductoController;
import modelo.Producto;
import utilidades.SesionUsuario;

import java.awt.Color;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProductoPanel2 extends JPanel {

    private ProductoController controller = new ProductoController();

    public ProductoPanel2() {
        
        initComponents();
        initStyles();
        cargarProductos("");
        
    }

    private void initStyles() {
        
        title.setText("Gestión de Productos");
        title.setForeground(Color.BLACK);
        txtBuscar.putClientProperty("JTextField.placeholderText", "Buscar por nombre...");
        
    }

    private void cargarProductos(String filtro) {
        
        DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
        model.setRowCount(0);
        List<Producto> lista = controller.listarPorUsuario(SesionUsuario.getUsuario().getId());
        lista.stream()
            .filter(p -> filtro.isEmpty() || p.getNombre().toLowerCase().contains(filtro.toLowerCase()))
            .forEach(p -> model.addRow(new Object[]{p.getId(), p.getNombre()}));
        
    }

    private void initComponents() {
        
        setLayout(new java.awt.BorderLayout());
        JPanel bg = new JPanel();
        bg.setBackground(Color.WHITE);
        bg.setLayout(new BoxLayout(bg, BoxLayout.Y_AXIS));

        
        title = new JLabel("Productos");
        title.setFont(new java.awt.Font("Segoe UI", 1, 24));
        title.setAlignmentX(CENTER_ALIGNMENT);
        bg.add(title);

        
        JPanel buscarPanel = new JPanel();
        txtBuscar = new JTextField(20);
        btnBuscar = new JButton("Buscar");
        buscarPanel.add(txtBuscar);
        buscarPanel.add(btnBuscar);
        bg.add(buscarPanel);

        
        tablaProductos = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Nombre"}
        ) {
            public boolean isCellEditable(int row, int column) { return false; }
        });
        JScrollPane scroll = new JScrollPane(tablaProductos);
        bg.add(scroll);

        
        JPanel botones = new JPanel();
        btnAgregar = new JButton("Nuevo");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Borrar");
        botones.add(btnAgregar);
        botones.add(btnEditar);
        botones.add(btnEliminar);
        bg.add(botones);

        add(bg, java.awt.BorderLayout.CENTER);

        
        btnBuscar.addActionListener(e -> cargarProductos(txtBuscar.getText().trim()));

        btnAgregar.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Nombre del producto:");
            if (nombre != null && !nombre.trim().isEmpty()) {
                boolean exito = controller.crear(nombre.trim(), SesionUsuario.getUsuario().getId());
                if (exito) cargarProductos("");
                else JOptionPane.showMessageDialog(this, "Error al crear el producto.");
            }
        });
        
        
    }

    // Componentes
    private JTable tablaProductos;
    private JTextField txtBuscar;
    private JButton btnBuscar, btnAgregar, btnEditar, btnEliminar;
    private JLabel title;
}
