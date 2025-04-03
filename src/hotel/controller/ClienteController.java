package hotel.controller;
import hotel.model.Cliente;
import java.util.List;
import hotel.model.Reserva;
public class ClienteController {
    private List<Cliente> clientes;
    private List<Reserva> reservas;
    public ClienteController(List<Cliente> clientes, List<Reserva> reservas) { // Constructor
        this.clientes = clientes;
        this.reservas = reservas;
    }
    public void mostrarResumenClientes() { // Método para mostrar un resumen de los clientes y sus reservas
        System.out.println("Resumen de Clientes:");
        for (Cliente cliente : clientes) { 
            System.out.println("ID: " +cliente.getId()+ " Nombre: " +cliente.getNombre()); // Muestra el ID y nombre del cliente
            for (Reserva reserva : reservas) { // Recorre las reservas
                if (reserva.getCliente().equals(cliente)) { // Si la reserva pertenece al cliente. Muestra el resumen. 
                    System.out.println("Reserva: " +reserva.getHabitacion().getNumero()+ " desde " +reserva.getCheckIn() + " a "+reserva.getCheckOut()+ " De tipo:" +reserva.getHabitacion().getTipo());
                }
            }
        }
    }
    public void AñadirCliente(Cliente cliente) { // Método para añadir un cliente
        clientes.add(cliente); // Añade el cliente a la lista de clientes
        System.out.println("Cliente añadido correctamente."); 
    }
    public void EliminarCliente(Cliente cliente) { // Método para eliminar un cliente
        if (clientes.contains(cliente)) { // Si el cliente existe en la lista de clientes
            clientes.remove(cliente); // Elimina el cliente de la lista
            System.out.println("Cliente eliminado correctamente");
        } else {
            System.out.println("El cliente no existe"); // Si el cliente no existe, muestra un mensaje
        }
    }
    public Cliente BuscarCliente(int id) { // Método para buscar un cliente por su ID
        for (Cliente cliente : clientes) { // Recorre la lista de clientes
            if (cliente.getId() == id) { // Si el ID del cliente coincide con el ID buscado
                System.out.println("Cliente: "+cliente.getNombre()); // Muestra el nombre del cliente
                return cliente; // Devuelve el cliente
            }
        }
        return null;
    }
}
