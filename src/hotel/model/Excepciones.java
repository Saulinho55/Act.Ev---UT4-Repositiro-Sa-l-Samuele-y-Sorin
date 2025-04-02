package hotel.model;

public class Excepciones {
    class ReservaNoDisponibleException extends Exception {
        public ReservaNoDisponibleException(String mensaje) {
            super(mensaje);
        }
    }
    class ClienteNoEncontradoException extends Exception {
        public ClienteNoEncontradoException(String mensaje) {
            super(mensaje);
        }
    }
}
