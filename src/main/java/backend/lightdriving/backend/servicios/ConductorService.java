package backend.lightdriving.backend.servicios;

import backend.lightdriving.backend.dto.ActualizarConductorDto;
import backend.lightdriving.backend.dto.ConductorDto;
import backend.lightdriving.backend.dto.ConductorLoginDto;
import backend.lightdriving.backend.dto.LoginDto;

public interface ConductorService {

    public int login(LoginDto login);

    public ConductorLoginDto obtenerInfoPaginaPrincipal(int idConductor);

    public boolean crearConductor(ConductorDto conductor);

    public boolean eliminarConductor(int idConductor);

    public boolean actualizarConductor(int idConductor, ActualizarConductorDto actualizar);
    
}
