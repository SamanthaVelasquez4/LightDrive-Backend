package backend.lightdriving.backend.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "uber")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Uber {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduber")
    private int idUber;

    private String marca;

    private String color;

    @Column(unique = true)
    private String placa;

    private int anio;

    private double lat;

    private double lng;

    @Column(name = "ubicacionnombre")
    private String ubicacionNombre;

    @ManyToOne
    @JoinColumn(name = "idtipouber", referencedColumnName = "idtipouber")
    private TipoUber tipoUber;

    @JsonIgnore
    @OneToOne(mappedBy = "uber")
    private Conductor conductor;
}
