package backend.lightdriving.backend.servicios;

import java.util.List;

import backend.lightdriving.backend.dto.ActualizarConductorDto;
import backend.lightdriving.backend.dto.ConductorDto;
import backend.lightdriving.backend.dto.LoginDto;
import backend.lightdriving.backend.modelos.Conductor;
import backend.lightdriving.backend.modelos.HistoricoUber;

public interface ConductorService {

    public Conductor login(LoginDto login);

    public boolean crearConductor(ConductorDto conductor);

    public boolean eliminarConductor(int idConductor);

    public boolean actualizarConductor(int idConductor, ActualizarConductorDto actualizar);

    public Conductor obtenerConductor(int idConductor);
    
}
