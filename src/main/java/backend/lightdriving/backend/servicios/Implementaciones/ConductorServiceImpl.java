package backend.lightdriving.backend.servicios.Implementaciones;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.lightdriving.backend.dto.ActualizarConductorDto;
import backend.lightdriving.backend.dto.ConductorDto;
import backend.lightdriving.backend.dto.LoginDto;
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
    public Conductor login(LoginDto login) {
        if(login != null){
            
            List<Conductor> conductores= this.conductorRepository.findAll();

            for (Conductor conductor : conductores) {
                if(conductor.getContrasena().equals(login.getContrasena()) && conductor.getCorreo().equals(login.getCorreo())){
                    conductor.setContrasena(null);
                    return conductor;
                }
            }
            
        }

        return null;
    }

    @Override
    public boolean eliminarConductor(int idConductor) {

        if(this.conductorRepository.existsById(idConductor)){
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
            conductorRepository.save(Conductor1);
            return true;
            
        }
        
        return false;
    }

    @Override
    public Conductor obtenerConductor(int idConductor) {
        if(this.conductorRepository.existsById(idConductor)){
            Conductor conductor= this.conductorRepository.findById(idConductor).get();
            conductor.setContrasena(null);
            return conductor;
        }
        
        return null;
    }

}
