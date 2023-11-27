package backend.lightdriving.backend.servicios.Implementaciones;


import java.util.Date;

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
import backend.lightdriving.backend.repositorios.FacturaRepository;
import backend.lightdriving.backend.repositorios.MetodoPagoRepository;
import backend.lightdriving.backend.repositorios.TipoUberRepository;
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

    @Autowired
    MetodoPagoRepository metodoPagoRepository;

    @Autowired
    TipoUberRepository tipoUberRepository;

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

            //calcula el total a pagar
            TipoUber tipoUber = tipoUberRepository.findById(carreraDto.getIdTipoUber()).get();
            double precioBase = tipoUber.getPrecioBase();
            double precioXkm = tipoUber.getPrecioXkm();
            double distancia = this.calcularDistancia(carreraDto.getLatInicio(), carreraDto.getLngInicio(), carreraDto.getLatFinal(), carreraDto.getLngFinal());
            double total;
            if(distancia>5){
                total = precioBase + ((distancia-5)*precioXkm);
            } else{
                total = precioBase;
            }
            
            
            //Se crea una factura
            Factura factura = new Factura();
            Date date = new Date(System.currentTimeMillis());
            MetodoPago metodoPago = metodoPagoRepository.findById(carreraDto.getMetodoPago()).get();
            factura.setFecha(date);
            factura.setMetodoPago(metodoPago);
            factura.setTotal(total);
            factura.setCarrera(nvCarrera);
            carreraRepository.save(nvCarrera);
            facturaRepository.save(factura);
            nvCarrera.setFactura(factura);


            return nvCarrera;

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
    
}
