package controllers;

import dto.AgregarConsumoDTO;
import dto.AgregarTarjetaDTO;
import dto.ConsultarConsumoDTO;
import models.*;

import java.util.*;

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
     * @param tarjetaDto TarjetaDTO
     * @return Tarjeta
     * @throws Exception
     */
    public Tarjeta agregarTarjeta(AgregarTarjetaDTO tarjetaDto) throws Exception
    {
        boolean found = false;
        int x = 0;
        Tarjeta tarjeta = this.buscarTarjeta(Integer.toString(tarjetaDto.getDni()));

        // Se verifica que no haya una tarjeta con el dni
        if(tarjeta != null) throw new Exception("El cliente ya posee una tarjeta");
        else{
            ClienteController clientes = ClienteController.getInstance();

            // Se verifica que exista el cliente, lanza una excepción de forma automática en caso de no existir
            Cliente cliente = clientes.getCliente(tarjetaDto.getDni());

            // Crea la tarjeta
            tarjeta = crearTarjeta(cliente, tarjetaDto.getTipo());
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

    /**
     * Agrega el consumo a la tarjeta correspondiente
     * @param agregarConsumoDTO AgregarConsumoDTO
     * @return
     * @throws Exception
     */
    public Consumo agregarConsumo(AgregarConsumoDTO agregarConsumoDTO) throws Exception
    {
        //verifica que tarjeta exista
        Tarjeta tarjeta = this.buscarTarjeta(agregarConsumoDTO.getNumeroTarjeta());
        if(tarjeta == null) throw new Exception("La tarjeta no existe en los registros");

        Consumo consumo = parsearConsumo(agregarConsumoDTO);
        tarjeta.agregarConsumo(consumo);

        return consumo;
    }

    public Consumo parsearConsumo(AgregarConsumoDTO agregarConsumoDTO){
        Date fecha = agregarConsumoDTO.getFecha();
        String establecimiento = agregarConsumoDTO.getEstablecimiento();
        Float monto = agregarConsumoDTO.getMonto();

        return new Consumo(fecha, establecimiento, monto);
    }

    /**
     * Realiza una búsqueda sobre las tarjetas en base al número de tarjeta o dni de cliente
     * @param numero String
     * @return Tarjeta
     */
    public Tarjeta buscarTarjeta(String numero)
    {
        Tarjeta tarjeta = null;
        int x = 0;

        // Para números de 16 dígitos, se busca por número de tarjeta. Para números con menos digitos, por DNI
        boolean busquedaPorDni = numero.length() < 16;

        while(x < this.tarjetas.size() && tarjeta == null){
            if((busquedaPorDni && Integer.parseInt(numero) == this.tarjetas.get(x).getCliente().getDni()) || numero.equals(this.tarjetas.get(x).getNumero()))
                tarjeta = this.tarjetas.get(x);
            x++;
        }

        return tarjeta;
    }

    /**
     * Calcula el consumo de la tarjeta
     * Para Tarjetas de Débito: Se suman todos los consumos del período y se descuenta la devolución del IVA,
     * calculada en base a un porcentaje específico.
     * Para Tarjetas de Crédito: Se suman todos los consumos del período y se añade el interés,
     * calculado en base a un porcentaje específico.
     * @param consultarConsumoDTO
     * @return
     */
    public double calcularConsumoReal(ConsultarConsumoDTO consultarConsumoDTO) {
        List<Consumo> consumosEnRango = getConsumos(consultarConsumoDTO);
        Tarjeta tarjeta = buscarTarjeta(consultarConsumoDTO.getNumeroTarjeta());
        double total = 0;

        for (Consumo consumo : consumosEnRango) {
            total += consumo.getMonto();
        }

        if (tarjeta instanceof TarjetaCredito credito) {
            return aplicarInteresCredito(total, credito);
        } else if (tarjeta instanceof TarjetaDebito debito) {
            return aplicarDevolucionIVA(total, debito);
        } else {
            throw new IllegalArgumentException("Tipo de tarjeta desconocido.");
        }
    }

    public float aplicarDevolucionIVA(double total, TarjetaDebito tarjeta){
        float porcentajeIva = tarjeta.getDevIVA();
        return (float) (total - (porcentajeIva * total / 100.0));
    }
    public float aplicarInteresCredito(double total, TarjetaCredito tarjeta){
        float porcentajeInteres = tarjeta.getInteres();
        return (float) (total + (porcentajeInteres * total / 100.0));
    }

    public List<Consumo> getConsumos(ConsultarConsumoDTO consultarConsumoDTO) {
        Tarjeta tarjeta = buscarTarjeta(consultarConsumoDTO.getNumeroTarjeta());
        List<Consumo> consumos = tarjeta.getConsumos();
        List<Consumo> consumosEnRango = new ArrayList<>();

        for (Consumo consumo : consumos) {
            Date fecha = consumo.getFecha();

            if (fecha.compareTo(consultarConsumoDTO.getFechaInicio()) >= 0
                    && fecha.compareTo(consultarConsumoDTO.getFechaFin()) <= 0) {
                consumosEnRango.add(consumo);
            }
        }

        return consumosEnRango;
    }

}
