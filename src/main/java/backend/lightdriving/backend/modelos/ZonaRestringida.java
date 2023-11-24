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
@Table(name = "zonasrestringidas")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ZonaRestringida {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idzonarestringida")
    private int idZonaRestringida;

    private double lat;

    private double lng;
}
