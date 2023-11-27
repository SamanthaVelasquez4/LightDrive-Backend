package backend.lightdriving.backend.servicios.Implementaciones;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.lightdriving.backend.dto.CarreraDto;
import backend.lightdriving.backend.modelos.Carrera;
import backend.lightdriving.backend.modelos.Cliente;
import backend.lightdriving.backend.modelos.Conductor;
import backend.lightdriving.backend.modelos.Factura;
import backend.lightdriving.backend.modelos.MetodoPago;
import backend.lightdriving.backend.modelos.TipoUber;
import backend.lightdriving.backend.repositorios.CarreraRepository;
import backend.lightdriving.backend.repositorios.ClienteRepository;
import backend.lightdriving.backend.repositorios.ConductorRepository;
import backend.lightdriving.backend.repositorios.MetodoPagoRepository;
import backend.lightdriving.backend.servicios.CarreraService;

@Service
public class CarreraServiceImpl implements CarreraService{

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ConductorRepository conductorRepository;

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Override
    public boolean nvaCarrera(CarreraDto carreraDto) {

        if(this.clienteRepository.existsById(carreraDto.getIdCliente()) && this.conductorRepository.existsById(carreraDto.getIdConductor())){

            Cliente cliente = clienteRepository.findById(carreraDto.getIdCliente()).get();
            Conductor conductor = conductorRepository.findById(carreraDto.getIdConductor()).get();

            //cambiar estado conductor
            conductor.setDisponible(false);
            this.conductorRepository.save(conductor);
            
            Carrera nvCarrera = new Carrera();
            nvCarrera.setLatInicio(carreraDto.getLatInicio());
            nvCarrera.setLatFinal(carreraDto.getLatInicio());
            nvCarrera.setCliente(cliente);
            nvCarrera.setConductor(conductor);
            nvCarrera.setLngInicio(carreraDto.getLngInicio());
            nvCarrera.setLngFinal(carreraDto.getLngFinal());
            nvCarrera.setEstado(0);
            //Se crea una factura
            Factura factura = new Factura();
            factura.setCarrera(nvCarrera);
            Date date = new Date(System.currentTimeMillis());
            
            factura.setFecha(date);
            
            MetodoPago metodoPago= this.metodoPagoRepository.findById(carreraDto.getMetodoPago()).get();
            factura.setMetodoPago(metodoPago);
            
            
            //calcular total
            double total=0;
            TipoUber tipoUber= conductor.getUber().getTipoUber();
            double distanciaRecorrida = this.calcularDistancia(carreraDto.getLatInicio(), carreraDto.getLngInicio(), carreraDto.getLatFinal(), carreraDto.getLngFinal());

            if(distanciaRecorrida>3){
                total = tipoUber.getPrecioBase() + (distanciaRecorrida-3)*tipoUber.getPrecioXkm();
            }else{
                total = tipoUber.getPrecioBase();
            }

            factura.setTotal(Math.ceil(total));
            nvCarrera.setFactura(factura);

            carreraRepository.save(nvCarrera);
            return true;

        }

        return false;
        
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
            Carrera carrera2= this.carreraRepository.findById(idCarrera).get();
            carrera2.setEstado(1);
            carreraRepository.save(carrera2);

            //cambiar estado del conductor
            Conductor conductor= carrera2.getConductor();
            conductor.setDisponible(true);
            conductor.getUber().setLat(carrera2.getLatFinal());
            conductor.getUber().setLng(carrera2.getLngFinal());

            this.conductorRepository.save(conductor);

            return true;
        }
        return false;
    }

    private double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        // Convierte grados a radianes
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        // Diferencia de latitud y longitud
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        // Fórmula de Haversine
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                   Math.cos(lat1) * Math.cos(lat2) *
                   Math.pow(Math.sin(dLon / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calcula la distancia; radio tierra=6371
        double distancia = 6371 * c;
        System.out.println(distancia);
        return distancia;
    }

    @Override
    public List<Carrera> obtenerTodo() {
        return this.carreraRepository.findAll();
    }
    
}
