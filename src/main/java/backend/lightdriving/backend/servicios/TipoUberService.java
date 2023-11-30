package backend.lightdriving.backend.servicios;



import java.util.List;

import backend.lightdriving.backend.modelos.TipoUber;

public interface TipoUberService {

    public boolean crearTipoUber(TipoUber TipoUber);

    public boolean eliminarTipoUber(int idTipoUber);

    public boolean actualizarTipoUber(int idTipoUber, TipoUber TipoUber);
    
    public TipoUber obtenerTipoUber(int idTipoUber);

    public List<TipoUber> obtenerTodos();
}
