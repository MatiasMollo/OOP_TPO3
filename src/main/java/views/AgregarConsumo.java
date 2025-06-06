package views;

import controllers.TarjetaController;
import dto.AgregarConsumoDTO;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgregarConsumo extends JFrame {

    private JLabel numeroTarjetaLabel;
    private JLabel fechaLabel;
    private JLabel establecimientoLabel;
    private JLabel montoLabel;

    private JTextField numeroTarjetaTxt;
    private JTextField fechaTxt;
    private JTextField establecimientoTxt;
    private JTextField montoTxt;

    private JButton agregar;
    private JButton cancelar;

    private TarjetaController tarjetas;

    public AgregarConsumo() {
        setTitle("Crear nuevo Consumo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));
        setSize(400, 250);
        setVisible(true);

        this.tarjetas = TarjetaController.getInstance();

        // Crear componentes
        numeroTarjetaLabel = new JLabel("Número de Tarjeta:");
        fechaLabel = new JLabel("Fecha (dd/MM/yyyy):");
        establecimientoLabel = new JLabel("Establecimiento:");
        montoLabel = new JLabel("Monto:");

        numeroTarjetaTxt = new JTextField();
        fechaTxt = new JTextField();
        establecimientoTxt = new JTextField();
        montoTxt = new JTextField();

        agregar = new JButton("Agregar");
        cancelar = new JButton("Cancelar");

        // Agregar componentes al layout
        add(numeroTarjetaLabel);
        add(numeroTarjetaTxt);
        add(fechaLabel);
        add(fechaTxt);
        add(establecimientoLabel);
        add(establecimientoTxt);
        add(montoLabel);
        add(montoTxt);
        add(agregar);
        add(cancelar);

        agregar.addActionListener(e -> agregar());
        cancelar.addActionListener(e -> limpiarCampos());
    }

    private void agregar() {
        try {
            AgregarConsumoDTO consumoDTO = validarCampos();

            tarjetas.agregarConsumo(consumoDTO);
            JOptionPane.showMessageDialog(this, "Consumo agregado exitosamente.");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private AgregarConsumoDTO validarCampos() throws Exception {
        String numeroTarjeta = numeroTarjetaTxt.getText().trim();
        String fechaStr = fechaTxt.getText().trim();
        String establecimiento = establecimientoTxt.getText().trim();
        String montoStr = montoTxt.getText().trim();

        if (numeroTarjeta.isEmpty() || fechaStr.isEmpty() || establecimiento.isEmpty() || montoStr.isEmpty()) {
            throw new RuntimeException("Todos los campos son obligatorios.");
        }

        Date fecha;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            fecha = sdf.parse(fechaStr);
        } catch (ParseException e) {
            throw new RuntimeException("Fecha inválida. Usá formato dd/MM/yyyy.");
        }

        float monto;
        try {
            monto = Float.parseFloat(montoStr);
            if (monto <= 0) throw new NumberFormatException("El monto debe ser un número.");
        } catch (NumberFormatException e) {
            throw new RuntimeException("El monto debe ser un número mayor a 0.");
        }

        return new AgregarConsumoDTO(numeroTarjeta, fecha, establecimiento, monto);
    }

    private void limpiarCampos() {
        numeroTarjetaTxt.setText("");
        fechaTxt.setText("");
        establecimientoTxt.setText("");
        montoTxt.setText("");
    }
}
