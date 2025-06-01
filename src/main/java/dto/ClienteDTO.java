package dto;

public class ClienteDTO {
    private String nombre;
    private int dni;

    public ClienteDTO(String nombre, int dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDni() {
        return dni;
    }
}
