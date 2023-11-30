package backend.lightdriving.backend.servicios.Implementaciones;

import java.util.List;

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
    public boolean crearTipoUber(TipoUber TipoUber) {
        if(TipoUber != null){
            TipoUberRepository.save(TipoUber);
            return true;
        }

        return false;
    }

    @Override
    public boolean eliminarTipoUber(int idTipoUber) {
        if(this.TipoUberRepository.existsById(idTipoUber)){
            TipoUber TipoUber = TipoUberRepository.findById(idTipoUber).get();
            TipoUberRepository.delete(TipoUber);
            return true;
        }
        
        return false;
    }

    @Override
    public boolean actualizarTipoUber(int idTipoUber, TipoUber TipoUber) {
        if(this.TipoUberRepository.existsById(idTipoUber)){
            TipoUber TipoUber1 = TipoUberRepository.findById(idTipoUber).get();
            TipoUber1.setDescripcion(TipoUber.getDescripcion());
            TipoUber1.setPrecioBase(TipoUber.getPrecioBase());
            TipoUber1.setPrecioXkm(TipoUber.getPrecioXkm());
            TipoUberRepository.save(TipoUber1);
            return true;
        }

        return false;
    }

    @Override
    public TipoUber obtenerTipoUber(int idTipoUber) {
        if(this.TipoUberRepository.existsById(idTipoUber)){
            TipoUber TipoUber= TipoUberRepository.findById(idTipoUber).get();
           return TipoUber;
        }

        return null;
    }

    @Override
    public List<TipoUber> obtenerTodos() {
       return this.TipoUberRepository.findAll();
    }
    
}
