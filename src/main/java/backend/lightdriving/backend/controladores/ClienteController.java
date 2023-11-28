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

import backend.lightdriving.backend.dto.CoordenadaDto;
import backend.lightdriving.backend.dto.FacturasClienteDto;
import backend.lightdriving.backend.dto.LoginDto;
import backend.lightdriving.backend.modelos.Carrera;
import backend.lightdriving.backend.modelos.Cliente;
import backend.lightdriving.backend.servicios.Implementaciones.ClienteServiceImpl;

@RestController
@RequestMapping("/api/cliente")

public class ClienteController {
    
    @Autowired
    private ClienteServiceImpl clienteServiceImpl;

    @PostMapping("/crear")
    public boolean crearCliente(@RequestBody Cliente cliente){
        return this.clienteServiceImpl.crearCliente(cliente);
    }

    @GetMapping("/login")
    public FacturasClienteDto login(@RequestBody LoginDto login){
        return this.clienteServiceImpl.login(login);
    }

    @DeleteMapping("/eliminar/{idCliente}")
    public boolean eliminarCliente(@PathVariable (name = "idCliente" ) int idCliente){
        return this.clienteServiceImpl.eliminarCliente(idCliente);
    }

    @PutMapping("/actualizar/{idCliente}")
    public boolean actualizarCliente(@PathVariable (name = "idCliente" ) int idCliente, @RequestBody Cliente cliente){
        return this.clienteServiceImpl.actualizarCliente(idCliente, cliente);
    }

    @GetMapping("/obtener/{idCliente}")
    public Cliente obtenerCliente(@PathVariable (name = "idCliente" ) int idCliente){
        return this.clienteServiceImpl.obtenerCliente(idCliente);
    }

    @GetMapping("/obtenerCarreras/{idCliente}")
    public List<Carrera> obtenerCarreras(@PathVariable (name = "idCliente" ) int idCliente){
        return this.clienteServiceImpl.obtenerCarreras(idCliente);
    }

    @GetMapping("/obtenerUbicacion/{idCliente}")
    public CoordenadaDto obtenerUbicacion(int idCliente){
        return this.obtenerUbicacion(idCliente);
    }
}
