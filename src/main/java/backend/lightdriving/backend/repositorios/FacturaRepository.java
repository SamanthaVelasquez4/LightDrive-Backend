package backend.lightdriving.backend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.lightdriving.backend.modelos.Factura;

public interface FacturaRepository extends JpaRepository<Factura,Integer>{
    
}
