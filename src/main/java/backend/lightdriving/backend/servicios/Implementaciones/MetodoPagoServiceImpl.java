package backend.lightdriving.backend.servicios.Implementaciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.lightdriving.backend.modelos.MetodoPago;
import backend.lightdriving.backend.repositorios.MetodoPagoRepository;
import backend.lightdriving.backend.servicios.MetodoPagoService;

@Service
public class MetodoPagoServiceImpl implements MetodoPagoService{

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Override
    public boolean crearMetodoPago(MetodoPago metodoPago) {
        if(metodoPago != null){
            this.metodoPagoRepository.save(metodoPago);
            return true;
        }

        return false;
    }

    @Override
    public boolean eliminarMetodoPago(int idMetodoPago) {
        
        if(this.metodoPagoRepository.existsById(idMetodoPago)){
            this.metodoPagoRepository.deleteById(idMetodoPago);
            return true;
        }

        return false;
    }

    @Override
    public boolean actualizarMetodoPago(int idMetodoPago, MetodoPago metodoPago) {
        if(this.metodoPagoRepository.existsById(idMetodoPago)){
            MetodoPago metodoPago2= this.metodoPagoRepository.findById(idMetodoPago).get();
            metodoPago2.setDescripcion(metodoPago.getDescripcion());
            return true;
        }

        return false;
    }

    @Override
    public MetodoPago obtenerMetodoPago(int idMetodoPago) {
        
        if(this.metodoPagoRepository.existsById(idMetodoPago)){
            return this.metodoPagoRepository.findById(idMetodoPago).get();
        }

        return null;
    }

    @Override
    public List<MetodoPago> obtenerMetodos() {
        return this.metodoPagoRepository.findAll();
    }

    
}
