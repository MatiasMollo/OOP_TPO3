package models;

import java.util.ArrayList;
import java.util.List;

public abstract class Tarjeta {
    protected Cliente cliente;
    protected List<Consumo> consumos;
    protected String numero;

    public Tarjeta(Cliente cliente, String numero) {
        this.cliente = cliente;
        this.numero = numero;
        this.consumos = new ArrayList<>();
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void agregarConsumo(Consumo consumo)
    {
        this.consumos.add(consumo);
    }
    public List<Consumo> getConsumos() {
        return this.consumos;
    }

    public String getNumero() {
        return this.numero;
    }

    @Override
    public String toString() {
        return "Tarjeta{" +
                "cliente=" + cliente +
                ", consumos=" + consumos +
                ", numero=" + numero +
                '}';
    }
}
