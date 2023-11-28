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

public class RespuestaDto {
    
    private boolean exito;

    private String mensaje;

    private List<UberCercanoDto> ubers = new ArrayList<>();
}
