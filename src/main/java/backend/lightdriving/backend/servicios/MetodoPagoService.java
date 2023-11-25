package backend.lightdriving.backend.servicios;

import java.util.List;

import backend.lightdriving.backend.modelos.MetodoPago;

public interface MetodoPagoService {

    public boolean crearMetodoPago(MetodoPago metodoPago);

    public boolean eliminarMetodoPago(int idMetodoPago);

    public boolean actualizarMetodoPago(int idMetodoPago, MetodoPago metodoPago);
    
    public MetodoPago obtenerMetodoPago(int idMetodoPago);

    public List<MetodoPago> obtenerMetodos();
}
