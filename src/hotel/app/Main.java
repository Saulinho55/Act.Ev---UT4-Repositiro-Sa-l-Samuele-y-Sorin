import hotel.model.Cliente;
import hotel.model.Habitacion;
import hotel.model.Habitacion.EstadoHabitacion;
import hotel.model.Habitacion.TipoHabitacion;
import hotel.model.Reserva;
import hotel.view.Pantalla;
import hotel.controller.ClienteController;
import hotel.controller.HabitacionController;
import hotel.controller.ReservaController;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Habitacion> habitaciones = new ArrayList<>();
        for (int i = 101; i <= 105; i++) {
            habitaciones.add(new Habitacion(i, TipoHabitacion.INDIVIDUAL, EstadoHabitacion.DISPONIBLE, "Habitación estándar", 50));
        }
        for (int i = 201; i <= 205; i++) {
            habitaciones.add(new Habitacion(i, TipoHabitacion.DOBLE, EstadoHabitacion.DISPONIBLE, "Habitación doble", 80));
        }
        for (int i = 301; i <= 305; i++) {
            habitaciones.add(new Habitacion(i, TipoHabitacion.SUITE, EstadoHabitacion.DISPONIBLE, "Suite de lujo", 150));
        }
        HabitacionController habitacionController = new HabitacionController(habitaciones);
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente(678324523, "Saúl Espino Santana"));
        clientes.add(new Cliente(659087546, "Alberto Moleiro"));
        clientes.add(new Cliente(603237684, "Fernando Alonso"));
        List<Reserva> reservas = new ArrayList<>();
        ClienteController clienteController = new ClienteController(clientes, reservas);
        ReservaController reservaController = new ReservaController();
        Pantalla pantalla = new Pantalla(habitacionController, clienteController, reservaController);
        pantalla.Menu();}
}
