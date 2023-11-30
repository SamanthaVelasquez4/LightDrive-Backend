package backend.lightdriving.backend.servicios;

import java.util.List;

import backend.lightdriving.backend.modelos.MetodoPago;

public interface MetodoPagoService {

    public List<MetodoPago> obtenerMetodos();
}
