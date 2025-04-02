package hotel.controller;

import hotel.model.Reserva;
import hotel.model.Cliente;
import hotel.model.Habitacion;
import hotel.model.excepciones.ReservaNoDisponibleException;
import hotel.model.excepciones.ClienteNoEncontradoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaController {
    private List<Reserva> reservas;

    public ReservaController() {
        this.reservas = new ArrayList<>();
    }
    public void AñadirReserva(Reserva reserva) {
        if (reserva.getCheckIn().isBefore(LocalDate.now())) {
            throw new ReservaNoDisponibleException("No se pueden realizar reservas en fechas pasadas.");
        }

        Cliente cliente = reserva.getCliente();
        long reservasActivas = 0;
        for (Reserva reservas : reservas) {
            if (reservas.getCliente().equals(cliente) && reservas.getCheckOut().isAfter(LocalDate.now())) {
                reservasActivas++;
            }
        }
        if (reservasActivas >= 3) {
            throw new ReservaNoDisponibleException("El cliente no puede tener más de 3 reservas activas.");
        }

        reservas.add(reserva);
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
        System.out.println("Reserva cancelada correctamente.");
    }
    public double PrecioTotal(Reserva reserva) {
        long dias = java.time.temporal.ChronoUnit.DAYS.between(reserva.getCheckIn(), reserva.getCheckOut());
        return reserva.getHabitacion().getPrecioNoche() * dias;
    }
}

