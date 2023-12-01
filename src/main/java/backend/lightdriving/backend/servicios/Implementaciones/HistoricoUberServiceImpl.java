package backend.lightdriving.backend.servicios.Implementaciones;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.lightdriving.backend.dto.HistoricoUberDto;
import backend.lightdriving.backend.modelos.HistoricoUber;
import backend.lightdriving.backend.repositorios.ConductorRepository;
import backend.lightdriving.backend.repositorios.HistoricoUberRepository;
import backend.lightdriving.backend.servicios.HistoricoUberService;

@Service
public class HistoricoUberServiceImpl implements HistoricoUberService{

    @Autowired
    private HistoricoUberRepository historicoUberRepository;

    @Autowired
    private ConductorRepository conductorRepository;

    @Override
    public HistoricoUber obtenerHistoricoUber(int idHistoricoUber) {
        
        if(this.historicoUberRepository.existsById(idHistoricoUber)){
            return this.historicoUberRepository.findById(idHistoricoUber).get();
        }

        return null;
    }

    @Override
    public HistoricoUberDto obtenerHistoricosConductor(int idConductor) {

        List<HistoricoUber> todos= this.historicoUberRepository.findAll();
        List<HistoricoUber> historicoConductor= new ArrayList<>();
        String placaUberActual= this.conductorRepository.findById(idConductor).get().getUber().getPlaca();

        HistoricoUberDto respuesta= new HistoricoUberDto();

        for (HistoricoUber historicoUber : todos) {
            if(historicoUber.getConductor().getIdConductor()==idConductor){
                if(historicoUber.getPlaca().equals(placaUberActual)){
                    respuesta.setUberActual(historicoUber);
                }else{
                    historicoConductor.add(historicoUber);
                }
                
            }
        }

        respuesta.setHistoricoUbers(historicoConductor);

        return respuesta;
    }


    
}
