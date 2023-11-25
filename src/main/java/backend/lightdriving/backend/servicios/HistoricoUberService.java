package backend.lightdriving.backend.servicios;

import backend.lightdriving.backend.modelos.HistoricoUber;

public interface HistoricoUberService {

    public boolean eliminarHistoricoUber(int idHistoricoUber);
    
    public HistoricoUber obtenerHistoricoUber(int idHistoricoUber);
}
