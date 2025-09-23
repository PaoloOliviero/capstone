package paolooliviero.capstone.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

    @ManyToOne (fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "prodotto_magazzino_id")
    private ProdottoMagazzino prodottoMagazzino;

    @ManyToOne (fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "magazzino_id")
    private Magazzino magazzino;

    @ManyToOne (fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "utente_id")
    private Utente richiestoDa;

    @OneToOne (fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "movimento_id")
    private MovimentoMagazzino movimentoAssociato;

    @OneToOne (fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "ordinecliente_id")
    private OrdineCliente ordineCliente;

    @OneToOne (fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "eventomancanzaprodotto_id")
    private EventoMancanzaProdotto eventoMancanzaProdotto;


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

    public OrdineCliente getOrdineCliente() {
        return ordineCliente;
    }

    public void setOrdineCliente(OrdineCliente ordineCliente) {
        this.ordineCliente = ordineCliente;
    }

    public EventoMancanzaProdotto getEventoMancanzaProdotto() {
        return eventoMancanzaProdotto;
    }

    public void setEventoMancanzaProdotto(EventoMancanzaProdotto eventoMancanzaProdotto) {
        this.eventoMancanzaProdotto = eventoMancanzaProdotto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}




