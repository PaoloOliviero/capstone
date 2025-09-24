package paolooliviero.capstone.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Notifica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titolo;
    private String descrizione;
    private boolean visualizzata;
    private LocalDateTime data;

    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Utente utenteDestinatario;
    @ManyToOne
    private OrdineCliente OrdineCliente;


    public Notifica(String titolo, String descrizione, boolean visualizzata, LocalDateTime data, Cliente cliente, Utente utenteDestinatario) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.visualizzata = visualizzata;
        this.data = data;
        this.cliente = cliente;
        this.utenteDestinatario = utenteDestinatario;

    }

    public Notifica() {}

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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public boolean isVisualizzata() {
        return visualizzata;
    }

    public void setVisualizzata(boolean visualizzata) {
        this.visualizzata = visualizzata;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utente getUtenteDestinatario() {
        return utenteDestinatario;
    }

    public void setUtenteDestinatario(Utente utenteDestinatario) {
        this.utenteDestinatario = utenteDestinatario;
    }

    public OrdineCliente getOrdineCliente() {
        return OrdineCliente;
    }

    public void setOrdineCliente(OrdineCliente ordineCliente) {
        OrdineCliente = ordineCliente;
    }
}

