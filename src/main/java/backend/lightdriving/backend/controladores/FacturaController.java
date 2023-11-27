package backend.lightdriving.backend.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.lightdriving.backend.dto.FacturasClienteDto;
import backend.lightdriving.backend.modelos.Factura;
import backend.lightdriving.backend.servicios.Implementaciones.FacturaServiceImpl;

@RestController
@RequestMapping("/api/factura")

public class FacturaController {

    @Autowired
    private FacturaServiceImpl facturaServiceImpl;

    @GetMapping("/obtenerFacturas/cliente/{idCliente}")
    public FacturasClienteDto obtenerFacturasCliente(@PathVariable (name = "idCliente") int idCliente){
        return this.facturaServiceImpl.obtenerFacturasCliente(idCliente);
    }

    @PutMapping("/obtener/{idFactura}")
    public Factura obtenerFactura(@PathVariable (name = "idFactura") int idFactura){
        return this.facturaServiceImpl.obtenerFactura(idFactura);
    }

    @GetMapping("/obtenerTodo")
    public List<Factura> obtenerTodo(){
        return this.facturaServiceImpl.obtenerTodo();
    }
    
}
