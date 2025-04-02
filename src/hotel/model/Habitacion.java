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

public class Habitacion {

    public enum Tipo {
        INDIVIDUAL,
        DOBLE,
        SUITE
    }

    public enum EstadoHabitacion {
        DISPONIBLE,
        OCUPADA,
        LIMPIEZA
    }

    private int Numero;
    private Tipo Tipo;
    private EstadoHabitacion Estado;
    private String Descripcion;
    private double PrecioNoche;

    public Habitacion(int Numero, Tipo Tipo, String Descripcion) {
        if (Tipo == null) {
            throw new IllegalArgumentException("El tipo de habitación no puede ser nulo");
        }
        if (Descripcion == null || Descripcion.isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede ser nula o vacía");
        }

        this.Numero = Numero;
        this.Tipo = Tipo;
        this.Estado = EstadoHabitacion.DISPONIBLE;
        this.Descripcion = Descripcion;
        establecerPrecioPorTipo(Tipo);
    }

    private void establecerPrecioPorTipo(Tipo Tipo) {
        switch (Tipo) {
>>>>>>> 21f939a557a95ff72b5b028d13637d88306ba23d
            case INDIVIDUAL:
                PrecioNoche = 50;
                this.PrecioNoche = 50;
                break;
            case DOBLE:
                PrecioNoche = 80;
                this.PrecioNoche = 80;
                break;
            case SUITE:
                PrecioNoche = 150;
                this.PrecioNoche = 150;
                break;
            default:
                throw new IllegalArgumentException("Tipo de habitación no válido");
        }
    }

    public int getNumero() {
        return numero;
        return Numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

<<<<<<< HEAD
=======
    public Tipo getTipo() {
        return Tipo;
    }

    public void setTipo(Tipo Tipo) {
        if (Tipo == null) {
            throw new IllegalArgumentException("El tipo de habitación no puede ser nulo");
        }
        this.Tipo = Tipo;
        establecerPrecioPorTipo(Tipo); // Actualiza el precio al cambiar el tipo
    }
>>>>>>> 21f939a557a95ff72b5b028d13637d88306ba23d

    public EstadoHabitacion getEstado() {
        return estado;
        return Estado;
    }

    public void setEstado(EstadoHabitacion estado) {
        this.estado = estado;
    public void setEstado(EstadoHabitacion Estado) {
        this.Estado = Estado;
    }

    public String getDescripcion() {
        return descripcion;
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    public void setDescripcion(String Descripcion) {
        if (Descripcion == null || Descripcion.isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede ser nula o vacía");
        }
        this.Descripcion = Descripcion;
    }

    public double getPrecioNoche() {
        return PrecioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        PrecioNoche = precioNoche;
    public void setPrecioNoche(double PrecioNoche) {
        if (PrecioNoche < 0) {
            throw new IllegalArgumentException("El precio por noche no puede ser negativo");
        }
        this.PrecioNoche = PrecioNoche;
    }
}
