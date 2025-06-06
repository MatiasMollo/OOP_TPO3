package views;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {

    public Menu(){
        this.setTitle("Menu - Gestor de tarjetas");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5,1));
        setSize(500,400);

        JButton agregarTarjeta = new JButton("Agregar Tarjeta");
        JButton agregarCliente = new JButton("Agregar Cliente");
        JButton agregarConsumo = new JButton("Agregar Consumo");
        JButton listarClientes = new JButton("Listar Clientes");
        JButton buscarTarjeta  = new JButton("Buscar Tarjeta");
        //JButton consultarConsumos  = new JButton("Consultar consumos");

        agregarTarjeta.addActionListener(e -> SwingUtilities.invokeLater(AgregarTarjeta::new));
        agregarConsumo.addActionListener(e -> SwingUtilities.invokeLater(AgregarConsumo::new));
        agregarCliente.addActionListener(e -> SwingUtilities.invokeLater(AgregarCliente::new));
        listarClientes.addActionListener(e -> SwingUtilities.invokeLater(ClientesDisponibles::new));
        buscarTarjeta.addActionListener(e -> SwingUtilities.invokeLater(BuscarTarjetaView::new));
        //consultarConsumos.addActionListener(e -> SwingUtilities.invokeLater(ConsultarConsumos::new));

        add(agregarTarjeta);
        add(agregarConsumo);
        add(agregarCliente);
        add(listarClientes);
        add(buscarTarjeta);
        //add(consultarConsumos);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Menu::new);
    }

}
