package hotel.model.excepciones;

public class ReservaNoDisponibleException extends RuntimeException { // Excepción personalizada para reserva no disponible
    public ReservaNoDisponibleException(String message) {
        super(message);
    }
}