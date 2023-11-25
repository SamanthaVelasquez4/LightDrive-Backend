package backend.lightdriving.backend.servicios.Implementaciones;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import backend.lightdriving.backend.modelos.Factura;
import backend.lightdriving.backend.repositorios.FacturaRepository;
import backend.lightdriving.backend.servicios.FacturaService;

public class FacuraServiceImpl implements FacturaService {
    @Autowired
    FacturaRepository facturaRepository;

    @Override
    public boolean actualizarFactura(int idFactura, Factura Factura) {
        Factura factura1 = facturaRepository.findById(idFactura).get();
        if(factura1 != null) {
            Date date = new Date(System.currentTimeMillis());
            factura1.setFecha(date);
            factura1.setCarrera(Factura.getCarrera());
            factura1.setMetodoPago(Factura.getMetodoPago());
            factura1.setTotal(Factura.getTotal());
            facturaRepository.save(factura1);
            return true;
        }
        return false;
    }

    @Override
    public Factura obtenerFactura(int idFactura) {
        return facturaRepository.findById(idFactura).get();
    }

}
