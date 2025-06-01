import controllers.ClienteController;
import controllers.TarjetaController;
import models.Tarjeta;

public class Test {


    public static void main(String[] args)
    {
        ClienteController clientes = ClienteController.getInstance();
        TarjetaController tarjetas = TarjetaController.getInstance();

        // PASAR A TRAVÉS DE VISTAS Y ARMAR OBJETOS DTOS

        try{
            /*
            clientes.agregarCliente("Matias Mollo", 11843958);
            clientes.agregarCliente("Matias Mollo", 11843950);

            Tarjeta tarjeta = tarjetas.agregarTarjeta(11843950,"credito");
            System.out.println(tarjeta);

             */
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}
