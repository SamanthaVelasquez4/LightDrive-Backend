package backend.lightdriving.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CarreraDto {

    private double latInicio;

    private double lngInicio;

    private double latFinal;

    private double lngFinal;

    private int idConductor;
    
    private int idCliente;

    private int matodo
}
