package backend.lightdriving.backend.servicios;

import backend.lightdriving.backend.modelos.Uber;

public interface UberService {

    public Uber crearUber(Uber Uber);

    public boolean eliminarUber(int idUber);

    public boolean actualizarUber(int idUber, Uber Uber);

    public Uber obtenerUber(int idUber);
}
