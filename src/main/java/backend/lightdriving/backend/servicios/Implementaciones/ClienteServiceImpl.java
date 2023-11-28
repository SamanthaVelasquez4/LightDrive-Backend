package backend.lightdriving.backend.servicios.Implementaciones;


import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.lightdriving.backend.dto.CoordenadaDto;
import backend.lightdriving.backend.dto.FacturaDto;
import backend.lightdriving.backend.dto.FacturasClienteDto;
import backend.lightdriving.backend.dto.LoginDto;
import backend.lightdriving.backend.modelos.Carrera;
import backend.lightdriving.backend.modelos.Cliente;
import backend.lightdriving.backend.modelos.Factura;
import backend.lightdriving.backend.repositorios.ClienteRepository;
import backend.lightdriving.backend.repositorios.FacturaRepository;
import backend.lightdriving.backend.servicios.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired 
    private FacturaRepository facturaRepository;

    @Override
    public boolean crearCliente(Cliente cliente) {
        
        if(cliente != null){
            clienteRepository.save(cliente);
            return true;
        }

        return false;
    }
    
    @Override
    public FacturasClienteDto login(LoginDto login) {

        FacturasClienteDto respuesta = new FacturasClienteDto();

        if(login != null){
            
            List<Cliente> clientes= this.clienteRepository.findAll();

            for (Cliente cliente : clientes) {
                if(cliente.getContrasena().equals(login.getContrasena()) && cliente.getCorreo().equals(login.getCorreo())){
                    List<Factura> facturas= this.facturaRepository.findAll();
                    
                    respuesta.setId(cliente.getIdCliente());
                    respuesta.setApellido(cliente.getApellido());
                    respuesta.setNombre(cliente.getNombre());
                    for (Factura factura : facturas) {
                        if(factura.getCarrera().getCliente().getIdCliente()== cliente.getIdCliente()){
                            FacturaDto facturaDto= new FacturaDto();
                            facturaDto.setCarrera(factura.getCarrera().getIdCarrera());
                            SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd");
                            facturaDto.setFecha(dt.format(factura.getFecha()));
                            facturaDto.setMetodoPago(factura.getMetodoPago().getDescripcion());
                            facturaDto.setTotal(factura.getTotal());
                            facturaDto.setIdFactura(factura.getIdFactura());
                            if(factura.getCarrera().getEstado()==0){
                                facturaDto.setEstadoCarrera("En progreso");
                            }else{
                                facturaDto.setEstadoCarrera("Finalizado");
                            }
                            respuesta.getFacturas().add(facturaDto);
                        }
                    }
                    return respuesta;
                }
            }
            
        }

        return respuesta;
        
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
    public List<Carrera> obtenerCarreras(int idCliente) {
        if(this.clienteRepository.existsById(idCliente)){

           return clienteRepository.findById(idCliente).get().getCarreras();
            
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
