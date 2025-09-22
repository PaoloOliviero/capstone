package paolooliviero.capstone.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@ToString
public class ProdottoMagazzino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double quantitaDisponibile;
    private LocalDate dataIngresso;

    @ManyToOne
    @JsonManagedReference
    private Prodotto prodotto;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "magazzino_id")
    private Magazzino magazzino;
    @ManyToOne
    @JoinColumn(name = "carico_id")
    private Carico carico;




    public ProdottoMagazzino() {
    }


    public ProdottoMagazzino( Double quantitaDisponibile, LocalDate dataIngresso, Carico carico, Magazzino magazzino, Prodotto prodotto) {
        this.prodotto = prodotto;
        this.quantitaDisponibile = quantitaDisponibile;
        this.dataIngresso = dataIngresso;
        this.carico = carico;
        this.magazzino = magazzino;

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


    public Double getQuantitaDisponibile() {
        return quantitaDisponibile;
    }

    public void setQuantitaDisponibile(Double quantitaDisponibile) {
        this.quantitaDisponibile = quantitaDisponibile;
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

    public Carico getCarico() {
        return carico;
    }

    public void setCarico(Carico carico) {
        this.carico = carico;
    }

}