package backend.lightdriving.backend.controladores;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.lightdriving.backend.modelos.TipoUber;
import backend.lightdriving.backend.servicios.Implementaciones.TipoUberServiceImpl;

@RestController
@RequestMapping("/api/tipoUber")

public class TipoUberController {
    @Autowired
    TipoUberServiceImpl tipoUberServiceImpl;
 
    @PostMapping("/crear")
    public boolean crearTipoUber(@RequestBody TipoUber TipoUber){
        return this.tipoUberServiceImpl.crearTipoUber(TipoUber);
    }

    @GetMapping("/eliminar/{idTipoUber}")
    public boolean eliminarTipoUber(@PathVariable (name="idTipoUber") int idTipoUber){
        return this.tipoUberServiceImpl.eliminarTipoUber(idTipoUber);
    }

    @GetMapping("/actualizar/{idTipoUber}")
    public boolean actualizarTipoUber(@PathVariable (name="idTipoUber") int idTipoUber, @RequestBody TipoUber TipoUber){
        return this.tipoUberServiceImpl.actualizarTipoUber(idTipoUber, TipoUber);
    }

    @GetMapping("/obtener/{idTipoUber}")
    public TipoUber obtenerTipoUber(@PathVariable (name = "idTipoUber") int idTipoUber){
        return this.tipoUberServiceImpl.obtenerTipoUber(idTipoUber);
    }

    @GetMapping("/obtener/todos")
    public List<TipoUber> obtenerTodos(){
        return this.tipoUberServiceImpl.obtenerTodos();
    }
}
