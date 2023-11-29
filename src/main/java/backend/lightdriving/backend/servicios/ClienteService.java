package backend.lightdriving.backend.servicios;

import java.util.List;

import backend.lightdriving.backend.dto.LoginDto;
import backend.lightdriving.backend.dto.CoordenadaDto;
import backend.lightdriving.backend.dto.FacturasClienteDto;
import backend.lightdriving.backend.modelos.Carrera;
import backend.lightdriving.backend.modelos.Cliente;

public interface ClienteService {

    public int login(LoginDto login);

    public FacturasClienteDto obtenerInformacion( int idCliente);

    public boolean crearCliente(Cliente cliente);

    public boolean eliminarCliente(int idCliente);

    public boolean actualizarCliente(int idCliente, Cliente cliente);

    public Cliente obtenerCliente(int idCliente);

    public CoordenadaDto obtenerUbicacion(int idCliente);

    public List<Carrera> obtenerCarreras(int idCliente);
}
