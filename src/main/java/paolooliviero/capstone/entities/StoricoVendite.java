package paolooliviero.capstone.entities;

import jakarta.persistence.*;
import lombok.ToString;

@Entity
@ToString
public class StoricoVendite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Prodotto prodotto;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private OrdineCliente ordineCliene;

    public StoricoVendite() {
    }

    public StoricoVendite(Prodotto prodotto, Cliente cliente, OrdineCliente ordineCliene) {
        this.prodotto = prodotto;
        this.cliente = cliente;
        this.ordineCliene = ordineCliene;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public OrdineCliente getOrdineCliene() {
        return ordineCliene;
    }

    public void setOrdineCliene(OrdineCliente ordineCliene) {
        this.ordineCliene = ordineCliene;
    }
}

