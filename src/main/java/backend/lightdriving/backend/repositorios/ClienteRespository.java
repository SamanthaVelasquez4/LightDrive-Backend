package backend.lightdriving.backend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.lightdriving.backend.modelos.Cliente;

public interface ClienteRespository extends JpaRepository<Cliente, Integer>{
    
}
