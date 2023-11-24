package backend.lightdriving.backend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.lightdriving.backend.modelos.ZonaRestringida;

public interface ZonaRestringidaRepository extends JpaRepository<ZonaRestringida, Integer>{
    
}
