package backend.lightdriving.backend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.lightdriving.backend.modelos.HistoricoUber;

public interface HistoricoUberRepository extends JpaRepository<HistoricoUber, Integer>{
    
}
