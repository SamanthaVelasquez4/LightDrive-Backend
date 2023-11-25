package backend.lightdriving.backend.servicios;

import java.util.List;

import backend.lightdriving.backend.modelos.Conductor;
import backend.lightdriving.backend.modelos.HistoricoUber;

public interface ConductorService {

    public Conductor login(String correo, String contrasena);

    public Conductor crearConductor(Conductor Conductor);

    public boolean eliminarConductor(int idConductor);

    public boolean actualizarConductor(int idConductor, Conductor Conductor);

    public Conductor obtenerConductor(int idConductor);
    
    public List<HistoricoUber> obtenerHistoricoUbers(int idConductor);
}
