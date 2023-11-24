package backend.lightdriving.backend.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
@Table(name = "carrera")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Carrera {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcarrera")
    private int idCarrera;

    @Column(name = "latinicio")
    private double latInicio;

    @Column(name = "lnginicio")
    private double lngInicio;

    @Column(name = "latfinal")
    private double latFinal;

    @Column(name = "lngfinal")
    private double lngFinal;

    private int estado;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idconductor", referencedColumnName = "idconductor")
    private Conductor conductor;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    private Cliente cliente;

    @OneToOne(mappedBy = "carrera", cascade = CascadeType.ALL)
    private Factura factura;
}
