package backend.lightdriving.backend.servicios;

import backend.lightdriving.backend.dto.CarreraClienteDto;
import backend.lightdriving.backend.dto.CarreraConductorDto;
import backend.lightdriving.backend.dto.CarreraDto;
import backend.lightdriving.backend.modelos.Carrera;

public interface CarreraService {

    public boolean nvaCarrera(CarreraDto carreraDto);

    public boolean eliminarCarrera(int idCarrera);

    public boolean actualizarCarrera(int idCarrera, Carrera carrera);

    public boolean cambiarEstadoCarrera(int idCarrera);

    public CarreraClienteDto obtenerDetalleCarreraCliente(int idCarrera);

    public CarreraConductorDto obtenerDetalleCarreraConductor (int idCarrera);

}
