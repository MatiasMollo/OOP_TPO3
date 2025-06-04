package views;

import controllers.TarjetaController;
import models.Tarjeta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BuscarTarjetaView extends JFrame {
    private JLabel lblDNI;
    private JTextField txtNumero;
    private JButton btnBuscar;
    private JTextArea resultado;

    TarjetaController tarjetaController;

    public BuscarTarjetaView() {

        this.setTitle("Buscar Tarjeta: ");
        setVisible(true);

        tarjetaController = TarjetaController.getInstance();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLayout(new GridLayout(3,2));

        lblDNI = new JLabel("Número de DNI: ");
        add(lblDNI);

        txtNumero = new JTextField();
        add(txtNumero);

        btnBuscar = new JButton("Buscar");
        add(btnBuscar);

        resultado = new JTextArea();
        resultado.setEditable(false);
        add(new JScrollPane(resultado));

        btnBuscar.addActionListener(buscarTarjeta());
    }

    private ActionListener buscarTarjeta() {
        return e -> {
            try {
                String numero = txtNumero.getText().trim();

                dto.BuscarTarjetaDTO dto = new dto.BuscarTarjetaDTO(numero);

                Tarjeta tarjeta = tarjetaController.buscarTarjeta(dto.getNumero());

                if (tarjeta != null) {
                    resultado.setText(tarjeta.toString());
                } else {
                    resultado.setText("No se encontró ninguna tarjeta asociada a ese DNI.");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BuscarTarjetaView::new);
    }
}
