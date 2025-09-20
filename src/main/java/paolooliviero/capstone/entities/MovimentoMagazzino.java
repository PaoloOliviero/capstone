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
    @JoinColumn(name = "magazzino_id")
    private Magazzino magazzino;
    @ManyToOne
    private ProdottoMagazzino prodottoMagazzino;
    @ManyToOne
    private Utente registratoDa;
    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private MezzoDiTrasporto mezzoDiTrasporto;
    @OneToOne(mappedBy = "movimentoAssociato")
    private RichiestaProdotto richiestaOrigine;
    @ManyToOne
    private Prodotto prodotto;


    public MovimentoMagazzino() {}

    public MovimentoMagazzino(double quantity, Prodotto prodotto, Magazzino magazzino, ProdottoMagazzino prodottoMagazzino, Utente registratoDa) {
        this.quantity = quantity;
        this.magazzino = magazzino;
        this.prodottoMagazzino = prodottoMagazzino;
        this.registratoDa = registratoDa;

    }

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


    public Magazzino getMagazzino() {
        return magazzino;
    }

    public void setMagazzino(Magazzino magazzino) {
        this.magazzino = magazzino;
    }

    public ProdottoMagazzino getProdottoMagazzino() {
        return prodottoMagazzino;
    }

    public void setProdottoMagazzino(ProdottoMagazzino prodottoMagazzino) {
        this.prodottoMagazzino = prodottoMagazzino;
    }

    public Utente getRegistratoDa() {
        return registratoDa;
    }

    public void setRegistratoDa(Utente registratoDa) {
        this.registratoDa = registratoDa;
    }

    public MezzoDiTrasporto getMezzoDiTrasporto() {
        return mezzoDiTrasporto;
    }

    public void setMezzoDiTrasporto(MezzoDiTrasporto mezzoDiTrasporto) {
        this.mezzoDiTrasporto = mezzoDiTrasporto;
    }

    public RichiestaProdotto getRichiestaOrigine() {
        return richiestaOrigine;
    }

    public void setRichiestaOrigine(RichiestaProdotto richiestaOrigine) {
        this.richiestaOrigine = richiestaOrigine;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }
}

