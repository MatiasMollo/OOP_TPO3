package models;

import java.util.List;

public abstract class Tarjeta {
    protected Cliente cliente;
    protected List<Consumo> consumos;
    protected String numero;

    public Tarjeta(Cliente cliente, String numero) {
        this.cliente = cliente;
        this.numero = numero;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public List<Consumo> getConsumos() {
        return this.consumos;
    }

    public String getNumero() {
        return this.numero;
    }
}
