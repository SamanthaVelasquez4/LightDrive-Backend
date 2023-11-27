package backend.lightdriving.backend.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FacturaDto {

    private int idFactura;

    private double total;

    private Date fecha;

    private int carrera;

    private String metodoPago;

    private String estadoCarrera;
    
}
