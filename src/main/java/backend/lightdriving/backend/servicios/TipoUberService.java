package backend.lightdriving.backend.servicios;



import java.util.List;

import backend.lightdriving.backend.modelos.TipoUber;

public interface TipoUberService {
    
    public TipoUber obtenerTipoUber(int idTipoUber);

    public List<TipoUber> obtenerTodos();
}
