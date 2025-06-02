package dto;

import java.util.Date;

public class ConsultarConsumoDTO {

    private String numeroTarjeta;
    private Date fechaInicio;
    private Date fechaFin;

    public ConsultarConsumoDTO(String numeroTarjeta, Date fechaInicio, Date fechaFin) {
        this.numeroTarjeta = numeroTarjeta;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }
}
