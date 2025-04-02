package hotel.model;
import java.time.LocalDate;
import java.util.List; //importaciones



public class Reserva {
    private int id;
    private Habitacion habitacion;
    private Cliente cliente;
    private LocalDate CheckIn;
    private LocalDate CheckOut;
    private double PrecioTotal; 

    public Reserva(int id, Habitacion habitacion, Cliente cliente, LocalDate checkIn, LocalDate checkOut) {
        this.id = id;
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.CheckIn = checkIn;
        this.CheckOut = checkOut;
        this.PrecioTotal = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getCheckIn() {
        return CheckIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        CheckIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return CheckOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        CheckOut = checkOut;
    }

    public double getPrecioTotal() {
        return PrecioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        PrecioTotal = precioTotal;
    }
}
