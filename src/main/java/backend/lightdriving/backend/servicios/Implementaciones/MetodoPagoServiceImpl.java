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
    public List<MetodoPago> obtenerMetodos() {
        return this.metodoPagoRepository.findAll();
    }

    
}
