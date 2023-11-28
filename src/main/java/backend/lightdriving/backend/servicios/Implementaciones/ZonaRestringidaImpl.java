package backend.lightdriving.backend.servicios.Implementaciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.lightdriving.backend.dto.CoordenadaDto;
import backend.lightdriving.backend.modelos.ZonaRestringida;
import backend.lightdriving.backend.repositorios.ZonaRestringidaRepository;
import backend.lightdriving.backend.servicios.ZonaRestringidaService;

@Service
public class ZonaRestringidaImpl implements ZonaRestringidaService{

    @Autowired
    private ZonaRestringidaRepository zonaRestringidaRepository;

    @Override
    public boolean crearZonaRestringida(ZonaRestringida zonaRestringida) {
        if(zonaRestringida != null){
            this.zonaRestringidaRepository.save(zonaRestringida);
            return true;
        }

        return false;
    }

    @Override
    public boolean eliminarZonaRestringida(int idZonaRestringida) {

        if(this.zonaRestringidaRepository.existsById(idZonaRestringida)){
            this.zonaRestringidaRepository.deleteById(idZonaRestringida);
            return true;
        }

        return false;
    }

    @Override
    public boolean actualizarZonaRestringida(int idZonaRestringida, ZonaRestringida zonaRestringida) {
        
        if(zonaRestringida != null){
            ZonaRestringida zonaActualizar= this.zonaRestringidaRepository.findById(idZonaRestringida).get();
            zonaActualizar.setLat(zonaRestringida.getLat());
            zonaActualizar.setLng(zonaRestringida.getLng());
            this.zonaRestringidaRepository.save(zonaActualizar);
            return true;
        }

        return false;
    }

    @Override
    public ZonaRestringida obtenerZonaRestringida(int idZonaRestringida) {
        
        if(this.zonaRestringidaRepository.existsById(idZonaRestringida)){
            return this.zonaRestringidaRepository.findById(idZonaRestringida).get();
        }

        return null;
    }

    @Override
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
        //System.out.println(distancia);
        return distancia;
    }

}
    

