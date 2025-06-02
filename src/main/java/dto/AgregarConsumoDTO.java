package dto;

import java.util.Date;

public class AgregarConsumoDTO {

    private String numeroTarjeta;
    private Date fecha;
    private String establecimiento;
    private float monto;

    public AgregarConsumoDTO(String numeroTarjeta, Date fecha, String establecimiento, float monto) {
        this.numeroTarjeta = numeroTarjeta;
        this.fecha = fecha;
        this.establecimiento = establecimiento;
        this.monto = monto;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public float getMonto() {
        return monto;
    }
}
