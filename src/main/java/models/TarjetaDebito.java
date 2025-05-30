package models;

public class TarjetaDebito extends Tarjeta{
    private float devIVA;

    public TarjetaDebito(Cliente cliente, String numero, float devIVA) {
        super(cliente, numero);
        this.devIVA = devIVA;
    }

    public float getDevIVA() {
        return this.devIVA;
    }
}
