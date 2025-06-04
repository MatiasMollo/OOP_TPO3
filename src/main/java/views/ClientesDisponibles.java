package views;

import controllers.ClienteController;
import dto.ClienteDTO;
import models.Cliente;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ClientesDisponibles extends JFrame {

    private JLabel titulo;
    private JList<String> listaClientes;
    private JButton actualizar;
    private JButton cerrar;

    ClienteController clientes;

    public ClientesDisponibles() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 1));
        setSize(500, 400);
        setTitle("Consultar Clientes");
        setVisible(true);

        this.clientes = ClienteController.getInstance();

        this.titulo = new JLabel("Clientes disponibles");
        add(this.titulo);

        this.listaClientes = new JList<>();
        add(new JScrollPane(this.listaClientes));

        JPanel botones = new JPanel(new GridLayout(1, 2));
        this.actualizar = new JButton("Actualizar");
        this.cerrar = new JButton("Cerrar");
        botones.add(actualizar);
        botones.add(cerrar);
        add(botones);

        this.actualizar.addActionListener(e -> actualizarLista());
        this.cerrar.addActionListener(e -> dispose());

        actualizarLista();
    }

    private void actualizarLista() {
        List<ClienteDTO> lista = this.clientes.getClientes();

        if (lista.isEmpty()) {
            listaClientes.setListData(new String[]{"No hay clientes disponibles."});
        } else {

            String[] datos = lista.stream()
                    .map(c -> "Nombre: " + c.getNombre() + " - DNI: " + c.getDni())
                    .toArray(String[]::new);
            listaClientes.setListData(datos);
        }
    }
}