package hotel.model;

public class TipoHabitacion {
    public enum Tipo {
        INDIVIDUAL,
        DOBLE,
        SUITE
    }

    private Tipo tipo;

    public TipoHabitacion(Tipo tipo) {
        this.tipo = tipo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
