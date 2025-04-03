package hotel.view;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;
import hotel.controller.ClienteController;
import hotel.controller.HabitacionController;
import hotel.controller.ReservaController;
import hotel.model.Cliente;
import hotel.model.Habitacion;
import hotel.model.Habitacion.EstadoHabitacion;
import hotel.model.Habitacion.TipoHabitacion;
import hotel.model.Reserva;

public class Pantalla {
    private HabitacionController habitacionController;
    private ClienteController clienteController;
    private ReservaController reservaController;
    public Pantalla(HabitacionController habitacionController, ClienteController clienteController, ReservaController reservaController) {
        this.habitacionController = habitacionController;
        this.clienteController = clienteController;
        this.reservaController = reservaController;
    }
    public void Menu() {
        int opcion;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al menú. Escoja la opciones siguientes:");
        do {
            System.out.println("0. Salir");
            System.out.println("1. Reservar habitacion");
            System.out.println("2. Cancelar reserva");
            System.out.println("3. Consultar habitaciones");
            System.out.println("4. Historial de clientes");
            System.out.println("5. Resumen de clientes");
            System.out.println("6. Buscar reservas activas");
            System.out.println("7. Buscar habitación (Tipo)");
            System.out.println("8. Buscar habitación (Estado)");
            System.out.println("9. Buscar habitación (Número)");
            System.out.println("10. Añadir cliente");
            System.out.println("11. Eliminar cliente");
            System.out.println("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    Reservar(scanner);
                    break;
                case 2:
                    Cancelar(scanner);
                    break;
                case 3:
                    habitacionController.ResumenHab();
                    break;
                case 4:
                    HistorialClientes(scanner);
                    break;
                case 5:
                    clienteController.mostrarResumenClientes();
                    break;
                case 6:
                    ReservasActivas(scanner);
                    break;
                case 7:
                    BuscarHabTipo(scanner);
                    break;
                case 8:
                    BuscarHabEstado(scanner);
                    break;
                case 9:
                    BuscarHabNum(scanner);
                    break;
                case 10:
                    AñadirCliente(scanner);
                    break;
                case 11:
                    EliminarCliente(scanner);
                    break;
                case 0:
                    System.out.println("Gracias por usar este sistema");
                    break;
                default:
                    System.out.println("Error. Opcion invalida.");
            }
        } while (opcion != 0);
        scanner.close();
    }
    private void Reservar(Scanner scanner) {
        System.out.println("Ingrese el ID del cliente:");
        int id = scanner.nextInt();
        Cliente cliente = clienteController.BuscarCliente(id);
        if (cliente == null) {
            System.out.println("Cliente no encontrado. Creando nuevo cliente.");
            return;
        }
        System.out.println("Ingrese el número de la habitación:");
        int numero = scanner.nextInt();
        Habitacion habitacion = habitacionController.BuscarNumero(numero);
        if (habitacion == null) {
            System.out.println("Habitación no encontrada");
            return;
        }
        System.out.println("Ingrese la fecha de check-in (EJ: YYYY-MM-DD):");
        LocalDate CheckIn = LocalDate.parse(scanner.next());
        System.out.println("Ingrese la fecha de check-out (EJ: YYYY-MM-DD):");
        LocalDate CheckOut = LocalDate.parse(scanner.next());
        int idreserva = reservaController.GenerarID();
        Reserva reserva = new Reserva(idreserva, habitacion, cliente, CheckIn, CheckOut);
        double Total = reservaController.PrecioTotal(reserva);
        System.out.println("El precio total de la reserva es: " +Total);
        try {
            reservaController.AñadirReserva(reserva);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }
    private void Cancelar(Scanner scanner) {
        System.out.println("Ingrese el ID de la reserva:");
        int id = scanner.nextInt();
        Reserva reserva = reservaController.BuscarReservaPorId(id);
        if (reserva == null) {
            System.out.println("Reserva no encontrada");
            return;
        }
        try {
            reservaController.cancelarReserva(reserva);
            System.out.println("Reserva cancelada con éxito.");
        } catch (Exception e) {
            System.out.println("Error al cancelar. " +e.getMessage());
        }
    }
    private void HistorialClientes(Scanner scanner) {
        System.out.println("Ingrese el ID del cliente:");
        int id = scanner.nextInt();
        Cliente cliente = clienteController.BuscarCliente(id);
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
            return;
        }
        List<Reserva> reservas = reservaController.BuscarReservaCliente(cliente);
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas activas.");
            return;
        }
        System.out.println("Historial de reservas del cliente " +cliente.getNombre()+ ":");
        for (Reserva reserva : reservas) {
            System.out.println("ID Reserva: " +reserva.getId()+ " Habitacion: " +reserva.getHabitacion().getNumero()+ " Check-in: " +reserva.getCheckIn()+ " Check-out: " +reserva.getCheckOut());
        }
    }
    private void ReservasActivas(Scanner scanner) {
        System.out.println("Ingrese el ID del cliente:");
        int id = scanner.nextInt();
        Cliente cliente = clienteController.BuscarCliente(id);
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
            return;
        }
        List<Reserva> reservasActivas = reservaController.ReservasActivasClientes(cliente);
        if (reservasActivas.isEmpty()) {
            System.out.println("No hay reservas activas.");
            return;
        }
        System.out.println("Reservas activas:");
        for (Reserva reserva : reservasActivas) {
            System.out.println("ID Reserva: " +reserva.getId()+ " Habitacion: " +reserva.getHabitacion().getNumero()+ " Check-in: " +reserva.getCheckIn()+ " Check-out: " +reserva.getCheckOut());
        }
    }
    private void BuscarHabTipo(Scanner scanner) {
        System.out.println("Ingrese el tipo de habitación (SIMPLE, DOBLE, SUITE)");
        String tipohab = scanner.next().toUpperCase();
        TipoHabitacion tipo = TipoHabitacion.valueOf(tipohab);
        List<Habitacion> habitaciones = habitacionController.BuscarTipo(tipo);
        if (habitaciones.isEmpty()) {
            System.out.println("No hay habitaciones");
            return;
        }
        for (Habitacion habitacion : habitaciones) {
            System.out.println("Número: " +habitacion.getNumero()+ " Estado actual: " +habitacion.getEstado());
        }
    }
    private void BuscarHabEstado(Scanner scanner) {
        System.out.println("Ingrese el estado de la habitación (DISPONIBLE, OCUPADA o RESERVADA):");
        String estado = scanner.next().toUpperCase();
        EstadoHabitacion estadoHabitacion = EstadoHabitacion.valueOf(estado);
        List<Habitacion> habitaciones = habitacionController.BuscarEstado(estadoHabitacion);
        if (habitaciones.isEmpty()) {
            System.out.println("No hay habitaciones");
            return;
        }
        for (Habitacion habitacion : habitaciones) {
            System.out.println("Número: " +habitacion.getNumero()+ " Tipo: " +habitacion.getTipo());
        }
    }
    private void BuscarHabNum(Scanner scanner) {
        System.out.println("Ingrese el número de la habitación:");
        int numero = scanner.nextInt();
        Habitacion habitacion = habitacionController.BuscarNumero(numero);
        if (habitacion == null) {
            System.out.println("Habitación no encontrada");
            return;
        }
        System.out.println("Número: " +habitacion.getNumero()+ " Tipo: " +habitacion.getTipo()+ " Estado: " +habitacion.getEstado());
    }
    private void AñadirCliente(Scanner scanner) {
        System.out.println("Ingrese el nombre del cliente:");
        String nombre = scanner.next();
        System.out.println("Ingrese el ID del cliente (Número Telefonico):");
        int id = scanner.nextInt();
        if (clienteController.BuscarCliente(id) != null) {
            System.out.println("El cliente ya existe");
            return;
        }
        Cliente cliente = new Cliente(id, nombre);
        clienteController.AñadirCliente(cliente);   
    }
    private void EliminarCliente(Scanner scanner) {
        System.out.println("Ingrese el ID del cliente:");
        int id = scanner.nextInt();
        Cliente cliente = clienteController.BuscarCliente(id);
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
            return;
        }
        clienteController.EliminarCliente(cliente);
        System.out.println("El cliente fue eliminado.");
    }
}
