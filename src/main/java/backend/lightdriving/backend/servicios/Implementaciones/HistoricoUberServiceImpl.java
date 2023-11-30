package backend.lightdriving.backend.servicios.Implementaciones;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.lightdriving.backend.modelos.HistoricoUber;
import backend.lightdriving.backend.repositorios.HistoricoUberRepository;
import backend.lightdriving.backend.servicios.HistoricoUberService;

@Service
public class HistoricoUberServiceImpl implements HistoricoUberService{

    @Autowired
    private HistoricoUberRepository historicoUberRepository;

    @Override
    public HistoricoUber obtenerHistoricoUber(int idHistoricoUber) {
        
        if(this.historicoUberRepository.existsById(idHistoricoUber)){
            return this.historicoUberRepository.findById(idHistoricoUber).get();
        }

        return null;
    }

    @Override
    public List<HistoricoUber> obtenerHistoricosConductor(int idConductor) {

        List<HistoricoUber> todos= this.historicoUberRepository.findAll();
        List<HistoricoUber> historicoConductor= new ArrayList<>();

        for (HistoricoUber historicoUber : todos) {
            if(historicoUber.getConductor().getIdConductor()==idConductor){
                historicoConductor.add(historicoUber);
            }
        }

        return historicoConductor;
    }


    
}
