package models;

public class TarjetaDebito extends Tarjeta{
    private float devIVA = 0.21F;

    public TarjetaDebito(Cliente cliente, String numero) {
        super(cliente, numero);
    }

    public float getDevIVA() {
        return this.devIVA;
    }
}
