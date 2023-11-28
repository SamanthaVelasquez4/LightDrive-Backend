package backend.lightdriving.backend.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.lightdriving.backend.dto.ActualizarUberDto;
import backend.lightdriving.backend.dto.RespuestaDto;
import backend.lightdriving.backend.dto.UberDto;
import backend.lightdriving.backend.dto.VerificarRutaDto;
import backend.lightdriving.backend.modelos.Uber;
import backend.lightdriving.backend.servicios.Implementaciones.UberServiceImpl;

@RestController
@RequestMapping("/api/uber")

public class UberController {
    
    @Autowired
    private UberServiceImpl uberServiceImpl;

    @PutMapping("/actualizar/{idUber}")
    public boolean actualizarUber(@PathVariable (name = "idUber") int idUber, @RequestBody ActualizarUberDto uber){
        return this.uberServiceImpl.actualizarUber(idUber, uber);
    }

    @GetMapping("/obtener/{idUber}")
    public Uber obtenerUber(@PathVariable (name = "idUber") int idUber){
        return this.uberServiceImpl.obtenerUber(idUber);
    }

    @PutMapping("/cambiarCarro/{idUber}")
    public boolean cambiarCarro(@PathVariable (name = "idUber") int idUber, @RequestBody UberDto uber){
        return this.uberServiceImpl.cambiarCarro(idUber, uber);
    }

    
    @GetMapping("/ubersCercanos")
    public RespuestaDto obtenerUbersCliente(@RequestBody VerificarRutaDto ruta){
        return this.uberServiceImpl.obtenerUbersCliente(ruta);
    }

}
