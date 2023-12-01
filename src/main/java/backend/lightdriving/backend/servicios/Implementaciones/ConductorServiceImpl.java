package backend.lightdriving.backend.servicios.Implementaciones;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.lightdriving.backend.dto.ActualizarConductorDto;
import backend.lightdriving.backend.dto.CarreraEnProgresoDto;
import backend.lightdriving.backend.dto.ClienteConductorDto;
import backend.lightdriving.backend.dto.ConductorDto;
import backend.lightdriving.backend.dto.ConductorLoginDto;
import backend.lightdriving.backend.dto.FacturaDto;
import backend.lightdriving.backend.dto.LoginDto;
import backend.lightdriving.backend.modelos.Carrera;
import backend.lightdriving.backend.modelos.Cliente;
import backend.lightdriving.backend.modelos.Conductor;
import backend.lightdriving.backend.modelos.HistoricoUber;
import backend.lightdriving.backend.modelos.TipoUber;
import backend.lightdriving.backend.modelos.Uber;
import backend.lightdriving.backend.repositorios.ConductorRepository;
import backend.lightdriving.backend.repositorios.HistoricoUberRepository;
import backend.lightdriving.backend.repositorios.TipoUberRepository;
import backend.lightdriving.backend.servicios.ConductorService;
@Service
public class ConductorServiceImpl implements ConductorService{

    @Autowired
    private ConductorRepository conductorRepository;

    @Autowired
    private TipoUberRepository tipoUberRepository;

    @Autowired
    private HistoricoUberRepository historicoUberRepository;

    @Override
    public boolean crearConductor(ConductorDto conductor) {

        if(conductor != null){

            //Verificar que el tipo uber exista antes de setear datos
            if(this.tipoUberRepository.existsById(conductor.getUber().getTipoUber())){
                Conductor nuevConductor = new Conductor();
                Uber nuevUber= new Uber();
                Date fechaActual = new Date();
                TipoUber tipoUberEncontrado = new TipoUber();
                HistoricoUber historicoUber= new HistoricoUber();

                //llenar info del conductor
                nuevConductor.setApellido(conductor.getApellido());
                nuevConductor.setContrasena(conductor.getContrasena());
                nuevConductor.setCorreo(conductor.getCorreo());
                nuevConductor.setDisponible(true);
                nuevConductor.setFechaContratacion(fechaActual);
                nuevConductor.setFechaNacimiento(conductor.getFechaNacimiento());
                nuevConductor.setNombre(conductor.getNombre());
                nuevConductor.setTelefono(conductor.getTelefono());

                //llenar infor uber
                nuevUber.setAnio(conductor.getUber().getAnio());
                nuevUber.setColor(conductor.getUber().getColor());
                nuevUber.setConductor(nuevConductor);
                nuevUber.setLat(conductor.getUber().getLat());
                nuevUber.setLng(conductor.getUber().getLng());
                nuevUber.setUbicacionNombre(conductor.getUber().getUbicacionNombre());
                nuevUber.setMarca(conductor.getUber().getMarca());
                nuevUber.setPlaca(conductor.getUber().getPlaca());
                tipoUberEncontrado.setIdTipoUber(conductor.getUber().getTipoUber());
                nuevUber.setTipoUber(tipoUberEncontrado);

                nuevConductor.setUber(nuevUber);
                this.conductorRepository.save(nuevConductor);

                //Crear Historico
                historicoUber.setAnio(nuevUber.getAnio());
                historicoUber.setColor(nuevUber.getColor());
                historicoUber.setConductor(nuevConductor);
                historicoUber.setFechaInicio(fechaActual);
                historicoUber.setMarca(nuevUber.getMarca());
                historicoUber.setPlaca(nuevUber.getPlaca());

                this.historicoUberRepository.save(historicoUber);
                
                return true;
            }
            
        }

        return false;
    }

    @Override
    public int login(LoginDto login) {
        if(login != null){
            
            List<Conductor> conductores= this.conductorRepository.findAll();
           

            for (Conductor conductor : conductores) {
                if(conductor.getContrasena().equals(login.getContrasena()) && conductor.getCorreo().equals(login.getCorreo())){
                    return conductor.getIdConductor(); 
                }
            }
            
        }

        return -1;
    }

    @Override
    public ConductorLoginDto obtenerInfoPaginaPrincipal(int idConductor) {
        ConductorLoginDto conductorLoginDto= new ConductorLoginDto();

        if(this.conductorRepository.existsById(idConductor)){
            Conductor conductor = this.conductorRepository.findById(idConductor).get();

            conductorLoginDto.setApellido(conductor.getApellido());
            conductorLoginDto.setIdConductor(conductor.getIdConductor());
            conductorLoginDto.setNombre(conductor.getNombre());

            List<Carrera> carreras= conductor.getCarreras();
            List<FacturaDto> facturas= new ArrayList<>();

            for (Carrera carrera : carreras) {
                
                //obtener carrera en progreso
                if(carrera.getEstado()==0){

                    CarreraEnProgresoDto carreraEnProgresoDto = new CarreraEnProgresoDto();
                    carreraEnProgresoDto.setFactura(carrera.getFactura());
                    carreraEnProgresoDto.setLatFinal(carrera.getLatFinal());
                    carreraEnProgresoDto.setLatInicio(carrera.getLatInicio());
                    carreraEnProgresoDto.setLngFinal(carrera.getLngFinal());
                    carreraEnProgresoDto.setLngInicio(carrera.getLngInicio());
                    carreraEnProgresoDto.setUbicacionFinal(carrera.getUbicacionFinal());
                    carreraEnProgresoDto.setUbicacionInicial(carrera.getUbicacionInicial());

                    //cliente
                    ClienteConductorDto clienteConductor = new ClienteConductorDto();
                    Cliente cliente= carrera.getCliente();

                    clienteConductor.setApellido(cliente.getApellido());
                    clienteConductor.setIdCliente(cliente.getIdCliente());
                    clienteConductor.setNombre(cliente.getNombre());
                    clienteConductor.setTelefono(cliente.getTelefono());

                    carreraEnProgresoDto.setCliente(clienteConductor);
                    
                    conductorLoginDto.setCarreraEnProgreso(carreraEnProgresoDto);

                }else{

                    FacturaDto facturaDto= new FacturaDto();
                    facturaDto.setCarrera(carrera.getIdCarrera());
                    SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd");
                    facturaDto.setFecha(dt.format(carrera.getFactura().getFecha()));
                    facturaDto.setMetodoPago(carrera.getFactura().getMetodoPago().getDescripcion());
                    facturaDto.setTotal(carrera.getFactura().getTotal());
                    facturaDto.setIdFactura(carrera.getFactura().getIdFactura());
                    facturaDto.setEstadoCarrera("Finalizado");
                    facturas.add(facturaDto);
                }
            }

            conductorLoginDto.setFacturas(facturas);
        }

        return conductorLoginDto;
    }

    @Override
    public boolean eliminarConductor(int idConductor) {

        if(this.conductorRepository.existsById(idConductor)){
            //poner fecha final en el historico
            List<HistoricoUber> historicos= this.historicoUberRepository.findAll();
            Date fechaActual= new Date();
            Uber uber= this.conductorRepository.findById(idConductor).get().getUber();

            for (HistoricoUber historicoUber : historicos) {
                if(historicoUber.getPlaca().equals(uber.getPlaca())){
                    historicoUber.setFechaFinal(fechaActual);
                    this.historicoUberRepository.save(historicoUber);
                }
            }
            this.conductorRepository.deleteById(idConductor);
            return true;     
        }
        
        
        return false;
    }

    @Override
    public boolean actualizarConductor(int idConductor, ActualizarConductorDto actualizar) {

        if(this.conductorRepository.existsById(idConductor)){
            Conductor Conductor1 = conductorRepository.findById(idConductor).get();
            
            Conductor1.setNombre(actualizar.getNombre());
            Conductor1.setApellido(actualizar.getApellido());
            Conductor1.setCorreo(actualizar.getCorreo());
            Conductor1.setTelefono(actualizar.getTelefono());
            Conductor1.setFechaNacimiento(actualizar.getFechaNacimiento());
            Conductor1.setContrasena(actualizar.getContrasena());
            conductorRepository.save(Conductor1);
            return true;
            
        }
        
        return false;
    }

}
