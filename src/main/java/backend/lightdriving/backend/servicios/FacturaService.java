package backend.lightdriving.backend.servicios;

import backend.lightdriving.backend.modelos.Factura;

public interface FacturaService {

    public Factura crearFactura(Factura Factura);

    public boolean eliminarFactura(int idFactura);

    public boolean actualizarFactura(int idFactura, Factura Factura);
    
    public Factura obtenerFactura(int idFactura);
}
