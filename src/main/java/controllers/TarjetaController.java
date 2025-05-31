package controllers;

import models.Cliente;
import models.Tarjeta;
import models.TarjetaCredito;
import models.TarjetaDebito;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TarjetaController {
    private List<Tarjeta> tarjetas;
    private static TarjetaController instance;

    private TarjetaController()
    {
        tarjetas = new ArrayList<Tarjeta>();
    }

    public static synchronized TarjetaController getInstance()
    {
        if(instance == null) instance = new TarjetaController();

        return instance;
    }

    /**
     * Retorna todas las tarjetas almacenadas
     * @return List<Tarjeta>
     */
    public List<Tarjeta> getTarjetas()
    {
        return tarjetas;
    }

    /**
     * Gestiona la creación de una nueva tarjeta, si la misma es válida, la agrega a la lista de tarjetas
     * @param dniCliente
     * @param tipoTarjeta
     * @return Tarjeta
     * @throws Exception
     */
    public Tarjeta agregarTarjeta(int dniCliente, String tipoTarjeta) throws Exception
    {
        boolean found = false;
        int x = 0;
        Tarjeta tarjeta = null;

        while(x < this.tarjetas.size() && !found){
            if(this.tarjetas.get(x).getCliente().getDni() == dniCliente) found = true;
            x++;
        }

        // Se verifica que no haya una tarjeta con el dni
        if(found) throw new Exception("El cliente ya posee una tarjeta");
        else{
            ClienteController clientes = ClienteController.getInstance();

            // Se verifica que exista el cliente, lanza una excepción de forma automática en caso de no existir
            Cliente cliente = clientes.getCliente(dniCliente);

            // Crea la tarjeta
            tarjeta = crearTarjeta(cliente, tipoTarjeta);
            tarjetas.add(tarjeta);
        }

        return tarjeta;
    }

    /**
     * Genera un número único de tarjeta verificando que la misma no se repita
     * @return
     */
    private String generarNumeroTarjeta(){
        String numero = "";
        Random random = new Random();
        int x;
        boolean found = false;

        while(numero.equals("")){
            x = 0;

            // Generá el número de 16 dígitos
            for(int i = 0; i < 16; i++){
                if((i)%4 == 0 && i != 0) numero += " "; // Agrega espacios en el número para facilitar su lectura
                numero = numero + random.nextInt(10);
            }


            // Verifica que el número no se repita en el resto de tarjetas
            while(x < this.tarjetas.size() && !found){
                if(this.tarjetas.get(x).getNumero().equals(numero)) found = true;
                x++;
            }

            // En caso de encontrar un duplicado, resetea el número y vuelve a iniciar el bucle
            if(found) numero = "";
        }

        return numero;
    }

    /**
     * Realiza la creación de la tarjeta en base al tipo de la misma
     * @param cliente Cliente
     * @param tipo String
     * @return Tarjeta
     * @throws Exception
     */
    private Tarjeta crearTarjeta(Cliente cliente, String tipo) throws Exception
    {
        String numero = generarNumeroTarjeta();
        Tarjeta tarjeta = null;

        if(tipo.toUpperCase().equals("DEBITO"))  tarjeta = new TarjetaDebito(cliente, numero);
        else if (tipo.toUpperCase().equals("CREDITO")) tarjeta = new TarjetaCredito(cliente, numero);

        else throw new Exception("El tipo de tarjeta es inválido");

        return tarjeta;
    }
}
