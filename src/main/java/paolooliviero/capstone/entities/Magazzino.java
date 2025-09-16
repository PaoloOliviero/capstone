package paolooliviero.capstone.entities;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;

@Entity
@ToString
public class Magazzino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double capacitàTotale;
    private double capacitàOccupata;
    @OneToMany(mappedBy = "magazzino")
    private List<ProdottoMagazzino> prodottiinMagazzino;

}
