package backend.lightdriving.backend.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.lightdriving.backend.modelos.Uber;

public interface UberRepository extends JpaRepository<Uber, Integer>{
    List<Uber> findByLatBetweenAndLngBetween(
        double latitudMin, double latitudMax, double longitudMin, double longitudMax);
}
