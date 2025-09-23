package paolooliviero.capstone.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;

@Entity
public class Magazzino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double capacitaTotale;
    private double capacitaOccupata;
    @OneToMany(mappedBy = "magazzino")
    @JsonIgnore
    private List<ProdottoMagazzino> prodottiinMagazzino;
    @OneToMany(mappedBy = "magazzino")
    @JsonIgnore
    private List<MovimentoMagazzino> movimentoMagazzino;
    @OneToMany(mappedBy = "magazzino")
    @JsonIgnore
    private List<Spedizione> spedizione;
    @OneToMany(mappedBy = "magazzino")
    @JsonManagedReference
    private List<Utente> utenti;


    public Magazzino() {}

    public Magazzino(double capacitàTotale, double capacitàOccupata, List<ProdottoMagazzino> prodottiinMagazzino, List<MovimentoMagazzino> movimentoMagazzino) {
        this.capacitaTotale = capacitaTotale;
        this.capacitaOccupata = capacitaOccupata;
        this.prodottiinMagazzino = prodottiinMagazzino;
        this.movimentoMagazzino = movimentoMagazzino;
    }

    public double getCapacitaTotale() {
        return capacitaTotale;
    }

    public void setCapacitaTotale(double capacitaTotale) {
        this.capacitaTotale = capacitaTotale;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCapacitaOccupata() {
        return capacitaOccupata;
    }

    public void setCapacitaOccupata(double capacitaOccupata) {
        this.capacitaOccupata = capacitaOccupata;
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

    public List<Utente> getUtenti() {
        return utenti;
    }

    public void setUtenti(List<Utente> utenti) {
        this.utenti = utenti;
    }

    public List<Spedizione> getSpedizione() {
        return spedizione;
    }

    public void setSpedizione(List<Spedizione> spedizione) {
        this.spedizione = spedizione;
    }
}
