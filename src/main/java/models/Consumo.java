package models;

import java.util.Date;

public class Consumo {

    private Date fecha;
    private String establecimiento;
    private float monto;

    public Consumo(Date fecha, String establecimiento, float monto) {
        this.fecha = fecha;
        this.establecimiento = establecimiento;
        this.monto = monto;
    }


    public Date getFecha() {
        return this.fecha;
    }

    public String getEstablecimiento() {
        return this.establecimiento;
    }

    public float getMonto() {
        return this.monto;
    }

    @Override
    public String toString() {
        return "Consumo{" +
                "fecha=" + fecha +
                ", establecimiento='" + establecimiento + '\'' +
                ", monto=" + monto +
                '}';
    }
}
