package backend.lightdriving.backend.servicios;

import backend.lightdriving.backend.dto.LoginDto;
import backend.lightdriving.backend.dto.CoordenadaDto;
import backend.lightdriving.backend.modelos.Cliente;

public interface ClienteService {

    public int login(LoginDto login);

    public boolean crearCliente(Cliente cliente);

    public boolean eliminarCliente(int idCliente);

    public boolean actualizarCliente(int idCliente, Cliente cliente);

    public Cliente obtenerCliente(int idCliente);

    public CoordenadaDto obtenerUbicacion(int idCliente);
}
