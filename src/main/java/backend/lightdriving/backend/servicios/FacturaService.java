package backend.lightdriving.backend.servicios;

import backend.lightdriving.backend.modelos.Factura;

public interface FacturaService {

    public boolean actualizarFactura(int idFactura, Factura Factura);
    
    public Factura obtenerFactura(int idFactura);

}
