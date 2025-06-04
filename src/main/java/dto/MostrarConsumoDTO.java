package dto;

import java.util.Date;

public class MostrarConsumoDTO {
    private Date fecha;
    private String establecimiento;
    private float monto;

    public MostrarConsumoDTO(Date fecha, String establecimiento, float monto) {
        this.fecha = fecha;
        this.establecimiento = establecimiento;
        this.monto = monto;
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
