package backend.lightdriving.backend.dto;

import backend.lightdriving.backend.modelos.Factura;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CarreraEnProgresoDto {

    private double latInicio;

    private double lngInicio;

    private double latFinal;

    private double lngFinal;

    private String ubicacionInicial;

    private String ubicacionFinal;

    private Factura factura;

    private ClienteConductorDto cliente;

    private int idCarrera;
}
