package models;

public class TarjetaCredito extends Tarjeta{
    private float interes = 1.5F;

    public TarjetaCredito(Cliente cliente, String numero) {
        super(cliente, numero);
    }

    public float getInteres() {
        return this.interes;
    }


}
