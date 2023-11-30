package backend.lightdriving.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CarreraClienteDto {

    private int idCarrera;

    private String metodoPago;

    private String ubicacionInicial;

    private String ubicacionFinal;

    private double total;

    private String estadoCarrera;

    private String fecha;

    private ConductorCarreraDto conductor;
}
