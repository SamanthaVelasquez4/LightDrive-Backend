package backend.lightdriving.backend.servicios;

import backend.lightdriving.backend.dto.HistoricoUberDto;
import backend.lightdriving.backend.modelos.HistoricoUber;

public interface HistoricoUberService {
    
    public HistoricoUber obtenerHistoricoUber(int idHistoricoUber);

    public HistoricoUberDto obtenerHistoricosConductor(int idConductor);
}
