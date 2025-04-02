package hotel.model.excepciones;

public class ReservaNoDisponibleException extends RuntimeException {
    public ReservaNoDisponibleException(String message) {
        super(message);
    }
}