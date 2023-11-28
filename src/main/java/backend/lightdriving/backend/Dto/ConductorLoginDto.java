package backend.lightdriving.backend.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ConductorLoginDto {
    
    private int idConductor;

    private String nombre;

    private String apellido;

    private CarreraEnProgresoDto carreraEnProgreso;

    private List<FacturaDto> facturas = new ArrayList<>();

}
