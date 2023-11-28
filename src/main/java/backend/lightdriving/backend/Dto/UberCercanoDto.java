package backend.lightdriving.backend.dto;

import backend.lightdriving.backend.modelos.TipoUber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class UberCercanoDto {
    
    private int idConductor;

    private String nombre;

    private String apellido;

    private String telefono;

    private String marca;

    private String color;

    private String placa;

    private TipoUber tipoUber;

    private double lat;

    private double lng;

    private String ubicacionNombre;
    
}
