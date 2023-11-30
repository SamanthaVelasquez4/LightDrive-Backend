package backend.lightdriving.backend.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.lightdriving.backend.modelos.MetodoPago;
import backend.lightdriving.backend.servicios.Implementaciones.MetodoPagoServiceImpl;

@RestController
@RequestMapping("/api/metodoPago")

public class MetodoPagoController {
    
    @Autowired
    private MetodoPagoServiceImpl metodoPagoServiceImpl;

    @GetMapping("/obtenerTodo")
    public List<MetodoPago> obtenerMetodos(){
        return this.metodoPagoServiceImpl.obtenerMetodos();
    }
}
