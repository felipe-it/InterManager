/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class GraficoIngresosFechasPanel extends JPanel {
    
    private Map<LocalDate, Double> ingresosPorFecha;
      
    public GraficoIngresosFechasPanel(Map<LocalDate, Double> ingresosPorFecha) {
        
        this.ingresosPorFecha = ingresosPorFecha;
        setPreferredSize(new Dimension(350, 200));
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        dibujarGrafico((Graphics2D) g);
    }

    private void dibujarGrafico(Graphics2D g) {
        
        int width = getWidth();
        int height = getHeight();
        int padding = 60;
        int espacioEntreBarras = 5;

        g.setColor(Color.BLACK);
        g.drawLine(padding, height - padding, width - padding, height - padding); // eje X
        g.drawLine(padding, padding, padding, height - padding); // eje Y

        if (ingresosPorFecha == null || ingresosPorFecha.isEmpty()) return;

        List<Map.Entry<LocalDate, Double>> listaIngresos = ingresosPorFecha.entrySet().stream().toList();
        double maxIngreso = listaIngresos.stream().mapToDouble(Map.Entry::getValue).max().orElse(1);
        

        int numDias = listaIngresos.size();
        int anchoBarra = (width - 2 * padding - (numDias - 1) * espacioEntreBarras) / numDias;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");

        for (int i = 0; i < numDias; i++) {
            LocalDate fecha = listaIngresos.get(i).getKey();
            double ingreso = listaIngresos.get(i).getValue();

            int barHeight = (int) ((ingreso / maxIngreso) * (height - 2 * padding));
            int x = padding + i * (anchoBarra + espacioEntreBarras);
            int y = height - padding - barHeight;

            g.setColor(new Color(100, 149, 237)); // azul suave
            g.fillRect(x, y, anchoBarra, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, anchoBarra, barHeight);

            
            String fechaStr = fecha.format(formatter);
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(fechaStr);
            //g.drawString(fechaStr, x + (anchoBarra - textWidth) / 2, height - padding + 15);
        }

        
        g.setFont(new Font("SansSerif", Font.BOLD, 17));
        g.drawString("Ingresos de los últimos 30 días", width / 2 - 120, padding / 2);        
        }
}

