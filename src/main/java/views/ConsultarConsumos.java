package views;

import controllers.TarjetaController;
import dto.ConsultarConsumoDTO;
import dto.MostrarConsumoDTO;
import models.Consumo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ConsultarConsumos extends JFrame {

    private JLabel numeroTarjetaLabel;
    private JLabel fechaInicioLabel;
    private JLabel fechaFinLabel;

    private JLabel numeroTarjetaValorLabel;
    private JTextField fechaInicioTxt;
    private JTextField fechaFinTxt;

    private JButton consultar;
    private JButton cancelar;

    private JTable tablaConsumos;
    private DefaultTableModel tablaModelo;
    private JLabel totalLabel;

    private TarjetaController tarjetas;
    private String numeroTarjeta;

    public ConsultarConsumos(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;

        setTitle("Consultar Consumos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 450);
        setLayout(new BorderLayout(10, 10));

        tarjetas = TarjetaController.getInstance();

        // Panel superior con los campos
        JPanel panelCampos = new JPanel(new GridLayout(4, 2, 10, 10));

        numeroTarjetaLabel = new JLabel("Número de Tarjeta:");
        fechaInicioLabel = new JLabel("Fecha Inicio (dd/MM/yyyy):");
        fechaFinLabel = new JLabel("Fecha Fin (dd/MM/yyyy):");

        numeroTarjetaValorLabel = new JLabel(numeroTarjeta);
        fechaInicioTxt = new JTextField();
        fechaFinTxt = new JTextField();

        consultar = new JButton("Consultar");
        cancelar = new JButton("Cancelar");

        panelCampos.add(numeroTarjetaLabel);
        panelCampos.add(numeroTarjetaValorLabel);
        panelCampos.add(fechaInicioLabel);
        panelCampos.add(fechaInicioTxt);
        panelCampos.add(fechaFinLabel);
        panelCampos.add(fechaFinTxt);
        panelCampos.add(consultar);
        panelCampos.add(cancelar);

        add(panelCampos, BorderLayout.NORTH);

        // Tabla
        tablaModelo = new DefaultTableModel(new String[]{"Fecha", "Establecimiento", "Monto"}, 0);
        tablaConsumos = new JTable(tablaModelo);
        JScrollPane scrollPane = new JScrollPane(tablaConsumos);
        scrollPane.setPreferredSize(new Dimension(550, 200));

        totalLabel = new JLabel("Total: $0.00");
        totalLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.add(scrollPane, BorderLayout.CENTER);
        panelTabla.add(totalLabel, BorderLayout.SOUTH);

        add(panelTabla, BorderLayout.CENTER);

        consultar.addActionListener(e -> consultarConsumos());
        cancelar.addActionListener(e -> limpiarCampos());
    }

    private void consultarConsumos() {
        try {
            DatosConsulta datos = validarCampos();
            ConsultarConsumoDTO consultarConsumoDTO = new ConsultarConsumoDTO(numeroTarjeta, datos.fechaInicio, datos.fechaFin);

            List<MostrarConsumoDTO> consumos = tarjetas.getConsumos(consultarConsumoDTO);
            double total = tarjetas.calcularConsumoReal(consultarConsumoDTO);

            // Actualizar tabla
            tablaModelo.setRowCount(0);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (MostrarConsumoDTO c : consumos) {
                tablaModelo.addRow(new Object[]{
                        sdf.format(c.getFecha()),
                        c.getEstablecimiento(),
                        String.format("$%.2f", c.getMonto())
                });
            }

            totalLabel.setText("Total: $" + String.format("%.2f", total));

            if (consumos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron consumos en el rango indicado.");
            }

        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private DatosConsulta validarCampos() {
        String fechaInicioStr = fechaInicioTxt.getText().trim();
        String fechaFinStr = fechaFinTxt.getText().trim();

        if (fechaInicioStr.isEmpty() || fechaFinStr.isEmpty()) {
            throw new RuntimeException("Las fechas de inicio y fin son obligatorias.");
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            Date fechaInicio = sdf.parse(fechaInicioStr);
            Date fechaFin = sdf.parse(fechaFinStr);

            if (fechaInicio.after(fechaFin)) {
                throw new RuntimeException("La fecha de inicio no puede ser posterior a la de fin.");
            }

            return new DatosConsulta(fechaInicio, fechaFin);

        } catch (ParseException e) {
            throw new RuntimeException("Formato de fecha inválido. Usá dd/MM/yyyy.");
        }
    }

    private void limpiarCampos() {
        fechaInicioTxt.setText("");
        fechaFinTxt.setText("");
        tablaModelo.setRowCount(0);
        totalLabel.setText("Total: $0.00");
    }

    private static class DatosConsulta {
        Date fechaInicio;
        Date fechaFin;

        public DatosConsulta(Date fechaInicio, Date fechaFin) {
            this.fechaInicio = fechaInicio;
            this.fechaFin = fechaFin;
        }
    }
}
