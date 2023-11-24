package backend.lightdriving.backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarreraDto {
    private double latIncio;
    private double lngInicio;
    private double latFinal;
    private double lngFinal;
    private int estado;
    private int idConductor;
    private int idCliente;
}
