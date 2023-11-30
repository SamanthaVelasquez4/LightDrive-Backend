package backend.lightdriving.backend.servicios.Implementaciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.lightdriving.backend.dto.CoordenadaDto;
import backend.lightdriving.backend.dto.RespuestaDto;
import backend.lightdriving.backend.dto.UberCercanoDto;
import backend.lightdriving.backend.dto.UberDto;
import backend.lightdriving.backend.dto.VerificarRutaDto;
import backend.lightdriving.backend.modelos.Conductor;
import backend.lightdriving.backend.modelos.HistoricoUber;
import backend.lightdriving.backend.modelos.TipoUber;
import backend.lightdriving.backend.modelos.Uber;
import backend.lightdriving.backend.modelos.ZonaRestringida;
import backend.lightdriving.backend.repositorios.ConductorRepository;
import backend.lightdriving.backend.repositorios.HistoricoUberRepository;
import backend.lightdriving.backend.repositorios.TipoUberRepository;
import backend.lightdriving.backend.repositorios.UberRepository;
import backend.lightdriving.backend.repositorios.ZonaRestringidaRepository;
import backend.lightdriving.backend.servicios.UberService;

@Service
public class UberServiceImpl implements UberService{

    @Autowired
    private UberRepository uberRepository;

    @Autowired 
    private HistoricoUberRepository historicoUberRepository;

    @Autowired
    private TipoUberRepository tipoUberRepository;

    @Autowired
    private ConductorRepository conductorRepository;

    @Autowired
    private ZonaRestringidaRepository zonaRestringidaRepository;

    @Override
    public Uber obtenerUber(int idUber) {
        if(this.uberRepository.existsById(idUber)){
            return this.uberRepository.findById(idUber).get();
        }

        return null;
    }

    @Override
    public boolean cambiarCarro(int idUber, UberDto uber) {
        if(this.uberRepository.existsById(idUber) && this.tipoUberRepository.existsById(uber.getTipoUber())){

            TipoUber tipoUberEncontrado= this.tipoUberRepository.findById(uber.getTipoUber()).get();
            Uber uberActual= this.uberRepository.findById(idUber).get();
            Conductor conductor= this.conductorRepository.findById(uberActual.getConductor().getIdConductor()).get();

            //poner fecha final en el historico anterior
            List<HistoricoUber> historicos= this.historicoUberRepository.findAll();
            Date fechaActual= new Date();

            for (HistoricoUber historicoUber : historicos) {
                if(historicoUber.getPlaca().equals(uberActual.getPlaca())){
                    historicoUber.setFechaFinal(fechaActual);
                    this.historicoUberRepository.save(historicoUber);
                }
            }
            
            //Crear Nuevo uber
            Uber nuevUber= new Uber();

            nuevUber.setAnio(uber.getAnio());
            nuevUber.setColor(uber.getColor());
            nuevUber.setConductor(uberActual.getConductor());
            nuevUber.setLat(uber.getLat());
            nuevUber.setLng(uber.getLng());
            nuevUber.setUbicacionNombre(uber.getUbicacionNombre());
            nuevUber.setMarca(uber.getMarca());
            nuevUber.setPlaca(uber.getPlaca());
            tipoUberEncontrado.setIdTipoUber(uber.getTipoUber());
            nuevUber.setTipoUber(tipoUberEncontrado);
            

            conductor.setUber(nuevUber);
            this.conductorRepository.save(conductor);
            this.uberRepository.delete(uberActual);

            //Crear Historico
            HistoricoUber nuevoHistorico= new HistoricoUber();
            nuevoHistorico.setAnio(uber.getAnio());
            nuevoHistorico.setColor(uber.getColor());
            nuevoHistorico.setConductor(uberActual.getConductor());
            nuevoHistorico.setFechaInicio(fechaActual);
            nuevoHistorico.setMarca(uber.getMarca());
            nuevoHistorico.setPlaca(uber.getPlaca());

            uberActual.setPlaca(uber.getPlaca());

            this.historicoUberRepository.save(nuevoHistorico);
            return true;

        }
        
        return false;
    }

    
    @Override
    public RespuestaDto obtenerUbersCliente(VerificarRutaDto ruta) {

        CoordenadaDto coordenadaInicio = new CoordenadaDto(ruta.getLatInicio(), ruta.getLngInicio(), null);
        CoordenadaDto coordenadaFinal = new CoordenadaDto(ruta.getLatFinal(), ruta.getLngFinal(), null);
        RespuestaDto respuesta= new RespuestaDto();

        //Verificar posicion de inicio
        if(!verificarPosicion(coordenadaInicio)){
            respuesta.setExito(false);
            respuesta.setMensaje("Punto de inicio ubicado en zona restringida");
            respuesta.setUbers(null);
            return respuesta;
        }
 
        //verificar posicion final
        if(!verificarPosicion(coordenadaFinal)){
            respuesta.setExito(false);
            respuesta.setMensaje("Punto de destino ubicado en zona restringida");
            respuesta.setUbers(null);
            return respuesta;
        }

        //obtener ubers cercanos
        List<Uber> ubersEncontrados=this.obtenerUbersCercanos(coordenadaInicio);
        List<UberCercanoDto> ubers= new ArrayList<>();
        double distaciaRecorrida= this.calcularDistancia(ruta.getLatInicio(), ruta.getLngInicio(), ruta.getLatFinal(), ruta.getLngFinal());

        if(ubersEncontrados.size()>0){
            for (Uber uber : ubersEncontrados) {
                //filtrar ubers disponibles
                if(uber.getConductor().getDisponible()){
                    UberCercanoDto uberCercanoDto = new UberCercanoDto();
                    uberCercanoDto.setApellido(uber.getConductor().getApellido());
                    uberCercanoDto.setColor(uber.getColor());
                    uberCercanoDto.setIdConductor(uber.getConductor().getIdConductor());
                    uberCercanoDto.setLat(uber.getLat());
                    uberCercanoDto.setLng(uber.getLng());
                    uberCercanoDto.setMarca(uber.getMarca());
                    uberCercanoDto.setNombre(uber.getConductor().getNombre());
                    uberCercanoDto.setPlaca(uber.getPlaca());
                    uberCercanoDto.setTelefono(uber.getConductor().getTelefono());
                    uberCercanoDto.setTipoUber(uber.getTipoUber());
                    uberCercanoDto.setUbicacionNombre(uber.getUbicacionNombre());

                    //Calcular distancia
                    double total= this.calcularTotal(uber.getTipoUber(), distaciaRecorrida);
                    uberCercanoDto.setTotal(Math.ceil(total));

                    ubers.add(uberCercanoDto);
                }
            }

            if(ubers.size()>0){
                respuesta.setExito(true);
                respuesta.setMensaje("Ubers encontrados");
                respuesta.setUbers(ubers);
                return respuesta;
            }
            
        }

        //No hay ubers cercanos disponibles -> mandar todos los ubers disponibles
        List<Uber> todosUbers = this.uberRepository.findAll();
        for (Uber uber : todosUbers) {
            if(uber.getConductor().getDisponible()){

                UberCercanoDto uberCercanoDto = new UberCercanoDto();

                uberCercanoDto.setApellido(uber.getConductor().getApellido());
                uberCercanoDto.setColor(uber.getColor());
                uberCercanoDto.setIdConductor(uber.getConductor().getIdConductor());
                uberCercanoDto.setLat(uber.getLat());
                uberCercanoDto.setLng(uber.getLng());
                uberCercanoDto.setMarca(uber.getMarca());
                uberCercanoDto.setNombre(uber.getConductor().getNombre());
                uberCercanoDto.setPlaca(uber.getPlaca());
                uberCercanoDto.setTelefono(uber.getConductor().getTelefono());
                uberCercanoDto.setTipoUber(uber.getTipoUber());
                uberCercanoDto.setUbicacionNombre(uber.getUbicacionNombre());

                //Calcular distancia
                double total= this.calcularTotal(uber.getTipoUber(), distaciaRecorrida);
                uberCercanoDto.setTotal(Math.ceil(total));

                ubers.add(uberCercanoDto);
            }
        }

        if(ubers.size()>0){
            respuesta.setExito(true);
            respuesta.setMensaje("No se encontraron ubers cercanos");
            respuesta.setUbers(ubers);
            return respuesta;
        }
        
        //no hay ubers disponibles
        respuesta.setExito(false);
        respuesta.setMensaje("No hay ubers disponibles");
        respuesta.setUbers(ubers);
        return respuesta;
        
    }

    
    public boolean verificarPosicion(CoordenadaDto coordenada) {
        
        List<ZonaRestringida> zonas= this.zonaRestringidaRepository.findAll();
        boolean bandera= true;
        
        for (ZonaRestringida zonaRestringida : zonas) {
            if(this.calcularDistancia(zonaRestringida.getLat(), zonaRestringida.getLng(), coordenada.getLat(), coordenada.getLng())<=1){
                return false;
            }
        }
        return bandera;
        
    }

    public List<Uber> obtenerUbersCercanos(CoordenadaDto coordenadaDto) {
        double latitudRad = Math.toRadians(coordenadaDto.getLat());
        double longitudRad = Math.toRadians(coordenadaDto.getLng());

        // Radio de la Tierra en kilómetros
        double radioTierra = 6371.0;
        double radioEnKm = 4;

        // Calcular las coordenadas del cuadro delimitador alrededor del punto central
        double latitudMin = Math.toDegrees(latitudRad - (radioEnKm / radioTierra));
        double latitudMax = Math.toDegrees(latitudRad + (radioEnKm / radioTierra));
        double longitudMin = Math.toDegrees(longitudRad - (radioEnKm / radioTierra));
        double longitudMax = Math.toDegrees(longitudRad + (radioEnKm / radioTierra));

        //Consultar Ubers dentro del cuadro delimitador
        List<Uber> UbersEnRango = uberRepository.findByLatBetweenAndLngBetween(
                latitudMin, latitudMax, longitudMin, longitudMax);

        // Filtrar Uberes dentro del radio
        UbersEnRango.removeIf(Uber ->
                calcularDistancia(coordenadaDto.getLat(), coordenadaDto.getLng(), Uber.getLat(), Uber.getLng()) > radioEnKm);

        return UbersEnRango;
    }

    public double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
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
        //System.out.println(distancia);
        return distancia;
    }
    
    public double calcularTotal(TipoUber tipoUber, double distaciaRecorrida){

        double total=0;

        if(distaciaRecorrida>3){
            total = tipoUber.getPrecioBase() + (distaciaRecorrida-3)*tipoUber.getPrecioXkm();
        }else{
            total = tipoUber.getPrecioBase();
        }

        return total;
    }
}
