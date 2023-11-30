package backend.lightdriving.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ConductorCarreraDto {
    
    private int idConductor;

    private String nombre;

    private String apellido;

    private String telefono;

    private String marca;

    private String color;

    private String placa;

}
