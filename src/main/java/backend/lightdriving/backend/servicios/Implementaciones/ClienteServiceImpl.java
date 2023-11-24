package backend.lightdriving.backend.servicios.Implementaciones;


import org.springframework.beans.factory.annotation.Autowired;

import backend.lightdriving.backend.modelos.Cliente;
import backend.lightdriving.backend.repositorios.ClienteRepository;
import backend.lightdriving.backend.servicios.ClienteService;

public class ClienteServiceImpl implements ClienteService{

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public Cliente logIn(String correo, String contrasna) {
        Cliente cliente = clienteRepository.findByCorreo(correo);
        if(cliente != null){
            String contrasena = cliente.getContrasena();
            if(contrasena.equals(contrasna)){
            return cliente;
            }
        }
        return null;
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public boolean eliminarCliente(int idCliente) {
        Cliente Cliente = clienteRepository.findById(idCliente).get();
        if(Cliente != null) {
            clienteRepository.delete(Cliente);
            return true;
        }
        return false;
    }

    @Override
    public boolean actualizarCliente(int idCliente, Cliente cliente) {
        Cliente Cliente1 = clienteRepository.findById(idCliente).get();
        if(Cliente1 != null) {
            Cliente1.setNombre(cliente.getNombre());
            Cliente1.setApellido(cliente.getApellido());
            Cliente1.setCorreo(cliente.getCorreo());
            Cliente1.setTelefono(cliente.getTelefono());
            Cliente1.setFechaNacimiento(cliente.getFechaNacimiento());
            clienteRepository.save(Cliente1);
            return true;
        }
        return false;
    }

    @Override
    public Cliente obtenerCliente(int idCliente) {
        return clienteRepository.findById(idCliente).get();
    }
    
}
