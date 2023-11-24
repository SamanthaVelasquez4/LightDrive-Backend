package backend.lightdriving.backend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.lightdriving.backend.modelos.TipoUber;

public interface TipoUberRepository extends JpaRepository<TipoUber, Integer>{
    
}
