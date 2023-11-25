package backend.lightdriving.backend.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.lightdriving.backend.dto.CoordenadaDto;
import backend.lightdriving.backend.dto.RespuestaDto;
import backend.lightdriving.backend.dto.VerificarRutaDto;
import backend.lightdriving.backend.modelos.ZonaRestringida;
import backend.lightdriving.backend.servicios.Implementaciones.ZonaRestringidaImpl;

@RestController
@RequestMapping("/api/zonaRestringida")

public class ZonaRestringidaController {

    @Autowired
    private ZonaRestringidaImpl zonaRestringidaImpl;

    @PostMapping("/crear")
    public boolean crearZonaRestringida(@RequestBody ZonaRestringida zonaRestringida){
        return this.zonaRestringidaImpl.crearZonaRestringida(zonaRestringida);
    }

    @DeleteMapping("/eliminar/{idZonaRestringida}")
    public boolean eliminarZonaRestringida(@PathVariable (name = "idZonaRestringida") int idZonaRestringida){
        return this.zonaRestringidaImpl.eliminarZonaRestringida(idZonaRestringida);
    }

    @PutMapping("/actualizar/{idZonaRestringida}")
    public boolean actualizarZonaRestringida(@PathVariable (name = "idZonaRestringida") int idZonaRestringida, @RequestBody ZonaRestringida zonaRestringida){
        return this.zonaRestringidaImpl.actualizarZonaRestringida(idZonaRestringida, zonaRestringida);
    }

    @GetMapping("/obtener/{idZonaRestringida}")
    public ZonaRestringida obtenerZonaRestringida(@PathVariable (name = "idZonaRestringida") int idZonaRestringida){
        return this.zonaRestringidaImpl.obtenerZonaRestringida(idZonaRestringida);
    }

    @GetMapping("/verificarRuta")
    public RespuestaDto verificarRuta(@RequestBody VerificarRutaDto ruta){
        return this.zonaRestringidaImpl.verificarRuta(ruta);
    }
    
    @GetMapping("/verificar")
    public boolean verificarPosicion(@RequestBody CoordenadaDto coordenada){
        return this.zonaRestringidaImpl.verificarPosicion(coordenada);
    }
}
