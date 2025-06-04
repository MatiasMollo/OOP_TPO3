package controllers;

import dto.ClienteDTO;
import models.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteController {

    private List<Cliente> clientes;
    private static ClienteController instance;

    private ClienteController(){
        clientes = new ArrayList<Cliente>();
    }

    public static synchronized ClienteController getInstance(){
        if(instance == null) instance = new ClienteController();
        return instance;
    }

    /**
     * Retorna una lista de ClienteDTO para ser utilizado en la vista
     * @return List<ClienteDTO>
     */
    public List<ClienteDTO> getClientes()
    {
        List<ClienteDTO> list = new ArrayList<ClienteDTO>();

        for(Cliente cliente : clientes){
            list.add(new ClienteDTO(
                cliente.getNombre(),
                cliente.getDni()
            ));
        }

        return list;
    }

    /**
     * Realiza la búsqueda de un cliente en base al dni
     * @param dni Cliente
     * @return
     * @throws Exception
     */
    public Cliente getCliente(int dni) throws Exception
    {
        int x = 0;
        Cliente cliente = null;

        while(x < clientes.size() && cliente == null){
            if(this.clientes.get(x).getDni() == dni) cliente = this.clientes.get(x);
            x++;
        }

        if(cliente == null) throw new Exception("El cliente no existe en los registros");

        return cliente;
    }

    /**
     * Gestiona la creación de un cliente
     * @param clienteDto ClienteDTO
     * @return
     * @throws Exception
     */
    public Cliente agregarCliente(ClienteDTO clienteDto) throws Exception
    {
        Cliente cliente = null;
        boolean found = false;
        int x = 0;

        while(!found && x < clientes.size()){
            if(clientes.get(x).getDni() == clienteDto.getDni()) found = true;
            x++;
        }

        if(found) throw new Exception("El cliente con DNI " + clienteDto.getDni() + " ya se encuentra registrado");

        cliente = new Cliente(clienteDto.getNombre(), clienteDto.getDni());
        clientes.add(cliente);

        return cliente;
    }
}
