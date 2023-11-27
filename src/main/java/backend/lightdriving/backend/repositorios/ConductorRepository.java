package backend.lightdriving.backend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.lightdriving.backend.modelos.Conductor;
import java.util.List;


public interface ConductorRepository extends JpaRepository<Conductor,Integer>{
    List<Conductor> findByUbicacionLatBetweenAndUbicacionLongBetween(
        double latitudMin, double latitudMax, double longitudMin, double longitudMax);
}
