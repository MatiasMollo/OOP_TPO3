package views;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {

    public Menu(){
        this.setTitle("Menu - Gestor de tarjetas");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,1));
        setSize(500,400);

        JButton agregarTarjeta = new JButton("Agregar Tarjeta");
        JButton agregarCliente = new JButton("Agregar Cliente");
        JButton listarClientes = new JButton("Listar Clientes");

        agregarTarjeta.addActionListener(e -> SwingUtilities.invokeLater(AgregarTarjeta::new));
        agregarCliente.addActionListener(e -> SwingUtilities.invokeLater(AgregarCliente::new));
        listarClientes.addActionListener(e -> SwingUtilities.invokeLater(ClientesDisponibles::new));

        add(agregarTarjeta);
        add(agregarCliente);
        add(listarClientes);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Menu::new);
    }

}
