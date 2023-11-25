package backend.lightdriving.backend.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.lightdriving.backend.dto.ActualizarConductorDto;
import backend.lightdriving.backend.dto.ConductorDto;
import backend.lightdriving.backend.dto.LoginDto;
import backend.lightdriving.backend.modelos.Carrera;
import backend.lightdriving.backend.modelos.Conductor;
import backend.lightdriving.backend.servicios.Implementaciones.ConductorServiceImpl;

@RestController
@RequestMapping("/api/conductor")

public class ConductorController {
    
    @Autowired
    private ConductorServiceImpl conductorServiceImpl;

    @PostMapping("/crear")
    public boolean crearConductor(@RequestBody ConductorDto conductor){
        return this.conductorServiceImpl.crearConductor(conductor);
    } 

    @GetMapping("/login")
    public Conductor login(@RequestBody LoginDto login){
        return this.conductorServiceImpl.login(login);
    }

    @GetMapping("/obtener/{idConductor}")
    public Conductor obtenerConductor(@PathVariable (name = "idConductor") int idConductor){
        return this.conductorServiceImpl.obtenerConductor(idConductor);
    }

    @DeleteMapping("/eliminar/{idConductor}")
    public boolean eliminarConductor(@PathVariable (name = "idConductor") int idConductor){
        return this.conductorServiceImpl.eliminarConductor(idConductor);
    }

    @PutMapping("/actualizar/{idConductor}")
    public boolean actualizarConductor(@PathVariable (name = "idConductor") int idConductor, @RequestBody ActualizarConductorDto actualizar){
        return this.conductorServiceImpl.actualizarConductor(idConductor, actualizar);
    }

    @GetMapping("/obtenerCarreras/{idConductor}")
    public List<Carrera> obtenerCarreras(@PathVariable (name = "idConductor") int idConductor){
        return this.conductorServiceImpl.obtenerCarreras(idConductor);
    }
}
