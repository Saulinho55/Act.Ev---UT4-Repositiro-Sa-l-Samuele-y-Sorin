package hotel.controller;
import hotel.model.Habitacion.EstadoHabitacion; 
import hotel.model.Habitacion;
import hotel.model.Reserva;
import java.time.LocalDate;
import java.util.List;

public class HabitacionController {
    public List<Habitacion> habitaciones;
    public HabitacionController(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }
    public static boolean Disponibilidad(Habitacion habitacion, LocalDate checkIn, LocalDate checkOut, List<Reserva> reservas) {
        for (Reserva reserva : reservas) {
            if (reserva.getHabitacion().equals(habitacion)) {
                if (!(checkOut.isBefore(reserva.getCheckIn()) || checkIn.isAfter(reserva.getCheckOut()))) {
                    return false;
                }
            }
        }
        return habitacion.getEstado() == EstadoHabitacion.DISPONIBLE;
    }

    public static void ActualizarCancelacion(Habitacion habitacion) {
        habitacion.setEstado(EstadoHabitacion.DISPONIBLE);
        System.out.println("La habitación " + habitacion.getNumero() + "fue cancelada");
    }
}
