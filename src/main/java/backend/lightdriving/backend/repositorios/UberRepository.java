package backend.lightdriving.backend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.lightdriving.backend.modelos.Uber;

public interface UberRepository extends JpaRepository<Uber, Integer>{
    
}
