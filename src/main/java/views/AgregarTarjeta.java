package views;

import controllers.TarjetaController;
import dto.AgregarTarjetaDTO;

import javax.swing.*;
import java.awt.*;

public class AgregarTarjeta extends JFrame {

    TarjetaController tarjetas;

    private JTextField dni;
    private JRadioButton debito;
    private JRadioButton credito;

    private JButton guardar;
    private JButton cancelar;

    public AgregarTarjeta() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,2));
        setSize(500,400);

        this.tarjetas = TarjetaController.getInstance();

        this.setTitle("Agregar tarjeta");

        add(new JLabel("DNI Cliente: "));
        this.dni = new JTextField();
        add(this.dni);

        add(new JLabel("Tipo Tarjeta: "));

        this.debito = new JRadioButton("Debito");
        this.credito = new JRadioButton("Credito");

        ButtonGroup group = new ButtonGroup();

        group.add(this.debito);
        group.add(this.credito);

        add(this.debito);
        add(this.credito);

        this.guardar = new JButton("Guardar");
        this.cancelar = new JButton("Cancelar");

        add(this.guardar);
        add(this.cancelar);

        this.guardar.addActionListener(e -> agregar());
        this.cancelar.addActionListener(e -> dispose());
    }


    private void agregar()
    {
        String tipo = debito.isSelected() ? "Debito" : "Credito";

        AgregarTarjetaDTO tarjeta = new AgregarTarjetaDTO(Integer.parseInt(dni.getText()), tipo);

        try{
            this.tarjetas.agregarTarjeta(tarjeta);
            JOptionPane.showMessageDialog(this, "Tarjeta agregado exitosamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }


    }

}
