package backend.lightdriving.backend.servicios.Implementaciones;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.lightdriving.backend.dto.FacturaDto;
import backend.lightdriving.backend.dto.FacturasClienteDto;
import backend.lightdriving.backend.modelos.Cliente;
import backend.lightdriving.backend.modelos.Factura;
import backend.lightdriving.backend.repositorios.ClienteRepository;
import backend.lightdriving.backend.repositorios.FacturaRepository;
import backend.lightdriving.backend.servicios.FacturaService;

@Service
public class FacturaServiceImpl implements FacturaService {
    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

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

        if(this.facturaRepository.existsById(idFactura)){
            return facturaRepository.findById(idFactura).get();
        }

        return null;
        
    }

    @Override
    public FacturasClienteDto obtenerFacturasCliente(int idCliente) {

        FacturasClienteDto respuesta = new FacturasClienteDto();

        if(this.clienteRepository.existsById(idCliente)){
            List<Factura> facturas= this.facturaRepository.findAll();
            Cliente cliente = this.clienteRepository.findById(idCliente).get();
            

            respuesta.setApellido(cliente.getApellido());
            respuesta.setNombre(cliente.getNombre());

            for (Factura factura : facturas) {
                if(factura.getCarrera().getCliente().getIdCliente()== idCliente){
                    FacturaDto facturaDto= new FacturaDto();
                    facturaDto.setCarrera(factura.getCarrera().getIdCarrera());
                    SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd");
                    facturaDto.setFecha(dt.format(factura.getFecha()));
                    facturaDto.setMetodoPago(factura.getMetodoPago().getDescripcion());
                    facturaDto.setTotal(factura.getTotal());
                    if(factura.getCarrera().getEstado()==0){
                        facturaDto.setEstadoCarrera("En progreso");
                    }else{
                        facturaDto.setEstadoCarrera("Finalizado");
                    }
                    respuesta.getFacturas().add(facturaDto);
                }
            }
        }

        return respuesta;        
    }

    @Override
    public List<Factura> obtenerTodo() {
        return this.facturaRepository.findAll();
    }

}
