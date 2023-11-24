package backend.lightdriving.backend.servicios;

import backend.lightdriving.backend.modelos.TipoUber;

public interface TipoUberService {
    public TipoUber crearTipoUber(TipoUber TipoUber);
    public boolean eliminarTipoUber(int idTipoUber);
    public boolean actualizarTipoUber(int idTipoUber, TipoUber TipoUber);
    public TipoUber ontenerTipoUber(int idTipoUber);
}
