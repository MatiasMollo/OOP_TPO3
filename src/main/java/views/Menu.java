package views;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {

    public Menu(){
        this.setTitle("Menu - Gestor de tarjetas");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,1));
        setSize(500,400);

        JButton agregarTarjeta = new JButton("Agregar Tarjeta");
        JButton agregarCliente = new JButton("Agregar Cliente");

        agregarTarjeta.addActionListener(e -> AgregarTarjeta.main());
        agregarCliente.addActionListener(e -> AgregarCliente.main());

        add(agregarTarjeta);
        add(agregarCliente);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Menu::new);
    }

}
