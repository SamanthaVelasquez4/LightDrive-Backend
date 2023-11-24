package backend.lightdriving.backend.servicios.Implementaciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import backend.lightdriving.backend.modelos.Conductor;
import backend.lightdriving.backend.modelos.HistoricoUber;
import backend.lightdriving.backend.repositorios.ConductorRepository;
import backend.lightdriving.backend.servicios.ConductorService;

public class ConductorServiceImpl implements ConductorService{

    @Autowired
    ConductorRepository conductorRepository;
    @Override
    public Conductor logIn(String correo, String contrasna) {
        Conductor Conductor = conductorRepository.findByCorreo(correo);
        if(Conductor != null){
            String contrasena = Conductor.getContrasena();
            if(contrasena.equals(contrasna)){
            return Conductor;
            }
        }
        return null;
    }

    @Override
    public Conductor crearConductor(Conductor Conductor) {
        return conductorRepository.save(Conductor);
    }

    @Override
    public boolean eliminarConductor(int idConductor) {
        Conductor Conductor = conductorRepository.findById(idConductor).get();
        if(Conductor != null) {
            conductorRepository.delete(Conductor);
            return true;
        }
        return false;
    }

    @Override
    public boolean actualizarConductor(int idConductor, Conductor Conductor) {
        Conductor Conductor1 = conductorRepository.findById(idConductor).get();
        if(Conductor1 != null) {
            Conductor1.setNombre(Conductor.getNombre());
            Conductor1.setApellido(Conductor.getApellido());
            Conductor1.setCorreo(Conductor.getCorreo());
            Conductor1.setTelefono(Conductor.getTelefono());
            Conductor1.setFechaNacimiento(Conductor.getFechaNacimiento());
            conductorRepository.save(Conductor1);
            return true;
        }
        return false;
    }

    @Override
    public Conductor obtenerConductor(int idConductor) {
        return conductorRepository.findById(idConductor).get();
    }

    @Override
    public List<HistoricoUber> obtenerHistoricoUbers(int idConductor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerHistoricoUbers'");
    }
    
}
