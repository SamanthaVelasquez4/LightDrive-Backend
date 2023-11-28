package backend.lightdriving.backend.servicios;

import backend.lightdriving.backend.dto.ActualizarUberDto;
import backend.lightdriving.backend.dto.RespuestaDto;
import backend.lightdriving.backend.dto.UberDto;
import backend.lightdriving.backend.dto.VerificarRutaDto;
import backend.lightdriving.backend.modelos.Uber;

public interface UberService {

    public boolean actualizarUber(int idUber, ActualizarUberDto uber);

    public boolean cambiarCarro(int idUber, UberDto uber);

    public Uber obtenerUber(int idUber);

    public RespuestaDto obtenerUbersCliente(VerificarRutaDto ruta);

}
