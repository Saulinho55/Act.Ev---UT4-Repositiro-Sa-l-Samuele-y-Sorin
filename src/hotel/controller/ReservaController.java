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
public class ReservaController { // Controlador de reservas
    private List<Reserva> reservas;

    public ReservaController() {
    public ReservaController() { // Constructor
        this.reservas = new ArrayList<>();
    }
    private int contador = 1;
    public int GenerarID() {
    private int contador = 1; // Contador para generar ID de reservas
    public int GenerarID() { // Genera un ID único para cada reserva
        return contador++;
    }
    public void AñadirReserva(Reserva reserva) {
        if (reserva.getCheckIn().isBefore(LocalDate.now())) {
            throw new ReservaNoDisponibleException("No se pueden realizar reservas en fechas pasadas.");
    public void AñadirReserva(Reserva reserva) { // Método para añadir una reserva
        if (reserva.getCheckIn().isBefore(LocalDate.now())) { // Si la fecha de check-in es anterior a la fecha actual
            throw new ReservaNoDisponibleException("No se pueden realizar reservas en fechas pasadas."); // Lanza una excepción de ReservaNoDisponibleException
        }
        if (reserva.getCheckOut().isBefore(reserva.getCheckIn())) {
            throw new ReservaNoDisponibleException("El check-out no puede ser antes del check-in.");
        if (reserva.getCheckOut().isBefore(reserva.getCheckIn())) { // Si la fecha de check-out es anterior a la fecha de check-in
            throw new ReservaNoDisponibleException("El check-out no puede ser antes del check-in."); // Lanza una excepción de ReservaNoDisponibleException
        }
        if (!HabitacionController.Disponibilidad(reserva.getHabitacion(), reserva.getCheckIn(), reserva.getCheckOut(), reservas)) {
            throw new ReservaNoDisponibleException("La habitación ya está reservada en ese rango de fechas.");
        if (!HabitacionController.Disponibilidad(reserva.getHabitacion(), reserva.getCheckIn(), reserva.getCheckOut(), reservas)) { // Verifica la disponibilidad de la habitación
            throw new ReservaNoDisponibleException("La habitación ya está reservada en ese rango de fechas."); // Lanza una excepción de ReservaNoDisponibleException
        }
        Cliente cliente = reserva.getCliente();
        long reservasActivas = 0;
        for (Reserva reservas : reservas) {
            if (reservas.getCliente().equals(cliente) && reservas.getCheckOut().isAfter(LocalDate.now())) {
                reservasActivas++;
        Cliente cliente = reserva.getCliente(); // Obtiene el cliente de la reserva
        long reservasActivas = 0; // Contador de reservas activas
        for (Reserva reservas : reservas) { // Recorre la lista de reservas
            if (reservas.getCliente().equals(cliente) && reservas.getCheckOut().isAfter(LocalDate.now())) { // Si la reserva pertenece al cliente y la fecha de check-out es posterior a la fecha actual
                reservasActivas++; // Aumenta el contador de reservas activas
            }
        }
        long diasReserva = ChronoUnit.DAYS.between(reserva.getCheckIn(), reserva.getCheckOut());
        if (diasReserva > 90) {
            throw new ReservaNoDisponibleException("Error. EL maximo de dias de reserva es 90.");
        long diasReserva = ChronoUnit.DAYS.between(reserva.getCheckIn(), reserva.getCheckOut()); // Calcula la cantidad de días de la reserva
        if (diasReserva > 90) { // Si la cantidad de días de la reserva es mayor a 90
            throw new ReservaNoDisponibleException("Error. EL maximo de dias de reserva es 90."); // Lanza una excepción de ReservaNoDisponibleException
        }
        if (reservasActivas >= 3) {
            throw new ReservaNoDisponibleException("El cliente no puede tener más de 3 reservas activas.");
        if (reservasActivas >= 3) { // Si el cliente ya tiene 3 reservas activas
            throw new ReservaNoDisponibleException("El cliente no puede tener más de 3 reservas activas."); // Lanza una excepción de ReservaNoDisponibleException
        }
        reserva.setId(GenerarID());
        reservas.add(reserva);
        reserva.setId(GenerarID()); // Genera un ID único para la reserva
        reservas.add(reserva); // Añade la reserva a la lista de reservas
        reserva.getHabitacion().setEstado(EstadoHabitacion.RESERVADA); //Poner en reservado la habitación
        System.out.println("Reserva creada correctamente.");
        System.out.println("Reserva creada correctamente."); 
    }
    public void cancelarReserva(Reserva reserva) {
        if (reserva.getCheckIn().isBefore(LocalDate.now())) {
            throw new ReservaNoDisponibleException("No se puede cancelar una reserva que ya ha comenzado.");
    public void cancelarReserva(Reserva reserva) { // Método para cancelar una reserva
        if (reserva.getCheckIn().isBefore(LocalDate.now())) { // Si la fecha de check-in es anterior a la fecha actual
            throw new ReservaNoDisponibleException("No se puede cancelar una reserva que ya ha comenzado."); // Lanza una excepción de ReservaNoDisponibleException
        }

        if (!reservas.contains(reserva)) {
            throw new ClienteNoEncontradoException("La reserva no existe.");
        if (!reservas.contains(reserva)) { // Si la reserva no existe en la lista de reservas
            throw new ClienteNoEncontradoException("La reserva no existe."); // Lanza una excepción de ClienteNoEncontradoException
        }
        reservas.remove(reserva);
        reservas.remove(reserva); // Elimina la reserva de la lista de reservas
        HabitacionController.ActualizarCancelacion(reserva.getHabitacion()); //Actualizar la habitación a disponible
        System.out.println("Reserva cancelada correctamente.");
    }
    public double PrecioTotal(Reserva reserva) {
        long dias = java.time.temporal.ChronoUnit.DAYS.between(reserva.getCheckIn(), reserva.getCheckOut());
        return reserva.getHabitacion().getPrecioNoche() * dias;
    public double PrecioTotal(Reserva reserva) { // Método para calcular el precio total de una reserva
        long dias = java.time.temporal.ChronoUnit.DAYS.between(reserva.getCheckIn(), reserva.getCheckOut()); // Calcula la cantidad de días de la reserva
        return reserva.getHabitacion().getPrecioNoche() * dias; // Calcula el precio total multiplicando el precio por noche por la cantidad de días
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
    public List<Reserva> ReservasActivasClientes(Cliente cliente) { // Método para obtener las reservas activas de un cliente
        List<Reserva> ReservasActivas = new ArrayList<>(); // Crea una lista para almacenar las reservas activas del cliente
        for (Reserva reserva : reservas) { // Recorre la lista de reservas
            if (reserva.getCliente().equals(cliente) && reserva.getCheckOut().isAfter(LocalDate.now())) { // Si la reserva pertenece al cliente y la fecha de check-out es posterior a la fecha actual
                ReservasActivas.add(reserva); // Añade la reserva a la lista de reservas activas
            }
        }
        return ReservasActivas;
        return ReservasActivas; // Devuelve la lista de reservas activas del cliente
    }
    public List<Reserva> HistorialReservas(Cliente cliente) {
        List<Reserva> ReservasLista = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva.getCliente().equals(cliente)) {
                ReservasLista.add(reserva);
    public List<Reserva> HistorialReservas(Cliente cliente) { // Método para obtener el historial de reservas de un cliente
        List<Reserva> ReservasLista = new ArrayList<>(); // Crea una lista para almacenar el historial de reservas del cliente
        for (Reserva reserva : reservas) { // Recorre la lista de reservas
            if (reserva.getCliente().equals(cliente)) { // Si la reserva pertenece al cliente
                ReservasLista.add(reserva); // Añade la reserva a la lista de reservas
            }
        }
        return ReservasLista;
        return ReservasLista;  // Devuelve la lista de reservas del cliente
    }
    public Reserva BuscarReservaPorId(int id) {
        for (Reserva reserva : reservas) {
            if (reserva.getId() == id) {
                return reserva;
    public Reserva BuscarReservaPorId(int id) {  // Método para buscar una reserva por su ID
        for (Reserva reserva : reservas) { // Recorre la lista de reservas
            if (reserva.getId() == id) { // Si el ID de la reserva coincide con el ID buscado
                return reserva; // Devuelve la reserva
            }
        }
        return null; 
    }
    public List<Reserva> BuscarReservaCliente(Cliente cliente) {
        List<Reserva> Cliente = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva.getCliente().equals(cliente)) {
                Cliente.add(reserva); 
    public List<Reserva> BuscarReservaCliente(Cliente cliente) { // Método para buscar reservas de un cliente
        List<Reserva> Cliente = new ArrayList<>(); // Crea una lista para almacenar las reservas del cliente
        for (Reserva reserva : reservas) { // Recorre la lista de reservas
            if (reserva.getCliente().equals(cliente)) { // Si la reserva pertenece al cliente
                Cliente.add(reserva);  // Añade la reserva a la lista de reservas del cliente
            }
        }
        return Cliente; 
        return Cliente; // Devuelve la lista de reservas del cliente
    }
}