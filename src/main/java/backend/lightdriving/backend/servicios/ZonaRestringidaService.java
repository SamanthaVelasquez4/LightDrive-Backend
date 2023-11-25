package backend.lightdriving.backend.servicios;

import backend.lightdriving.backend.dto.CoordenadaDto;
import backend.lightdriving.backend.dto.RespuestaDto;
import backend.lightdriving.backend.dto.VerificarRutaDto;
import backend.lightdriving.backend.modelos.ZonaRestringida;

public interface ZonaRestringidaService {

    public boolean crearZonaRestringida(ZonaRestringida zonaRestringida);

    public boolean eliminarZonaRestringida(int idZonaRestringida);

    public boolean actualizarZonaRestringida(int idZonaRestringida, ZonaRestringida zonaRestringida);
    
    public ZonaRestringida obtenerZonaRestringida(int idZonaRestringida);
    
    public RespuestaDto verificarRuta(VerificarRutaDto ruta);

    public boolean verificarPosicion (CoordenadaDto coordenada);
}
