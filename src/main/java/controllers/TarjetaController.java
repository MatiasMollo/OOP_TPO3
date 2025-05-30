package controllers;

import models.Tarjeta;

import java.util.ArrayList;
import java.util.List;

public class TarjetaController {
    private List<Tarjeta> tarjetas;
    private static TarjetaController instance;

    private TarjetaController()
    {
        tarjetas = new ArrayList<Tarjeta>();
    }

    private static synchronized TarjetaController getInstance()
    {
        if(instance == null) instance = new TarjetaController();

        return instance;
    }

    public List<Tarjeta> getTarjetas()
    {
        return tarjetas;
    }

    public void addTarjeta(Tarjeta tarjeta) throws Exception
    {
        boolean found = false;
        for (int x=0; x<tarjetas.size(); x++){
            if(tarjetas.get(x).getNumero().equals(tarjeta.getNumero())) found = true;
        }

        if(found) throw new Exception("La tarjeta ya se encuentra en el listado");
        else this.tarjetas.add(tarjeta);
    }
}
