package backend.lightdriving.backend.servicios.Implementaciones;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.lightdriving.backend.dto.ActualizarConductorDto;
import backend.lightdriving.backend.dto.ConductorDto;
import backend.lightdriving.backend.dto.ConductoresCercaDto;
import backend.lightdriving.backend.dto.LoginDto;
import backend.lightdriving.backend.modelos.Carrera;
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
                nuevConductor.setUbicacionLat(conductor.getUbicacionLat());
                nuevConductor.setUbicacionLong(conductor.getUbicacionLong());

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

    @Override
    public List<Carrera> obtenerCarreras(int idConductor) {
        if(this.conductorRepository.existsById(idConductor)){

            return conductorRepository.findById(idConductor).get().getCarreras();
             
         }
 
         return null;
    }

    @Override
    public List<Conductor> obtenerConductoresCercanos(ConductoresCercaDto conductoresCercaDto) {
        double latitudRad = Math.toRadians(conductoresCercaDto.getLatInicio());
        double longitudRad = Math.toRadians(conductoresCercaDto.getLngInicio());

        // Radio de la Tierra en kilómetros
        double radioTierra = 6371.0;
        double radioEnKm = 4;

        // Calcular las coordenadas del cuadro delimitador alrededor del punto central
        double latitudMin = Math.toDegrees(latitudRad - (radioEnKm / radioTierra));
        double latitudMax = Math.toDegrees(latitudRad + (radioEnKm / radioTierra));
        double longitudMin = Math.toDegrees(longitudRad - (radioEnKm / radioTierra));
        double longitudMax = Math.toDegrees(longitudRad + (radioEnKm / radioTierra));

        // Consultar conductores dentro del cuadro delimitador
        List<Conductor> conductoresEnRango = conductorRepository.findByUbicacionLatBetweenAndUbicacionLongBetween(
                latitudMin, latitudMax, longitudMin, longitudMax);

        // Filtrar conductores dentro del radio
        conductoresEnRango.removeIf(conductor ->
                calcularDistancia(conductoresCercaDto.getLatInicio(), conductoresCercaDto.getLngInicio(), conductor.getUbicacionLat(), conductor.getUbicacionLong()) > radioEnKm);

        return conductoresEnRango;
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
