package hotel.model;

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
        if (Descripcion == null || Descripcion.isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede ser nula o vacía");
        }
        this.Descripcion = Descripcion;
    }

    public double getPrecioNoche() {
        return PrecioNoche;
    }

    public void setPrecioNoche(double PrecioNoche) {
        if (PrecioNoche < 0) {
            throw new IllegalArgumentException("El precio por noche no puede ser negativo");
        }
        this.PrecioNoche = PrecioNoche;
    }
}
