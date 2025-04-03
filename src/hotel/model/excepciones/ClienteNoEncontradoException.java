package hotel.model.excepciones;

public class ClienteNoEncontradoException extends RuntimeException { // Excepción personalizada para cliente no encontrado
    public ClienteNoEncontradoException(String message) {
        super(message);
    }
}