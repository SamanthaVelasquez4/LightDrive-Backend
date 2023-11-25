package backend.lightdriving.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UberDto {

    private int idUber;

    private String marca;

    private String color;

    private String placa;

    private int anio;

    private double lat;

    private double lng;

    private int tipoUber;

}
