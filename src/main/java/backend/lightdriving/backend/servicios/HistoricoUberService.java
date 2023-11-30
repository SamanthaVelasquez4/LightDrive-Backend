package backend.lightdriving.backend.servicios;

import java.util.List;

import backend.lightdriving.backend.modelos.HistoricoUber;

public interface HistoricoUberService {
    
    public HistoricoUber obtenerHistoricoUber(int idHistoricoUber);

    public List<HistoricoUber> obtenerHistoricosConductor(int idConductor);
}
