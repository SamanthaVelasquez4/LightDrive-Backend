package backend.lightdriving.backend.servicios.Implementaciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.lightdriving.backend.dto.CoordenadaDto;
import backend.lightdriving.backend.dto.LoginDto;
import backend.lightdriving.backend.modelos.Cliente;
import backend.lightdriving.backend.repositorios.ClienteRepository;
import backend.lightdriving.backend.servicios.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public boolean crearCliente(Cliente cliente) {
        
        if(cliente != null){
            clienteRepository.save(cliente);
            return true;
        }

        return false;
    }
    
    @Override
    public int login(LoginDto login) {

        if(login != null){
            
            List<Cliente> clientes= this.clienteRepository.findAll();

            for (Cliente cliente : clientes) {
                if(cliente.getContrasena().equals(login.getContrasena()) && cliente.getCorreo().equals(login.getCorreo())){
                    return cliente.getIdCliente();
                }
            }
            
        }

        return -1;
        
    }



    @Override
    public boolean eliminarCliente(int idCliente) {

        if(this.clienteRepository.existsById(idCliente)){
            Cliente Cliente = clienteRepository.findById(idCliente).get();
            clienteRepository.delete(Cliente);
            return true;
        }
        
        return false;
    }

    @Override
    public boolean actualizarCliente(int idCliente, Cliente cliente) {

        if(this.clienteRepository.existsById(idCliente)){
            Cliente Cliente1 = clienteRepository.findById(idCliente).get();
            Cliente1.setNombre(cliente.getNombre());
            Cliente1.setApellido(cliente.getApellido());
            Cliente1.setCorreo(cliente.getCorreo());
            Cliente1.setTelefono(cliente.getTelefono());
            Cliente1.setFechaNacimiento(cliente.getFechaNacimiento());
            Cliente1.setUbicacionNombre(cliente.getUbicacionNombre());
            Cliente1.setContrasena(cliente.getContrasena());
            Cliente1.setLat(cliente.getLat());
            Cliente1.setLng(cliente.getLng());
            clienteRepository.save(Cliente1);
            return true;
        }

        return false;
    }

    @Override
    public Cliente obtenerCliente(int idCliente) {

        if(this.clienteRepository.existsById(idCliente)){
            Cliente cliente= clienteRepository.findById(idCliente).get();
            cliente.setContrasena(null);
           return cliente;
        }

        return null;
    }

    @Override
    public CoordenadaDto obtenerUbicacion(int idCliente) {
        CoordenadaDto ubicacion = new CoordenadaDto();

        if(this.clienteRepository.existsById(idCliente)){
            Cliente cliente= this.clienteRepository.findById(idCliente).get();
            ubicacion.setLat(cliente.getLat());
            ubicacion.setLng(cliente.getLng());
            ubicacion.setUbicacionNombre(cliente.getUbicacionNombre());
        }

        return ubicacion;
    }
    
}
