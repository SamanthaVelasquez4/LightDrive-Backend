package backend.lightdriving.backend.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.lightdriving.backend.dto.CarreraDto;
import backend.lightdriving.backend.modelos.Carrera;
import backend.lightdriving.backend.servicios.Implementaciones.CarreraServiceImpl;

@RestController
@RequestMapping("/api/carreras")

public class CarreraController {
    @Autowired
    CarreraServiceImpl CarreraServiceImpl;
 
    @PostMapping("/crear")
    public Carrera crearCarrera(@RequestBody CarreraDto Carrera){
        return this.CarreraServiceImpl.nvaCarrera(Carrera);
    }

    @GetMapping("/eliminar/{idCarrera}")
    public boolean eliminarCarrera(@PathVariable (name="idCarrera") int idCarrera){
        return this.CarreraServiceImpl.eliminarCarrera(idCarrera);
    }

    @GetMapping("/obtener/{idCarrera}")
    public Carrera obtenerCarrera(@PathVariable (name = "idCarrera") int idCarrera){
        return this.CarreraServiceImpl.obtenerCarrera(idCarrera);
    }

    @GetMapping("/cambiarEstado/{idCarrera}")
    public boolean cambiarEstadoCarrera(@PathVariable (name="idCarrera") int idCarrera){
        return this.CarreraServiceImpl.cambiarEstadoCarrera(idCarrera);
    }
}
