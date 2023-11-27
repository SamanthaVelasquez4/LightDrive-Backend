package backend.lightdriving.backend.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class FacturasClienteDto {

    private String nombre;

    private String apellido;

    private List<FacturaDto> facturas = new ArrayList<>();
    
}
