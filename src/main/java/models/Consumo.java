package models;

public class Consumo {

    private int mes;
    private int ano;
    private String establecimiento;
    private float monto;

    public Consumo(int mes, int ano, String establecimiento, float monto) {
        this.mes = mes;
        this.ano = ano;
        this.establecimiento = establecimiento;
        this.monto = monto;
    }

    public int getMes() {
        return this.mes;
    }

    public int getAno() {
        return this.ano;
    }

    public String getEstablecimiento() {
        return this.establecimiento;
    }

    public float getMonto() {
        return this.monto;
    }
}
