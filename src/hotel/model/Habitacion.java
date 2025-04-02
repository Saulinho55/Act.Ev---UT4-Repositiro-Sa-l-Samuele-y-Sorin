package hotel.model;

 enum EstadoHabitacion {
    OCUPADA,
    DISPONIBLE,
    RESERVADA
}
 enum TipoHabitacion {
    INDIVIDUAL,
    DOBLE,
    SUITE
}
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
            default:
                throw new IllegalArgumentException("Tipo de habitación no válido");
        }
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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
