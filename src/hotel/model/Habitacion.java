package hotel.model;
import hotel.model.EstadoHabitacion;
import hotel.model.TipoHabitacion;
import hotel.model.TipoHabitacion.Tipo;


public class Habitacion {
    private int numero;
    private TipoHabitacion tipo;
    private EstadoHabitacion estado;
    private String descripcion;
    private double PrecioNoche;

    public Habitacion(int numero, TipoHabitacion tipo, EstadoHabitacion estado, String descripcion, double precioNoche) {
        this.numero = numero;
        this.tipo = tipo;
        this.estado = EstadoHabitacion.DISPONIBLE;
        this.descripcion = descripcion;
        PrecioTipoHabitacion(tipo);
    }

    private void PrecioTipoHabitacion(TipoHabitacion tipo) {
        switch (tipo) {
            case INDIVIDUAL:
                PrecioNoche = 50;
                break;
            case DOBLE:
                PrecioNoche = 80;
                break;
            case SUITE:
                PrecioNoche = 150;
                break;
        }
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoHabitacion tipo) {
        this.tipo = tipo;
    }

    public EstadoHabitacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoHabitacion estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioNoche() {
        return PrecioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        PrecioNoche = precioNoche;
    }
}
