package backend.lightdriving.backend.modelos;

import java.util.Date;

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
@Table(name = "factura")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Factura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfactura")
    private int idFactura;

    private double total;

    private Date fecha;

    @OneToOne
    @JoinColumn(name = "idcarrera", referencedColumnName = "idcarrera")
    private Carrera carrera;

    @ManyToOne
    @JoinColumn(name = "idmetodopago", referencedColumnName = "idmetodopago")
    private MetodoPago metodoPago;
}
