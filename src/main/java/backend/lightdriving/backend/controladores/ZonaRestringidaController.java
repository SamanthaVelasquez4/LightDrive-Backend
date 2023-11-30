package backend.lightdriving.backend.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.lightdriving.backend.dto.CoordenadaDto;
import backend.lightdriving.backend.servicios.Implementaciones.ZonaRestringidaImpl;

@RestController
@RequestMapping("/api/zonaRestringida")

public class ZonaRestringidaController {

    @Autowired
    private ZonaRestringidaImpl zonaRestringidaImpl;
    
    @GetMapping("/verificar")
    public boolean verificarPosicion(@RequestBody CoordenadaDto coordenada){
        return this.zonaRestringidaImpl.verificarPosicion(coordenada);
    }
}
