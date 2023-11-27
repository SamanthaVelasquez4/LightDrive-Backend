package backend.lightdriving.backend.servicios;

import backend.lightdriving.backend.modelos.TipoUber;

public interface TipoUberService {

    public boolean crearTipoUber(TipoUber TipoUber);

    public boolean eliminarTipoUber(int idTipoUber);

    public boolean actualizarTipoUber(int idTipoUber, TipoUber TipoUber);
    
    public TipoUber obtenerTipoUber(int idTipoUber);
}
