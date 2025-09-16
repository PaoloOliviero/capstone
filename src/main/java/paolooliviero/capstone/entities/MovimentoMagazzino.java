package paolooliviero.capstone.entities;

import jakarta.persistence.*;
import lombok.ToString;


@Entity
@ToString
public class MovimentoMagazzino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double quantity;
    @ManyToOne
    @JoinColumn(name = "prodotto_id")
    private Prodotto prodotto;
    @ManyToOne
    private ProdottoMagazzino prodottoMagazzino;
    @ManyToOne
    private Utente registratoDa;

    public MovimentoMagazzino(double quantity, Prodotto prodotto, Utente registratoDa, ProdottoMagazzino prodottoMagazzino) {
        this.quantity = quantity;
        this.prodotto = prodotto;
        this.registratoDa = registratoDa;
        this.prodottoMagazzino = prodottoMagazzino;
    }

    public MovimentoMagazzino () {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public ProdottoMagazzino getProdottoMagazzino() {
        return prodottoMagazzino;
    }

    public void setProdottoMagazzino(ProdottoMagazzino prodottoMagazzino) {
        this.prodottoMagazzino = prodottoMagazzino;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public Utente getRegistratoDa() {
        return registratoDa;
    }

    public void setRegistratoDa(Utente registratoDa) {
        this.registratoDa = registratoDa;
    }
}
