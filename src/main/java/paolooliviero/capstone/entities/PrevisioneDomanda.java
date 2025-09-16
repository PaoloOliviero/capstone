package paolooliviero.capstone.entities;


import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@ToString
public class PrevisioneDomanda {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private LocalDate periodoinizio;
    private LocalDate periodoFine;
    private Integer quantitàprevista;
    @ManyToOne
    private Prodotto prodotto;
    @ManyToOne
    private Magazzino magazzino;

    public PrevisioneDomanda() {}

    public PrevisioneDomanda(LocalDate periodoinizio, LocalDate periodoFine, Integer quantitàprevista, Prodotto prodotto, Magazzino magazzino) {
        this.periodoinizio = periodoinizio;
        this.periodoFine = periodoFine;
        this.quantitàprevista = quantitàprevista;
        this.prodotto = prodotto;
        this.magazzino = magazzino;
    }

    public LocalDate getPeriodoinizio() {
        return periodoinizio;
    }

    public void setPeriodoinizio(LocalDate periodoinizio) {
        this.periodoinizio = periodoinizio;
    }

    public LocalDate getPeriodoFine() {
        return periodoFine;
    }

    public void setPeriodoFine(LocalDate periodoFine) {
        this.periodoFine = periodoFine;
    }

    public Integer getQuantitàprevista() {
        return quantitàprevista;
    }

    public void setQuantitàprevista(Integer quantitàprevista) {
        this.quantitàprevista = quantitàprevista;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public Magazzino getMagazzino() {
        return magazzino;
    }

    public void setMagazzino(Magazzino magazzino) {
        this.magazzino = magazzino;
    }
}
