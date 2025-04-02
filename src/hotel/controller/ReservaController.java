package hotel.controller;

import hotel.model.Reserva;
import hotel.model.Habitacion;
import java.time.LocalDate;
import hotel.model.Excepciones.ReservaNoDisponibleException;
import hotel.model.Cliente;

    public ReservaController(Reserva reserva, Habitacion habitacion, Cliente cliente) {
        this.reserva = reserva;
        this.habitacion = habitacion;
        this.cliente = cliente;
    }
    public Reserva reserva;
    public Habitacion habitacion;
    public ReservaController(Reserva reserva, Habitacion habitacion) {
        this.reserva = reserva;
        this.habitacion = habitacion;
    }
    public double PrecioTotal() {
        double PrecioTotal = 0;
        LocalDate checkIn = reserva.getCheckIn();
        LocalDate checkOut = reserva.getCheckOut();
        long dias = checkIn.until(checkOut).getDays();
        if (checkIn.isBefore(LocalDate.now())) {
            throw new ReservaNoDisponibleException("No se pueden hacer reservas en el dia seleccionado");
        }
        
        if (checkOut.isBefore(checkIn)) {
            throw new ReservaNoDisponibleException("El check-out no puede ser antes del check-in");
        }
        if (dias > 90) {
            System.out.println("Error. No puede exceder 90 dias");
        }
        PrecioTotal = dias * habitacion.getPrecioNoche();
        return PrecioTotal;
    }
    public void AñadirReserva() {
        if (habitacion.getEstado() == EstadoHabitacion.RESERVADA) {
            throw new ReservaNoDisponibleException("La habitación no está disponible en la fechas seleccionada");
        } else {
            if (cliente.puedeReservar()) {
                cliente.AñadirReserva(reserva);
                double PrecioTotal = PrecioTotal();
                reserva.setPrecioTotal(PrecioTotal);
                habitacion.setEstado(EstadoHabitacion.RESERVADA);
                System.out.println("Reserva añadida correctamente");
            } else {
                throw new ReservaNoDisponibleException("El cliente no puede hacer más de 3 reservas");
            }
        }
    }
    public void CancelarReserva() {
        if (LocalDate.now().isAfter(reserva.getCheckIn())) {
            throw new ReservaNoDisponibleException("No se puede cancelar la reserva después del check-in.");
        }
        if (habitacion.getEstado() == EstadoHabitacion.RESERVADA) {
            habitacion.setEstado(EstadoHabitacion.DISPONIBLE);
            cliente.CancelarReserva(reserva);
            System.out.println("Reserva cancelada correctamente");
        } else {
            throw new ReservaNoDisponibleException("La habitación no está reservada");
        }
    }