package hotel.model;

import hotel.model.EstadoHabitacion;
import hotel.model.TipoHabitacion;
import hotel.model.TipoHabitacion.Tipo;

public class Habitacion {
    private int Numero;
    private TipoHabitacion Tipo;
    private EstadoHabitacion Estado;
    private String Descripcion;
    private double PrecioNoche;

    public Habitacion(int Numero, TipoHabitacion Tipo, String Descripcion) {
        this.Numero = Numero;
        this.Tipo = Tipo;
        this.Estado = EstadoHabitacion.DISPONIBLE; 
        this.Descripcion = Descripcion;
        PrecioTipoHabitacion(Tipo);
    }

    private void PrecioTipoHabitacion(TipoHabitacion Tipo) {
        if (Tipo == null || Tipo.getTipo() == null) {
            throw new IllegalArgumentException("El tipo de habitación no puede ser nulo");
        }
        switch (Tipo.getTipo()) { 
            case INDIVIDUAL:
                this.PrecioNoche = 50;
                break;
            case DOBLE:
                this.PrecioNoche = 80;
                break;
            case SUITE:
                this.PrecioNoche = 150;
                break;
            default:
                throw new IllegalArgumentException("Tipo de habitación no válido");
        }
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    public TipoHabitacion getTipo() {
        return Tipo;
    }

    public void setTipo(TipoHabitacion Tipo) {
        this.Tipo = Tipo;
        PrecioTipoHabitacion(Tipo); 
    }

    public EstadoHabitacion getEstado() {
        return Estado;
    }

    public void setEstado(EstadoHabitacion Estado) {
        this.Estado = Estado;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public double getPrecioNoche() {
        return PrecioNoche;
    }

    public void setPrecioNoche(double PrecioNoche) {
        this.PrecioNoche = PrecioNoche;
    }
}
