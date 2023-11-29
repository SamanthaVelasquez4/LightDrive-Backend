package backend.lightdriving.backend.servicios.Implementaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.lightdriving.backend.modelos.TipoUber;
import backend.lightdriving.backend.repositorios.TipoUberRepository;
import backend.lightdriving.backend.servicios.TipoUberService;
@Service
public class TipoUberServiceImpl implements TipoUberService{

    @Autowired
    private TipoUberRepository TipoUberRepository;

    @Override
    public TipoUber obtenerTipoUber(int idTipoUber) {
        if(this.TipoUberRepository.existsById(idTipoUber)){
            TipoUber TipoUber= TipoUberRepository.findById(idTipoUber).get();
           return TipoUber;
        }

        return null;
    }
    
}
