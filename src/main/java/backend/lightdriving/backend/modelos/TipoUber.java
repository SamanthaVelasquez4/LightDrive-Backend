package backend.lightdriving.backend.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tipouber")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TipoUber {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtipouber")
    private int idTipoUber;

    private String descripcion;

    @Column(name = "preciobase")
    private double precioBase;

    @Column(name = "precioxkm")
    private double precioXkm;
}
