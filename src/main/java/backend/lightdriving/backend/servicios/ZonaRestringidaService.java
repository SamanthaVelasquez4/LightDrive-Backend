package backend.lightdriving.backend.servicios;

import backend.lightdriving.backend.dto.CoordenadaDto;

public interface ZonaRestringidaService {

    public boolean verificarPosicion (CoordenadaDto coordenada);
}
