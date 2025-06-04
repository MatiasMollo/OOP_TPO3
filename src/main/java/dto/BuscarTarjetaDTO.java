package dto;

public class BuscarTarjetaDTO {
    private String dni;

    public BuscarTarjetaDTO(String dni) {
        this.dni = dni;
    }

    public String getNumero() {
        return dni;
    }
}
