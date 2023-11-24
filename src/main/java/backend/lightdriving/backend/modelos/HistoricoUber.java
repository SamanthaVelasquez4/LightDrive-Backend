package backend.lightdriving.backend.modelos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "historicouber")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class HistoricoUber {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhistorico")
    private int idHistorico;

    private String marca;

    private String color;

    private String placa;

    private int anio;

    @Column(name = "fechainicio")
    private Date fechaInicio;

    @Column(name = "fechafinal")
    private Date fechaFinal;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idconductor", referencedColumnName = "idconductor")
    private Conductor conductor;
}
