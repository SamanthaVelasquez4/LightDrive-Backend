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

import backend.lightdriving.backend.modelos.MetodoPago;
import backend.lightdriving.backend.servicios.Implementaciones.MetodoPagoServiceImpl;

@RestController
@RequestMapping("/api/metodoPago")

public class MetodoPagoController {
    
    @Autowired
    private MetodoPagoServiceImpl metodoPagoServiceImpl;

    @PostMapping("/crear")
    public boolean crearMetodoPago(@RequestBody MetodoPago metodoPago){
        return this.metodoPagoServiceImpl.crearMetodoPago(metodoPago);
    }

    @DeleteMapping("/eliminar/{idMetodoPago}")
    public boolean eliminarMetodoPago(@PathVariable (name="idMetodoPago") int idMetodoPago){
        return this.metodoPagoServiceImpl.eliminarMetodoPago(idMetodoPago);
    }

    @PutMapping("/actualizar/{idMetodoPago}")
    public boolean actualizarMetodoPago(@PathVariable (name="idMetodoPago") int idMetodoPago, @RequestBody MetodoPago metodoPago){
        return this.metodoPagoServiceImpl.actualizarMetodoPago(idMetodoPago, metodoPago);
    }

    @GetMapping("/obtener/{idMetodoPago}")
    public MetodoPago obtenerMetodoPago(@PathVariable (name = "idMetodoPago") int idMetodoPago){
        return this.metodoPagoServiceImpl.obtenerMetodoPago(idMetodoPago);
    }

    @GetMapping("/obtenerTodo")
    public List<MetodoPago> obtenerMetodos(){
        return this.metodoPagoServiceImpl.obtenerMetodos();
    }
}
