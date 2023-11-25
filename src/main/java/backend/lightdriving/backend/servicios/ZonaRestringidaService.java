package backend.lightdriving.backend.servicios;

import backend.lightdriving.backend.modelos.ZonaRestringida;

public interface ZonaRestringidaService {

    public ZonaRestringida crearZonaRestringida(ZonaRestringida ZonaRestringida);

    public boolean eliminarZonaRestringida(int idZonaRestringida);

    public boolean actualizarZonaRestringida(int idZonaRestringida, ZonaRestringida ZonaRestringida);
    
    public ZonaRestringida ontenerZonaRestringida(int idZonaRestringida);
}
