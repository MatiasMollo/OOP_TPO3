package views;

import controllers.ClienteController;
import dto.ClienteDTO;

import javax.swing.*;
import java.awt.*;

public class AgregarCliente extends JFrame {

    private JLabel nombreLabel;
    private JLabel dniLabel;

    private JTextField nombreTxt;
    private JTextField dniTxt;

    private JButton agregar;
    private JButton cancelar;

    ClienteController clientes;


    public AgregarCliente(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,2));
        setSize(500,400);

        this.clientes = ClienteController.getInstance();

        this.setTitle("Agregar cliente");

        this.nombreLabel = new JLabel("Nombre completo: ");
        add(this.nombreLabel);
        this.nombreTxt = new JTextField();
        add(this.nombreTxt);

        this.dniLabel = new JLabel("DNI: ");
        add(this.dniLabel);
        this.dniTxt = new JTextField();
        add(this.dniTxt);

        this.agregar = new JButton("Agregar");
        add(this.agregar);
        this.cancelar = new JButton("Cancelar");
        add(this.cancelar);

        this.agregar.addActionListener(e -> guardar());
        this.cancelar.addActionListener(e -> dispose());
    }

    private void guardar(){
        ClienteDTO cliente = new ClienteDTO(
                nombreTxt.getText(),
                Integer.parseInt(dniTxt.getText())
        );

        try{
            this.clientes.agregarCliente(cliente);
            JOptionPane.showMessageDialog(this, "Cliente agregado exitosamente");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

}
