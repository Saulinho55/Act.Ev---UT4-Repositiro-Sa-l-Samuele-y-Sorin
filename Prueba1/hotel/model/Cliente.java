package hotel.model;
import java.util.List;
import java.util.ArrayList;

public class Cliente {
    private int id;
    private String nombre;
    private List<String> reservas; 
    private List<String> reservasactivas;

    public Cliente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.reservas = new ArrayList<>();
        this.reservasactivas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getReservas() {
        return reservas;
    }

    public void setReservas(List<String> reservas) {
        this.reservas = reservas;
    }

    public List<String> getReservasactivas() {
        return reservasactivas;
    }

    public void setReservasactivas(List<String> reservasactivas) {
        this.reservasactivas = reservasactivas;
    }

    public boolean reservasmax () {
        return reservasactivas.size() <3;
    }

    public void addReserva(String reserva) {
        if (reservasmax()) {
            reservas.add(reserva);
            reservasactivas.add(reserva);
        } else {
            System.out.println("Error. No se pueden añadir mas reservas activas");
        }
    }

    public void FinalizarReserva(String reserva) {
        if (reservasactivas.contains(reserva)) {
            reservasactivas.remove(reserva);
            reservas.add(reserva);
        } else {
            System.out.println("Error. No se puede finalizar");
        }
    }
}

