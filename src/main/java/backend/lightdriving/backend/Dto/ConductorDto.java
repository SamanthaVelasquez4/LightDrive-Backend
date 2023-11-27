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

public class ConductorDto {
    
    private int idConductor;

    private String nombre;

    private String apellido;

    private String correo;

    private String telefono;

    private String contrasena;

    private Date fechaNacimiento;

    private UberDto uber;

    private double ubicacionLat;

    private double ubicacionLong;
}
