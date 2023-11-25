package backend.lightdriving.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class VerificarRutaDto {
    
    private double latInicio;

    private double lngInicio;

    private double latFinal;

    private double lngFinal;
}
