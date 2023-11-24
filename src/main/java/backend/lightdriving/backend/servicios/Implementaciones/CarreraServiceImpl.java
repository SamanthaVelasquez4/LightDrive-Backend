package backend.lightdriving.backend.servicios.Implementaciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import backend.lightdriving.backend.Dto.CarreraDto;
import backend.lightdriving.backend.modelos.Carrera;
import backend.lightdriving.backend.modelos.Cliente;
import backend.lightdriving.backend.modelos.Conductor;
import backend.lightdriving.backend.repositorios.CarreraRepository;
import backend.lightdriving.backend.repositorios.ClienteRepository;
import backend.lightdriving.backend.repositorios.ConductorRepository;
import backend.lightdriving.backend.repositorios.FacturaRepository;
import backend.lightdriving.backend.servicios.CarreraService;

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
            nvCarrera.setLatInicio(carreraDto.getLatIncio());
            nvCarrera.setLatFinal(carreraDto.getLatFinal());
            nvCarrera.setCliente(cliente);
            nvCarrera.setConductor(Conductor);
            nvCarrera.setLngInicio(carreraDto.getLngInicio());
            nvCarrera.setLngFinal(carreraDto.getLngFinal());
            nvCarrera.setEstado(0);
            //Factura factura = new Factura();
            return carreraRepository.save(nvCarrera);

        }
        return null;
        
    }

    @Override
    public Carrera obtenCarrera(int idCarrera) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenCarrera'");
    }

    @Override
    public boolean eliminarCarrera(Carrera carrera) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarCarrera'");
    }

    @Override
    public boolean actualizarCarrera(int idCarrera, Carrera carrera) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarCarrera'");
    }

    @Override
    public boolean cambiarEstadoCarrera(int estado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cambiarEstadoCarrera'");
    }

    @Override
    public List<Cliente> obtenerCarrerasPorCliente(int idCliente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerCarrerasPorCliente'");
    }

    @Override
    public List<Conductor> obtenerCarrerasPorConductor(int idConductor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerCarrerasPorConductor'");
    }
    
}
