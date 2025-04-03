package hotel.controller;
import hotel.model.Cliente;
import java.util.List;
import hotel.model.Reserva;
public class ClienteController {
    private List<Cliente> clientes;
    private List<Reserva> reservas;
    public ClienteController(List<Cliente> clientes, List<Reserva> reservas) {
        this.clientes = clientes;
        this.reservas = reservas;
    }
    public void mostrarResumenClientes() {
        System.out.println("Resumen de Clientes:");
        for (Cliente cliente : clientes) {
            System.out.println("ID: " +cliente.getId()+ " Nombre: " +cliente.getNombre());
            for (Reserva reserva : reservas) {
                if (reserva.getCliente().equals(cliente)) {
                    System.out.println("Reserva: " +reserva.getHabitacion().getNumero()+ " desde " +reserva.getCheckIn() + " a "+reserva.getCheckOut()+ " De tipo:" +reserva.getHabitacion().getTipo());
                }
            }
        }
    }
    public void AñadirCliente(Cliente cliente) {
        clientes.add(cliente);
        System.out.println("Cliente añadido correctamente.");
    }
    public void EliminarCliente(Cliente cliente) {
        if (clientes.contains(cliente)) {
            clientes.remove(cliente);
            System.out.println("Cliente eliminado correctamente");
        } else {
            System.out.println("El cliente no existe");
        }
    }
    public Cliente BuscarCliente(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                System.out.println("Cliente: "+cliente.getNombre());
                return cliente;
            }
        }
        System.out.println("Error. Cliente no encontrado.");
        return null;
    }
}
