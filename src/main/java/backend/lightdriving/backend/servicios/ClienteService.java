package backend.lightdriving.backend.servicios;

import backend.lightdriving.backend.modelos.Cliente;

public interface ClienteService {
    public Cliente logIn(String correo, String contrasna);
    public Cliente crearCliente(Cliente cliente);
    public boolean eliminarCliente(int idCliente);
    public boolean actualizarCliente(int idCliente, Cliente cliente);
    public Cliente obtenerCliente(int idCliente);
}
