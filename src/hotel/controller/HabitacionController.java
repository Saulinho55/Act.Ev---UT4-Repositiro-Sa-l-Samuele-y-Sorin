package hotel.controller;
import hotel.model.Habitacion.EstadoHabitacion;
import hotel.model.Habitacion.TipoHabitacion;
import hotel.model.Habitacion;
import hotel.model.Reserva;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HabitacionController { // Clase HabitacionController
    public List<Habitacion> habitaciones;
    public HabitacionController(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }
    public static boolean Disponibilidad(Habitacion habitacion, LocalDate checkIn, LocalDate checkOut, List<Reserva> reservas) { // Método para verificar la disponibilidad de una habitación
        for (Reserva reserva : reservas) {
            if (reserva.getHabitacion().equals(habitacion)) { // Si la habitación coincide con la reserva
                if (!(checkOut.isBefore(reserva.getCheckIn()) || checkIn.isAfter(reserva.getCheckOut()))) { // Si las fechas de la reserva no están disponibles
                    return false;
                }
            }
        }
        return habitacion.getEstado() == EstadoHabitacion.DISPONIBLE; // Si la habitación está disponible
    }

    public static void ActualizarCancelacion(Habitacion habitacion) { // Método para actualizar el estado de la habitación al cancelar una reserva
        habitacion.setEstado(EstadoHabitacion.DISPONIBLE); // Cambia el estado de la habitación a DISPONIBLE
        System.out.println("La habitación " + habitacion.getNumero() + "fue cancelada"); // Muestra un mensaje de cancelación
    }

    public Habitacion BuscarNumero(int numero) { // Método para buscar una habitación por su número
        for (Habitacion habitacion : habitaciones) { // Recorre la lista de habitaciones
            if (habitacion.getNumero() == numero) { // Si el número de la habitación coincide con el número buscado
                return habitacion; // Devuelve la habitación
            }
        }
        return null;
    }

    public List<Habitacion> BuscarEstado(EstadoHabitacion estado) { // Método para buscar habitaciones por su estado
        List<Habitacion> ResultadoHab = new ArrayList<>(); // Crea una lista para almacenar las habitaciones que coinciden con el estado buscado
        for (Habitacion habitacion : habitaciones) { // Recorre la lista de habitaciones
            if (habitacion.getEstado() == estado) { // Si el estado de la habitación coincide con el estado buscado 
                ResultadoHab.add(habitacion); // Añade la habitación a la lista de resultados
            }
        }
        return ResultadoHab; // Devuelve la lista de habitaciones que coinciden con el estado buscado
    }
    public List<Habitacion> BuscarTipo(TipoHabitacion tipo) { // Método para buscar habitaciones por su tipo
        List<Habitacion> ResultadoHab = new ArrayList<>(); // Crea una lista para almacenar las habitaciones que coinciden con el tipo buscado
        for (Habitacion habitacion : habitaciones) { // Recorre la lista de habitaciones
            if (habitacion.getTipo() == tipo) { // Si el tipo de la habitación coincide con el tipo buscado
                ResultadoHab.add(habitacion); // Añade la habitación a la lista de resultados
            }
        }
        return ResultadoHab; // Devuelve la lista de habitaciones que coinciden con el tipo buscado
    }
    public void ResumenHab() { // Método para mostrar un resumen de las habitaciones
        System.out.println("Resumen de Habitaciones:");
        for (Habitacion habitacion : habitaciones) { // Recorre la lista de habitaciones
            System.out.println("Habitación:" +habitacion.getNumero()+ " Tipo: " +habitacion.getTipo()+ " Estado: " +habitacion.getEstado()); // Muestra el número, tipo y estado de la habitación
        }
    }
}

