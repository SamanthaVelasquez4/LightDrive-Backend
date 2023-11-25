package backend.lightdriving.backend.servicios.Implementaciones;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.lightdriving.backend.dto.ActualizarUberDto;
import backend.lightdriving.backend.dto.UberDto;
import backend.lightdriving.backend.modelos.Conductor;
import backend.lightdriving.backend.modelos.HistoricoUber;
import backend.lightdriving.backend.modelos.TipoUber;
import backend.lightdriving.backend.modelos.Uber;
import backend.lightdriving.backend.repositorios.ConductorRepository;
import backend.lightdriving.backend.repositorios.HistoricoUberRepository;
import backend.lightdriving.backend.repositorios.TipoUberRepository;
import backend.lightdriving.backend.repositorios.UberRepository;
import backend.lightdriving.backend.servicios.UberService;

@Service
public class UberServiceImpl implements UberService{

    @Autowired
    private UberRepository uberRepository;

    @Autowired 
    private HistoricoUberRepository historicoUberRepository;

    @Autowired
    private TipoUberRepository tipoUberRepository;

    @Autowired
    private ConductorRepository conductorRepository;

    @Override
    public boolean actualizarUber(int idUber, ActualizarUberDto uber) {

        if(this.uberRepository.existsById(idUber) && this.tipoUberRepository.existsById(uber.getTipoUber())){

            TipoUber tipoUberEncontrado= this.tipoUberRepository.findById(uber.getTipoUber()).get();
            Uber uberActual= this.uberRepository.findById(idUber).get();

            
            uberActual.setColor(uber.getColor());
            uberActual.setTipoUber(tipoUberEncontrado);

            //Cambiar color en el historico
            List<HistoricoUber> historicos= this.historicoUberRepository.findAll();

            for (HistoricoUber historicoUber : historicos) {
                if(historicoUber.getPlaca().equals(uberActual.getPlaca())){
                    historicoUber.setColor(uber.getColor());
                    this.historicoUberRepository.save(historicoUber);
                }
            }

            this.uberRepository.save(uberActual);
            return true;

        }
        
        return false;
    }

    @Override
    public Uber obtenerUber(int idUber) {
        if(this.uberRepository.existsById(idUber)){
            return this.uberRepository.findById(idUber).get();
        }

        return null;
    }

    @Override
    public boolean cambiarCarro(int idUber, UberDto uber) {
        if(this.uberRepository.existsById(idUber) && this.tipoUberRepository.existsById(uber.getTipoUber())){

            TipoUber tipoUberEncontrado= this.tipoUberRepository.findById(uber.getTipoUber()).get();
            Uber uberActual= this.uberRepository.findById(idUber).get();
            Conductor conductor= this.conductorRepository.findById(uberActual.getConductor().getIdConductor()).get();

            //poner fecha final en el historico anterior
            List<HistoricoUber> historicos= this.historicoUberRepository.findAll();
            Date fechaActual= new Date();

            for (HistoricoUber historicoUber : historicos) {
                if(historicoUber.getPlaca().equals(uberActual.getPlaca())){
                    historicoUber.setFechaFinal(fechaActual);
                    this.historicoUberRepository.save(historicoUber);
                }
            }

            //Crear Nuevo uber
            Uber nuevUber= new Uber();

            nuevUber.setAnio(uber.getAnio());
            nuevUber.setColor(uber.getColor());
            nuevUber.setConductor(uberActual.getConductor());
            nuevUber.setLat(uber.getLat());
            nuevUber.setLng(uber.getLng());
            nuevUber.setMarca(uber.getMarca());
            nuevUber.setPlaca(uber.getPlaca());
            tipoUberEncontrado.setIdTipoUber(uber.getTipoUber());
            nuevUber.setTipoUber(tipoUberEncontrado);

            conductor.setUber(nuevUber);
            this.conductorRepository.save(conductor);
            this.uberRepository.delete(uberActual);

            //Crear Historico
            HistoricoUber nuevoHistorico= new HistoricoUber();
            nuevoHistorico.setAnio(uber.getAnio());
            nuevoHistorico.setColor(uber.getColor());
            nuevoHistorico.setConductor(uberActual.getConductor());
            nuevoHistorico.setFechaInicio(fechaActual);
            nuevoHistorico.setMarca(uber.getMarca());
            nuevoHistorico.setPlaca(uber.getPlaca());

            uberActual.setPlaca(uber.getPlaca());

            this.historicoUberRepository.save(nuevoHistorico);
            return true;

        }
        
        return false;
    }
    
}
