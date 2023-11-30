package backend.lightdriving.backend.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.lightdriving.backend.modelos.HistoricoUber;
import backend.lightdriving.backend.servicios.Implementaciones.HistoricoUberServiceImpl;

@RestController
@RequestMapping("/api/historicoUber")

public class HistoricoUberController {

    @Autowired
    private HistoricoUberServiceImpl historicoUberServiceImpl;
    
    @GetMapping("/obtener/{idHistoricoUber}")
    public HistoricoUber obtenerHistoricoUber(@PathVariable (name="idHistoricoUber") int idHistoricoUber){
        return this.historicoUberServiceImpl.obtenerHistoricoUber(idHistoricoUber);
    }

    @GetMapping("/obtenerHistoricoConductor/{idConductor}")
    public List<HistoricoUber> obtenerHistoricosConductor(@PathVariable(name = "idConductor") int idConductor){
        return this.historicoUberServiceImpl.obtenerHistoricosConductor(idConductor);
    }
}
