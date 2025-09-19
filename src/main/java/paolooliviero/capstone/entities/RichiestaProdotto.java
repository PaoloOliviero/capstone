package paolooliviero.capstone.entities;

import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@ToString
public class RichiestaProdotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantitaRichiesta;

    private LocalDate dataRichiesta;

    private String motivazione;

    @ManyToOne
    @JoinColumn(name = "prodotto_magazzino_id")
    private ProdottoMagazzino prodottoMagazzino;

    @ManyToOne
    @JoinColumn(name = "magazzino_id")
    private Magazzino magazzino;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente richiestoDa;

    @OneToOne
    @JoinColumn(name = "movimento_id")
    private MovimentoMagazzino movimentoAssociato;

    public int getQuantitaRichiesta() {
        return quantitaRichiesta;
    }

    public void setQuantitaRichiesta(int quantitaRichiesta) {
        this.quantitaRichiesta = quantitaRichiesta;
    }

    public LocalDate getDataRichiesta() {
        return dataRichiesta;
    }

    public void setDataRichiesta(LocalDate dataRichiesta) {
        this.dataRichiesta = dataRichiesta;
    }

    public String getMotivazione() {
        return motivazione;
    }

    public void setMotivazione(String motivazione) {
        this.motivazione = motivazione;
    }

    public ProdottoMagazzino getProdottoMagazzino() {
        return prodottoMagazzino;
    }

    public void setProdottoMagazzino(ProdottoMagazzino prodottoMagazzino) {
        this.prodottoMagazzino = prodottoMagazzino;
    }

    public Magazzino getMagazzino() {
        return magazzino;
    }

    public void setMagazzino(Magazzino magazzino) {
        this.magazzino = magazzino;
    }

    public Utente getRichiestoDa() {
        return richiestoDa;
    }

    public void setRichiestoDa(Utente richiestoDa) {
        this.richiestoDa = richiestoDa;
    }

    public MovimentoMagazzino getMovimentoAssociato() {
        return movimentoAssociato;
    }

    public void setMovimentoAssociato(MovimentoMagazzino movimentoAssociato) {
        this.movimentoAssociato = movimentoAssociato;
    }
}




