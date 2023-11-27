package backend.lightdriving.backend.servicios;

import java.util.List;

import backend.lightdriving.backend.dto.ActualizarUberDto;
import backend.lightdriving.backend.dto.CoordenadaDto;
import backend.lightdriving.backend.dto.UberDto;
import backend.lightdriving.backend.modelos.Uber;

public interface UberService {

    public boolean actualizarUber(int idUber, ActualizarUberDto uber);

    public boolean cambiarCarro(int idUber, UberDto uber);

    public Uber obtenerUber(int idUber);

    public List<Uber> obtenerUbersCercanos(CoordenadaDto conductoresCercaDto);
}
