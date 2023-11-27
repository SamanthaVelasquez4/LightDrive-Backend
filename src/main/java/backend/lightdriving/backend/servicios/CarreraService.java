package backend.lightdriving.backend.servicios;

import backend.lightdriving.backend.dto.CarreraDto;
import backend.lightdriving.backend.modelos.Carrera;

public interface CarreraService {

    public Carrera nvaCarrera(CarreraDto carreraDto);

    public  Carrera obtenerCarrera(int idCarrera);

    public boolean eliminarCarrera(int idCarrera);

    public boolean actualizarCarrera(int idCarrera, Carrera carrera);

    public boolean cambiarEstadoCarrera(int idCarrera);

}
