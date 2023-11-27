package backend.lightdriving.backend.servicios;

import java.util.List;

import backend.lightdriving.backend.dto.FacturasClienteDto;
import backend.lightdriving.backend.modelos.Factura;

public interface FacturaService {

    public boolean actualizarFactura(int idFactura, Factura Factura);
    
    public Factura obtenerFactura(int idFactura);

    public FacturasClienteDto obtenerFacturasCliente(int idCliente);

    public List<Factura> obtenerTodo();

}
