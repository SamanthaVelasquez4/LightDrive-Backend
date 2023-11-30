package backend.lightdriving.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class CarreraConductorDto {

    private int idCarrera;

    private String metodoPago;

    private String ubicacionInicial;

    private String ubicacionFinal;

    private double total;

    private String estadoCarrera;

    private String fecha;

    private ClienteConductorDto cliente;
    
}
