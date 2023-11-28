package backend.lightdriving.backend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ClienteConductorDto {

    private int idCliente;

    private String nombre;

    private String apellido;

    private String telefono;

}
