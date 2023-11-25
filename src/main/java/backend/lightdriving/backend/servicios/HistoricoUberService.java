package backend.lightdriving.backend.servicios;

import backend.lightdriving.backend.modelos.HistoricoUber;

public interface HistoricoUberService {
    public HistoricoUber crearHistoricoUber(HistoricoUber HistoricoUber);

    public boolean eliminarHistoricoUber(int idHistoricoUber);

    public boolean actualizarHistoricoUber(int idHistoricoUber, HistoricoUber HistoricoUber);
    
    public HistoricoUber ontenerHistoricoUber(int idHistoricoUber);
}
