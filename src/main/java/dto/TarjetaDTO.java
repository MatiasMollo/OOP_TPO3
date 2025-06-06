package dto;

public class TarjetaDTO {
    protected int dniCliente;
    protected String numero;

    public TarjetaDTO(int dniCliente, String numero) {
        this.dniCliente = dniCliente;
        this.numero = numero;
    }

    public int getDniCliente() {
        return dniCliente;
    }

    public String getNumero() {
        return this.numero;
    }

    @Override
    public String toString() {
        return "dniCliente=" + dniCliente +
                ", numero=" + numero;
    }
}
