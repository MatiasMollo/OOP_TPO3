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
    private JTextArea resultado;
    private JButton listar;

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

        this.listar = new JButton("");

        resultado = new JTextArea();
        resultado.setEditable(false);
        add(new JScrollPane(resultado));
        add(this.listar);

        JButton cerrar = new JButton("Cerrar");
        cerrar.addActionListener(e -> dispose());
        add(cerrar);

        btnBuscar.addActionListener(buscarTarjeta());
    }

    private ActionListener buscarTarjeta() {
        return e -> {
            try {
                String numero = txtNumero.getText().trim();

                dto.BuscarTarjetaDTO dto = new dto.BuscarTarjetaDTO(numero);

                TarjetaDTO tarjeta = tarjetaController.buscarTarjetaDto(dto.getNumero());

                if (tarjeta != null) {
                    resultado.setText(tarjeta.toString());
                    this.listar.setText("Listar consumos");
                    this.listar.addActionListener(x -> new ConsultarConsumos(tarjeta.getNumero()));
                } else {
                    resultado.setText("No se encontró ninguna tarjeta asociada a ese DNI.");
                    this.listar.setText("");
                    this.listar.removeActionListener(x -> new ConsultarConsumos(tarjeta.getNumero()));
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
