/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author felip
 */
public class ExportarCSV {
    
    public static void exportarTablaCSV(JTable tabla, File archivo) {
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            
        // Nombres de las columnas
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            writer.write(tabla.getColumnName(i));
            if (i < tabla.getColumnCount() - 1) writer.write(","); 
        }
        
        writer.newLine();

        // Datos de las filas
        for (int row = 0; row < tabla.getRowCount(); row++) {
            for (int col = 0; col < tabla.getColumnCount(); col++) {
                
                Object valor = tabla.getValueAt(row, col);
                writer.write(valor != null ? valor.toString() : "");
                if (col < tabla.getColumnCount() - 1) writer.write(",");                
            }            
            writer.newLine();
        }

        writer.flush();
        JOptionPane.showMessageDialog(null, "Exportación completada correctamente.");
    } catch (IOException ex) {
        
        JOptionPane.showMessageDialog(null, "Error al exportar: " + ex.getMessage());
        ex.printStackTrace();
    }
        
    }
    
}
