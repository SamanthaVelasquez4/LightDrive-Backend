package backend.lightdriving.backend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.lightdriving.backend.modelos.MetodoPago;

public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer>{
    
}
