package backend.lightdriving.backend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.lightdriving.backend.modelos.Carrera;

public interface CarreraRepository extends JpaRepository<Carrera,Integer> {
    
}
