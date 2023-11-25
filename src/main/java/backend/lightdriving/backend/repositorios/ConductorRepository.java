package backend.lightdriving.backend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.lightdriving.backend.modelos.Conductor;

public interface ConductorRepository extends JpaRepository<Conductor,Integer>{
}
