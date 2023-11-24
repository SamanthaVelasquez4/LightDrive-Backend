package backend.lightdriving.backend.modelos;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "conductor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Conductor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idconductor")
    private int idConductor;

    private String nombre;

    private String apellido;

    @Column(unique = true)
    private String correo;

    private String contrasena;

    private boolean disponible;

    @Column(name = "fechanacimiento")
    private Date fechaNacimiento;

    @Column(name = "fechacontratacion")
    private Date fechaContratacion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "iduber", referencedColumnName = "iduber")
    private Uber uber;

    @OneToMany(mappedBy = "conductor")
    private List<Carrera> carreras;
}
