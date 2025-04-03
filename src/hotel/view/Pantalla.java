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
public class Pantalla { // Clase para manejar la interfaz de usuario
    private HabitacionController habitacionController; // Controlador de habitaciones
    private ClienteController clienteController;
    private ReservaController reservaController;
    public Pantalla(HabitacionController habitacionController, ClienteController clienteController, ReservaController reservaController) {
        this.habitacionController = habitacionController;
        this.clienteController = clienteController;
        this.reservaController = reservaController;
    }
    public void Menu() {
    public void Menu() { // Método para mostrar el menú principal
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
            switch (opcion) { // Muestra las opciones y espera la opción del usuario
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
    private void Reservar(Scanner scanner) { // Método para reservar una habitación
        System.out.println("Ingrese el ID del cliente:");
        int id = scanner.nextInt();
        Cliente cliente = clienteController.BuscarCliente(id);
        if (cliente == null) {
            System.out.println("Cliente no encontrado. Creando nuevo cliente.");
            System.out.println("Cliente no encontrado");
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
        if (CheckIn.isBefore(LocalDate.now())) {
            System.out.println("No se pueden realizar reservas en una fecha pasada");
            return;
        }
        if (CheckOut.isBefore(CheckIn)) {
            System.out.println("El check-out no puede ser antes del check-in");
            return;
        }
        if (CheckIn.isEqual(CheckOut)) {
            System.out.println("El check-in y el check-out no pueden ser el mismo día");
            return;
        }
        try {
            int idreserva = reservaController.GenerarID();
            Reserva reserva = new Reserva(idreserva, habitacion, cliente, CheckIn, CheckOut);
            double Total = reservaController.PrecioTotal(reserva);
            reservaController.AñadirReserva(reserva);
            System.out.println("El precio total de la reserva es: " +Total);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }
    private void Cancelar(Scanner scanner) {
    private void Cancelar(Scanner scanner) { // Método para cancelar una reserva
        System.out.println("Ingrese el ID de la reserva:");
        int id = scanner.nextInt();
        Reserva reserva = reservaController.BuscarReservaPorId(id);
        if (reserva == null) {
        Reserva reserva = reservaController.BuscarReservaPorId(id); // Busca la reserva por ID
        if (reserva == null) { // Si no encuentra la reserva, muestra un mensaje
            System.out.println("Reserva no encontrada");
            return;
        }
        try {
            reservaController.cancelarReserva(reserva);
            System.out.println("Reserva cancelada con éxito.");
            reservaController.cancelarReserva(reserva); // Cancela la reserva
            System.out.println("Reserva cancelada con éxito."); 
        } catch (Exception e) {
            System.out.println("Error al cancelar. " +e.getMessage());
            System.out.println("Error al cancelar. " +e.getMessage()); // Muestra el mensaje de error
        }
    }
    private void HistorialClientes(Scanner scanner) {
    private void HistorialClientes(Scanner scanner) { // Método para mostrar el historial de reservas de un cliente
        System.out.println("Ingrese el ID del cliente:");
        int id = scanner.nextInt();
        Cliente cliente = clienteController.BuscarCliente(id);
        Cliente cliente = clienteController.BuscarCliente(id); // Busca el cliente por ID
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
            System.out.println("Cliente no encontrado"); // Si no encuentra el cliente, muestra un mensaje
            return;
        }
        List<Reserva> reservas = reservaController.BuscarReservaCliente(cliente);
        List<Reserva> reservas = reservaController.BuscarReservaCliente(cliente); // Busca las reservas del cliente
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas activas.");
            System.out.println("No hay reservas activas."); // Si no hay reservas, muestra un mensaje
            return;
        }
        System.out.println("Historial de reservas del cliente " +cliente.getNombre()+ ":");
        for (Reserva reserva : reservas) {
        System.out.println("Historial de reservas del cliente " +cliente.getNombre()+ ":"); // Muestra el nombre del cliente
        for (Reserva reserva : reservas) { // Muestra las reservas del cliente
            System.out.println("ID Reserva: " +reserva.getId()+ " Habitacion: " +reserva.getHabitacion().getNumero()+ " Check-in: " +reserva.getCheckIn()+ " Check-out: " +reserva.getCheckOut());
        }
    }
    private void ReservasActivas(Scanner scanner) {
    private void ReservasActivas(Scanner scanner) { // Método para mostrar las reservas activas de un cliente
        System.out.println("Ingrese el ID del cliente:");
        int id = scanner.nextInt();
        Cliente cliente = clienteController.BuscarCliente(id);
        Cliente cliente = clienteController.BuscarCliente(id); // Busca el cliente por ID
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
            System.out.println("Cliente no encontrado"); // Si no encuentra el cliente, muestra un mensaje
            return;
        }
        List<Reserva> reservasActivas = reservaController.ReservasActivasClientes(cliente);
        List<Reserva> reservasActivas = reservaController.ReservasActivasClientes(cliente); // Busca las reservas activas del cliente
        if (reservasActivas.isEmpty()) {
            System.out.println("No hay reservas activas.");
            System.out.println("No hay reservas activas."); // Si no hay reservas activas, muestra un mensaje
            return;
        }
        System.out.println("Reservas activas:");
        for (Reserva reserva : reservasActivas) {
        System.out.println("Reservas activas:"); // Muestra el nombre del cliente
        for (Reserva reserva : reservasActivas) { // Muestra las reservas activas del cliente
            System.out.println("ID Reserva: " +reserva.getId()+ " Habitacion: " +reserva.getHabitacion().getNumero()+ " Check-in: " +reserva.getCheckIn()+ " Check-out: " +reserva.getCheckOut());
        }
    }
    private void BuscarHabTipo(Scanner scanner) {
        System.out.println("Ingrese el tipo de habitación (SIMPLE, DOBLE, SUITE)");
    private void BuscarHabTipo(Scanner scanner) { // Método para buscar habitaciones por tipo
        System.out.println("Ingrese el tipo de habitación (INDIVIDUAL, DOBLE, SUITE)");
        String tipohab = scanner.next().toUpperCase();
        TipoHabitacion tipo = TipoHabitacion.valueOf(tipohab);
        TipoHabitacion tipo = TipoHabitacion.valueOf(tipohab); // Convierte el tipo de habitación a un valor del enum
        List<Habitacion> habitaciones = habitacionController.BuscarTipo(tipo);
        if (habitaciones.isEmpty()) {
            System.out.println("No hay habitaciones");
            System.out.println("No hay habitaciones"); // Si no hay habitaciones, muestra un mensaje
            return;
        }
        for (Habitacion habitacion : habitaciones) {
        for (Habitacion habitacion : habitaciones) { // Muestra las habitaciones encontradas
            System.out.println("Número: " +habitacion.getNumero()+ " Estado actual: " +habitacion.getEstado());
        }
    }
    private void BuscarHabEstado(Scanner scanner) {
    private void BuscarHabEstado(Scanner scanner) { // Método para buscar habitaciones por estado
        System.out.println("Ingrese el estado de la habitación (DISPONIBLE, OCUPADA o RESERVADA):");
        String estado = scanner.next().toUpperCase();
        String estado = scanner.next().toUpperCase(); // Convierte el estado a mayúsculas
        EstadoHabitacion estadoHabitacion = EstadoHabitacion.valueOf(estado);
        List<Habitacion> habitaciones = habitacionController.BuscarEstado(estadoHabitacion);
        if (habitaciones.isEmpty()) {
            System.out.println("No hay habitaciones");
            System.out.println("No hay habitaciones"); // Si no hay habitaciones, muestra un mensaje
            return;
        }
        for (Habitacion habitacion : habitaciones) {
        for (Habitacion habitacion : habitaciones) { // Muestra las habitaciones encontradas
            System.out.println("Número: " +habitacion.getNumero()+ " Tipo: " +habitacion.getTipo());
        }
    }
    private void BuscarHabNum(Scanner scanner) {
    private void BuscarHabNum(Scanner scanner) { // Método para buscar habitaciones por número
        System.out.println("Ingrese el número de la habitación:");
        int numero = scanner.nextInt();
        Habitacion habitacion = habitacionController.BuscarNumero(numero);
        Habitacion habitacion = habitacionController.BuscarNumero(numero); // Busca la habitación por número
        if (habitacion == null) {
            System.out.println("Habitación no encontrada");
            System.out.println("Habitación no encontrada"); // Si no encuentra la habitación, muestra un mensaje
            return;
        }
        System.out.println("Número: " +habitacion.getNumero()+ " Tipo: " +habitacion.getTipo()+ " Estado: " +habitacion.getEstado());
        System.out.println("Número: " +habitacion.getNumero()+ " Tipo: " +habitacion.getTipo()+ " Estado: " +habitacion.getEstado()); // Muestra la habitación encontrada
    }
    private void AñadirCliente(Scanner scanner) {
        System.out.println("Ingrese el nombre del cliente:");
        String nombre = scanner.next();
    private void AñadirCliente(Scanner scanner) { // Método para añadir un nuevo cliente
        System.out.println("Ingrese el nombre del cliente:"); 
        scanner.nextLine();
        String nombre = scanner.nextLine(); // Lee el nombre del cliente
        System.out.println("Ingrese el ID del cliente (Número Telefonico):");
        int id = scanner.nextInt();
        if (clienteController.BuscarCliente(id) != null) {
        int id = scanner.nextInt(); // Lee el ID del cliente
        if (String.valueOf(id).length() != 9) { // Verifica que el ID tenga 9 dígitos
            System.out.println("Error. Debe contener 9 digitos."); // Si no tiene 9 dígitos, muestra un mensaje
            return;
        }
        if (clienteController.BuscarCliente(id) != null) { // Verifica si el cliente ya existe
            System.out.println("El cliente ya existe");
            return;
        }
        Cliente cliente = new Cliente(id, nombre);
        clienteController.AñadirCliente(cliente);   
        Cliente cliente = new Cliente(id, nombre); // Crea un nuevo cliente
        clienteController.AñadirCliente(cliente);  // Añade el cliente al controlador
    }
    private void EliminarCliente(Scanner scanner) {
    private void EliminarCliente(Scanner scanner) { //  Método para eliminar un cliente
        System.out.println("Ingrese el ID del cliente:");
        int id = scanner.nextInt();
        Cliente cliente = clienteController.BuscarCliente(id);
        Cliente cliente = clienteController.BuscarCliente(id); // Busca el cliente por ID
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
            System.out.println("Cliente no encontrado"); // Si no encuentra el cliente, muestra un mensaje
            return;
        }
        clienteController.EliminarCliente(cliente);
        clienteController.EliminarCliente(cliente); // Elimina el cliente del controlador
        System.out.println("El cliente fue eliminado.");
    }
}
