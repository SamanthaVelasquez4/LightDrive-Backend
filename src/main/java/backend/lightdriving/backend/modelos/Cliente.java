package backend.lightdriving.backend.modelos;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcliente")
    private int idCliente;

    private String nombre;

    private String apellido;

    @Column(unique = true)
    private String correo;

    private String contrasena;

    private String telefono;

    @Column(name = "fechanacimiento")
    private Date fechaNacimiento;

    private double lat;

    private double lng;

    @OneToMany(mappedBy = "cliente")
    List<Carrera> carreras;
}
