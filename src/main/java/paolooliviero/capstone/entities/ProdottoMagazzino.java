package paolooliviero.capstone.entities;

import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@ToString
public class ProdottoMagazzino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantita;
    private LocalDate dataIngresso;

    @ManyToOne
    private Prodotto prodotto;

    @ManyToOne
    @JoinColumn(name = "magazzino_id")
    private Magazzino magazzino;


    public ProdottoMagazzino() {
    }

    public ProdottoMagazzino(Prodotto prodotto, ProdottoMagazzino prodottoMagazzino, Integer quantita, LocalDate dataIngresso) {
        this.prodotto = prodotto;
        this.quantita = quantita;
        this.dataIngresso = dataIngresso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }


    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public LocalDate getDataIngresso() {
        return dataIngresso;
    }

    public void setDataIngresso(LocalDate dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    public Magazzino getMagazzino() {
        return magazzino;
    }

    public void setMagazzino(Magazzino magazzino) {
        this.magazzino = magazzino;
    }
}