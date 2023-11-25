package backend.lightdriving.backend.servicios;

import backend.lightdriving.backend.modelos.MetodoPago;

public interface MetodoPagoService {

    public MetodoPago crearMetodoPago(MetodoPago MetodoPago);

    public boolean eliminarMetodoPago(int idMetodoPago);

    public boolean actualizarMetodoPago(int idMetodoPago, MetodoPago MetodoPago);
    
    public MetodoPago ontenerMetodoPago(int idMetodoPago);
}
