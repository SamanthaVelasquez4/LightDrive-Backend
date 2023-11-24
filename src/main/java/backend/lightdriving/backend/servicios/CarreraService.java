package backend.lightdriving.backend.servicios;

import java.util.List;

import backend.lightdriving.backend.Dto.CarreraDto;
import backend.lightdriving.backend.modelos.Carrera;
import backend.lightdriving.backend.modelos.Cliente;
import backend.lightdriving.backend.modelos.Conductor;

public interface CarreraService {
    public Carrera nvaCarrera(CarreraDto carreraDto);
    public  Carrera obtenCarrera(int idCarrera);
    public boolean eliminarCarrera(Carrera carrera);
    public boolean actualizarCarrera(int idCarrera, Carrera carrera);
    public boolean cambiarEstadoCarrera(int estado);
    public List<Cliente> obtenerCarrerasPorCliente(int idCliente);
    public List<Conductor> obtenerCarrerasPorConductor(int idConductor);
}
