package backend.lightdriving.backend.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ActualizarConductorDto {
    
    private String nombre;

    private String apellido;

    private String correo;

    private String telefono;

    private Date fechaNacimiento;

}
