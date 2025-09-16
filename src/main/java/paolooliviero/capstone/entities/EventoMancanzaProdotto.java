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
    private LocalDate periodoiniziale;
    private LocalDate periodofinale;
    @ManyToOne
    private Utente scopertoda;
    @ManyToOne
    private ProdottoMagazzino prodottoMagazzino;

    public EventoMancanzaProdotto ()
    {}

    public EventoMancanzaProdotto(LocalDate periodoiniziale, LocalDate periodofinale, Utente scopertoda, ProdottoMagazzino prodottoMagazzino) {
        this.periodoiniziale = periodoiniziale;
        this.periodofinale = periodofinale;
        this.scopertoda = scopertoda;
        this.prodottoMagazzino = prodottoMagazzino;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPeriodoiniziale() {
        return periodoiniziale;
    }

    public void setPeriodoiniziale(LocalDate periodoiniziale) {
        this.periodoiniziale = periodoiniziale;
    }

    public LocalDate getPeriodofinale() {
        return periodofinale;
    }

    public void setPeriodofinale(LocalDate periodofinale) {
        this.periodofinale = periodofinale;
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
