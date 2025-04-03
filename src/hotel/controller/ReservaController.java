package hotel.controller;
import hotel.model.Reserva;
import hotel.model.Cliente;
import hotel.model.Habitacion;
import hotel.controller.HabitacionController;
import hotel.model.Habitacion.EstadoHabitacion;
import hotel.model.excepciones.ReservaNoDisponibleException;
import hotel.model.excepciones.ClienteNoEncontradoException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ReservaController {
    private List<Reserva> reservas;

    public ReservaController() {
        this.reservas = new ArrayList<>();
    }
    private int contador = 1;
    public int GenerarID() {
        return contador++;
    }
    public void AñadirReserva(Reserva reserva) {
        if (reserva.getCheckIn().isBefore(LocalDate.now())) {
            throw new ReservaNoDisponibleException("No se pueden realizar reservas en fechas pasadas.");
        }
        if (reserva.getCheckOut().isBefore(reserva.getCheckIn())) {
            throw new ReservaNoDisponibleException("El check-out no puede ser antes del check-in.");
        }
        if (!HabitacionController.Disponibilidad(reserva.getHabitacion(), reserva.getCheckIn(), reserva.getCheckOut(), reservas)) {
            throw new ReservaNoDisponibleException("La habitación ya está reservada en ese rango de fechas.");
        }
        Cliente cliente = reserva.getCliente();
        long reservasActivas = 0;
        for (Reserva reservas : reservas) {
            if (reservas.getCliente().equals(cliente) && reservas.getCheckOut().isAfter(LocalDate.now())) {
                reservasActivas++;
            }
        }
        long diasReserva = ChronoUnit.DAYS.between(reserva.getCheckIn(), reserva.getCheckOut());
        if (diasReserva > 90) {
            throw new ReservaNoDisponibleException("Error. EL maximo de dias de reserva es 90.");
        }
        if (reservasActivas >= 3) {
            throw new ReservaNoDisponibleException("El cliente no puede tener más de 3 reservas activas.");
        }
        reserva.setId(GenerarID());
        reservas.add(reserva);
        reserva.getHabitacion().setEstado(EstadoHabitacion.RESERVADA); //Poner en reservado la habitación
        System.out.println("Reserva creada correctamente.");
    }
    public void cancelarReserva(Reserva reserva) {
        if (reserva.getCheckIn().isBefore(LocalDate.now())) {
            throw new ReservaNoDisponibleException("No se puede cancelar una reserva que ya ha comenzado.");
        }

        if (!reservas.contains(reserva)) {
            throw new ClienteNoEncontradoException("La reserva no existe.");
        }
        reservas.remove(reserva);
        HabitacionController.ActualizarCancelacion(reserva.getHabitacion()); //Actualizar la habitación a disponible
        System.out.println("Reserva cancelada correctamente.");
    }
    public double PrecioTotal(Reserva reserva) {
        long dias = java.time.temporal.ChronoUnit.DAYS.between(reserva.getCheckIn(), reserva.getCheckOut());
        return reserva.getHabitacion().getPrecioNoche() * dias;
    }
    public void Check(Reserva reserva) {
        if (reserva.getCheckIn().isBefore(LocalDate.now())) {
            throw new ReservaNoDisponibleException("No se puede realizar el check-in al haber pasado la fecha.");
        }
        if (!reservas.contains(reserva)) {
            throw new ClienteNoEncontradoException("La reserva no existe");
        }
        System.out.println("Fue realizado correctamente");
        reserva.getHabitacion().setEstado(EstadoHabitacion.OCUPADA);
    }
    public List<Reserva> ReservasActivasClientes(Cliente cliente) {
        List<Reserva> ReservasActivas = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva.getCliente().equals(cliente) && reserva.getCheckOut().isAfter(LocalDate.now())) {
                ReservasActivas.add(reserva);
            }
        }
        return ReservasActivas;
    }
    public List<Reserva> HistorialReservas(Cliente cliente) {
        List<Reserva> ReservasLista = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva.getCliente().equals(cliente)) {
                ReservasLista.add(reserva);
            }
        }
        return ReservasLista;
    }
    public Reserva BuscarReservaPorId(int id) {
        for (Reserva reserva : reservas) {
            if (reserva.getId() == id) {
                return reserva;
            }
        }
        return null; 
    }
    public List<Reserva> BuscarReservaCliente(Cliente cliente) {
        List<Reserva> Cliente = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva.getCliente().equals(cliente)) {
                Cliente.add(reserva); 
            }
        }
        return Cliente; 
    }
}