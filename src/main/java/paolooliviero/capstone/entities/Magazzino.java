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
    @OneToMany(mappedBy = "magazzino")
    private List<MovimentoMagazzino> movimentoMagazzino;
    @OneToMany(mappedBy = "magazzino")
    private List<Spedizione> spedizione;

    public Magazzino() {}

    public Magazzino(double capacitàTotale, double capacitàOccupata, List<ProdottoMagazzino> prodottiinMagazzino, List<MovimentoMagazzino> movimentoMagazzino) {
        this.capacitàTotale = capacitàTotale;
        this.capacitàOccupata = capacitàOccupata;
        this.prodottiinMagazzino = prodottiinMagazzino;
        this.movimentoMagazzino = movimentoMagazzino;
    }

    public double getCapacitàTotale() {
        return capacitàTotale;
    }

    public void setCapacitàTotale(double capacitàTotale) {
        this.capacitàTotale = capacitàTotale;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCapacitàOccupata() {
        return capacitàOccupata;
    }

    public void setCapacitàOccupata(double capacitàOccupata) {
        this.capacitàOccupata = capacitàOccupata;
    }

    public List<ProdottoMagazzino> getProdottiinMagazzino() {
        return prodottiinMagazzino;
    }

    public void setProdottiinMagazzino(List<ProdottoMagazzino> prodottiinMagazzino) {
        this.prodottiinMagazzino = prodottiinMagazzino;
    }

    public List<MovimentoMagazzino> getMovimentoMagazzino() {
        return movimentoMagazzino;
    }

    public void setMovimentoMagazzino(List<MovimentoMagazzino> movimentoMagazzino) {
        this.movimentoMagazzino = movimentoMagazzino;
    }
}
