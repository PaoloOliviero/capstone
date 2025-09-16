package paolooliviero.capstone.entities;

import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@ToString
public class OrdineCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String statoOrdine;
    private LocalDate dataOrdine;
    private String indirizzoSpedizione;
    private String metodopagamento;
    @OneToOne
    @JoinColumn(name = "fattura_id")
    private Fattura fattura;
    @OneToOne
    @JoinColumn(name = "spedizione_id")
    private Spedizione spedizione;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "carico_id")
    private Carico carico;
    @OneToMany(mappedBy = "ordinecliente")
    private List<Prodotto> prodotto;

    public OrdineCliente() {
    }

    public OrdineCliente(String statoOrdine, LocalDate dataOrdine, String indirizzoSpedizione, String metodopagamento, Fattura fattura, Spedizione spedizione, Cliente cliente, Carico carico, List<Prodotto> prodotto) {
        this.statoOrdine = statoOrdine;
        this.dataOrdine = dataOrdine;
        this.indirizzoSpedizione = indirizzoSpedizione;
        this.metodopagamento = metodopagamento;
        this.fattura = fattura;
        this.spedizione = spedizione;
        this.cliente = cliente;
        this.carico = carico;
        this.prodotto = prodotto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatoOrdine() {
        return statoOrdine;
    }

    public void setStatoOrdine(String statoOrdine) {
        this.statoOrdine = statoOrdine;
    }

    public LocalDate getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(LocalDate dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public String getIndirizzoSpedizione() {
        return indirizzoSpedizione;
    }

    public void setIndirizzoSpedizione(String indirizzoSpedizione) {
        this.indirizzoSpedizione = indirizzoSpedizione;
    }

    public String getMetodopagamento() {
        return metodopagamento;
    }

    public void setMetodopagamento(String metodopagamento) {
        this.metodopagamento = metodopagamento;
    }

    public Fattura getFattura() {
        return fattura;
    }

    public void setFattura(Fattura fattura) {
        this.fattura = fattura;
    }

    public Spedizione getSpedizione() {
        return spedizione;
    }

    public void setSpedizione(Spedizione spedizione) {
        this.spedizione = spedizione;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carico getCarico() {
        return carico;
    }

    public void setCarico(Carico carico) {
        this.carico = carico;
    }

    public List<Prodotto> getProdotto() {
        return prodotto;
    }

    public void setProdotto(List<Prodotto> prodotto) {
        this.prodotto = prodotto;
    }
}


