package backend.lightdriving.backend.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.lightdriving.backend.modelos.TipoUber;
import backend.lightdriving.backend.servicios.Implementaciones.TipoUberServiceImpl;

@RestController
@RequestMapping("/api/tipoUber")

public class TipoUberController {
    @Autowired
    TipoUberServiceImpl tipoUberServiceImpl;

    @GetMapping("/obtener/{idTipoUber}")
    public TipoUber obtenerTipoUber(@PathVariable (name = "idTipoUber") int idTipoUber){
        return this.tipoUberServiceImpl.obtenerTipoUber(idTipoUber);
    }

}
