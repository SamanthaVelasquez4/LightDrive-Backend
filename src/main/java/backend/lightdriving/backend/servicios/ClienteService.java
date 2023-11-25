package backend.lightdriving.backend.servicios;

import java.util.List;

import backend.lightdriving.backend.dto.LoginDto;
import backend.lightdriving.backend.modelos.Carrera;
import backend.lightdriving.backend.modelos.Cliente;

public interface ClienteService {

    public Cliente login(LoginDto login);

    public boolean crearCliente(Cliente cliente);

    public boolean eliminarCliente(int idCliente);

    public boolean actualizarCliente(int idCliente, Cliente cliente);

    public Cliente obtenerCliente(int idCliente);

    public List<Carrera> obtenerCarreras(int idCliente);
}
