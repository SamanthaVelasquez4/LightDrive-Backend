package backend.lightdriving.backend.servicios.Implementaciones;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.lightdriving.backend.dto.CarreraDto;
import backend.lightdriving.backend.modelos.Carrera;
import backend.lightdriving.backend.modelos.Cliente;
import backend.lightdriving.backend.modelos.Conductor;
import backend.lightdriving.backend.modelos.Factura;
import backend.lightdriving.backend.repositorios.CarreraRepository;
import backend.lightdriving.backend.repositorios.ClienteRepository;
import backend.lightdriving.backend.repositorios.ConductorRepository;
import backend.lightdriving.backend.repositorios.FacturaRepository;
import backend.lightdriving.backend.servicios.CarreraService;

@Service
public class CarreraServiceImpl implements CarreraService{

    @Autowired
    CarreraRepository carreraRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ConductorRepository ConductorRepository;

    @Autowired
    FacturaRepository facturaRepository;

    @Override
    public Carrera nvaCarrera(CarreraDto carreraDto) {

        Cliente cliente = clienteRepository.findById(carreraDto.getIdCliente()).get();
        Conductor Conductor = ConductorRepository.findById(carreraDto.getIdConductor()).get();
        
        if(Conductor != null && cliente != null){
            Carrera nvCarrera = new Carrera();
            nvCarrera.setLatInicio(carreraDto.getLatInicio());
            nvCarrera.setLatFinal(carreraDto.getLatInicio());
            nvCarrera.setCliente(cliente);
            nvCarrera.setConductor(Conductor);
            nvCarrera.setLngInicio(carreraDto.getLngInicio());
            nvCarrera.setLngFinal(carreraDto.getLngFinal());
            nvCarrera.setEstado(0);
            //Se crea una factura
            Factura factura = new Factura();
            Date date = new Date(System.currentTimeMillis());
            
            factura.setFecha(date);
            factura.setMetodoPago

            return carreraRepository.save(nvCarrera);

        }
        return null;
        
    }

    @Override
    public Carrera obtenerCarrera(int idCarrera) {
        if(this.carreraRepository.existsById(idCarrera)){
            return this.carreraRepository.findById(idCarrera).get();
        }

        return null;
    }

    @Override
    public boolean eliminarCarrera(int idCarrera) {
        if(this.carreraRepository.existsById(idCarrera)){
            this.carreraRepository.deleteById(idCarrera);
            return true;
        }

        return false;
    }

    @Override
    public boolean actualizarCarrera(int idCarrera, Carrera carrera) {
        if(this.carreraRepository.existsById(idCarrera)){
            Carrera Carrera2= this.carreraRepository.findById(idCarrera).get();
            Carrera2.setCliente(null);
            return true;
        }

        return false;
    }
    
    @Override
    public boolean cambiarEstadoCarrera(int idCarrera) {
        if(this.carreraRepository.existsById(idCarrera)){
            Carrera Carrera2= this.carreraRepository.findById(idCarrera).get();
            Carrera2.setEstado(1);
            carreraRepository.save(Carrera2);
            return true;
        }
        return false;
    }
    
}
