package dto;

public class AgregarTarjetaDTO {
    private int dni;
    private String tipo;

    public AgregarTarjetaDTO(int dni, String tipo) {
        this.dni = dni;
        this.tipo = tipo;
    }

    public int getDni() {
        return dni;
    }

    public String getTipo() {
        return tipo;
    }
}
