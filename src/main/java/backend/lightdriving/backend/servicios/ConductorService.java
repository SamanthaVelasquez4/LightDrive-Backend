package backend.lightdriving.backend.servicios;

import java.util.List;

import backend.lightdriving.backend.dto.ActualizarConductorDto;
import backend.lightdriving.backend.dto.ConductorDto;
import backend.lightdriving.backend.dto.ConductorLoginDto;
import backend.lightdriving.backend.dto.LoginDto;
import backend.lightdriving.backend.modelos.Carrera;
import backend.lightdriving.backend.modelos.Conductor;

public interface ConductorService {

    public ConductorLoginDto login(LoginDto login);

    public boolean crearConductor(ConductorDto conductor);

    public boolean eliminarConductor(int idConductor);

    public boolean actualizarConductor(int idConductor, ActualizarConductorDto actualizar);

    public Conductor obtenerConductor(int idConductor);

    public List<Carrera> obtenerCarreras (int idConductor);
    
}
