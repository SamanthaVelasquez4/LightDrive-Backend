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

import backend.lightdriving.backend.dto.CarreraClienteDto;
import backend.lightdriving.backend.dto.CarreraConductorDto;
import backend.lightdriving.backend.dto.CarreraDto;
import backend.lightdriving.backend.servicios.Implementaciones.CarreraServiceImpl;

@RestController
@RequestMapping("/api/carreras")

public class CarreraController {
    @Autowired
    CarreraServiceImpl CarreraServiceImpl;
 
    @PostMapping("/crear")
    public boolean crearCarrera(@RequestBody CarreraDto Carrera){
        return this.CarreraServiceImpl.nvaCarrera(Carrera);
    }

    @DeleteMapping("/eliminar/{idCarrera}")
    public boolean eliminarCarrera(@PathVariable (name="idCarrera") int idCarrera){
        return this.CarreraServiceImpl.eliminarCarrera(idCarrera);
    }

    @PutMapping("/cambiarEstado/{idCarrera}")
    public boolean cambiarEstadoCarrera(@PathVariable (name="idCarrera") int idCarrera){
        return this.CarreraServiceImpl.cambiarEstadoCarrera(idCarrera);
    }

    @GetMapping("/obtenerDetalleCliente/{idCarrera}")
    public CarreraClienteDto obtenerDetalleCarreraCliente(@PathVariable (name="idCarrera") int idCarrera){
        return this.CarreraServiceImpl.obtenerDetalleCarreraCliente(idCarrera);
    }

    @GetMapping("/obtenerDetalleConductor/{idCarrera}")
    public CarreraConductorDto obtenerDetalleCarreraConductor(@PathVariable (name="idCarrera") int idCarrera){
        return this.CarreraServiceImpl.obtenerDetalleCarreraConductor(idCarrera);
    }
}
