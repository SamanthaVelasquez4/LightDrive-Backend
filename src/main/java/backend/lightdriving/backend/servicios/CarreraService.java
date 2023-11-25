package backend.lightdriving.backend.servicios;

import java.util.List;

import backend.lightdriving.backend.dto.CarreraDto;
import backend.lightdriving.backend.modelos.Carrera;
import backend.lightdriving.backend.modelos.Cliente;
import backend.lightdriving.backend.modelos.Conductor;

public interface CarreraService {

    public Carrera nvaCarrera(CarreraDto carreraDto);

    public  Carrera obtenerCarrera(int idCarrera);

    public boolean eliminarCarrera(Carrera carrera);

    public boolean actualizarCarrera(int idCarrera, Carrera carrera);

    public boolean cambiarEstadoCarrera(int estado);

}
