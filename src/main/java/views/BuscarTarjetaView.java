package views;

import controllers.TarjetaController;
import dto.TarjetaDTO;
import models.Tarjeta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BuscarTarjetaView extends JFrame {
    private JLabel lblDNI;
    private JTextField txtNumero;
    private JButton btnBuscar;
    private JButton btnConsultarConsumos;
    private JTextArea resultado;

    TarjetaController tarjetaController;
    private String numeroTarjeta = "";

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

        btnConsultarConsumos = new JButton("Consultar consumos");
        btnConsultarConsumos.setEnabled(false);
        add(btnConsultarConsumos);

        btnBuscar.addActionListener(buscarTarjeta());
        btnConsultarConsumos.addActionListener((i) -> buscarConsumos());
    }

    private void buscarConsumos(){
        ConsultarConsumos consultarConsumos = new ConsultarConsumos(numeroTarjeta);
        consultarConsumos.setVisible(true);
    }

    private ActionListener buscarTarjeta() {
        return e -> {
            try {
                String numero = txtNumero.getText().trim();
                TarjetaDTO tarjeta = tarjetaController.buscarTarjetaDto(numero);

                if (tarjeta != null) {
                    resultado.setText(tarjeta.toString());

                    numeroTarjeta = tarjeta.getNumero();
                    btnConsultarConsumos.setEnabled(true);
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
