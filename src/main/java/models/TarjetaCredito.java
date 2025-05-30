package models;

public class TarjetaCredito extends Tarjeta{
    private float interes;

    public TarjetaCredito(Cliente cliente, String numero, float interes) {
        super(cliente, numero);
        this.interes = interes;
    }

    public float getInteres() {
        return this.interes;
    }
}
