package paolooliviero.capstone.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titolo;
    private String descrizione;
    private LocalDateTime dataCreazione;

    @ManyToOne
    private OrdineCliente ordineCliente;

    @ManyToOne
    private Utente utenteCreatore;

    public Ticket(String titolo, String descrizione, LocalDateTime dataCreazione, OrdineCliente ordineCliente, Utente utenteCreatore) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.dataCreazione = dataCreazione;
        this.ordineCliente = ordineCliente;
        this.utenteCreatore = utenteCreatore;
    }

    public Ticket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDateTime getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDateTime dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public OrdineCliente getOrdineCliente() {
        return ordineCliente;
    }

    public void setOrdineCliente(OrdineCliente ordineCliente) {
        this.ordineCliente = ordineCliente;
    }

    public Utente getUtenteCreatore() {
        return utenteCreatore;
    }

    public void setUtenteCreatore(Utente utenteCreatore) {
        this.utenteCreatore = utenteCreatore;
    }
}

