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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        setVisible(true);

    }

    private void guardar(){
        try{
            this.verifyData();
            ClienteDTO cliente = new ClienteDTO(
                    nombreTxt.getText(),
                    Integer.parseInt(dniTxt.getText())
            );

            this.clientes.agregarCliente(cliente);
            JOptionPane.showMessageDialog(this, "Cliente agregado exitosamente");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }


    /**
     * Verifica que los datos sean válidos
     * @throws Exception
     */
    private void verifyData() throws Exception
    {
        if(nombreTxt.getText().isEmpty() || nombreTxt.getText().length() < 3) throw new Exception("El nombre no es válido");
        if(!dniTxt.getText().matches("\\d+")) throw new Exception("El DNI ingresado no es válido");

    }

}
