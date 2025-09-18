package paolooliviero.capstone.entities;

import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@ToString
public class EventoMancanzaProdotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate periodoIniziale;
    private LocalDate periodoFinale;
    @ManyToOne
    private Utente scopertoda;
    @ManyToOne
    private ProdottoMagazzino prodottoMagazzino;

    public EventoMancanzaProdotto ()
    {}

    public EventoMancanzaProdotto(LocalDate periodoiniziale, LocalDate periodofinale, Utente scopertoda, ProdottoMagazzino prodottoMagazzino) {
        this.periodoIniziale = periodoiniziale;
        this.periodoFinale = periodofinale;
        this.scopertoda = scopertoda;
        this.prodottoMagazzino = prodottoMagazzino;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPeriodoIniziale() {
        return periodoIniziale;
    }

    public void setPeriodoIniziale(LocalDate periodoiniziale) {
        this.periodoIniziale = periodoiniziale;
    }

    public LocalDate getPeriodoFinale() {
        return periodoFinale;
    }

    public void setPeriodoFinale(LocalDate periodofinale) {
        this.periodoFinale = periodofinale;
    }

    public Utente getScopertoda() {
        return scopertoda;
    }

    public void setScopertoda(Utente scopertoda) {
        this.scopertoda = scopertoda;
    }

    public ProdottoMagazzino getProdottoMagazzino() {
        return prodottoMagazzino;
    }

    public void setProdottoMagazzino(ProdottoMagazzino prodottoMagazzino) {
        this.prodottoMagazzino = prodottoMagazzino;
    }
}
