package paolooliviero.capstone.entities;

import jakarta.persistence.*;
import lombok.ToString;
import paolooliviero.capstone.enums.StatoOrdine;

import java.time.LocalDate;
import java.util.List;

@Entity
@ToString
public class OrdineCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private StatoOrdine statoOrdine;
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
    @OneToMany(mappedBy = "ordineCliente")
    private List<Prodotto> prodotto;
    @OneToOne(mappedBy = "ordineCliente")
    private RichiestaProdotto richiestaProdotto;
    @OneToMany(mappedBy = "ordineCliente")
    private List<ProdottoOrdinatoCliente> prodottoOrdinatoCliente;


    public OrdineCliente() {}

    public OrdineCliente(StatoOrdine statoOrdine, LocalDate dataOrdine, String indirizzoSpedizione, String metodopagamento, Fattura fattura, Spedizione spedizione, Cliente cliente, Carico carico, List<Prodotto> prodotto) {
        this.statoOrdine = statoOrdine;
        this.dataOrdine = dataOrdine;
        this.indirizzoSpedizione = indirizzoSpedizione;
        this.metodopagamento = metodopagamento;
        this.fattura = fattura;
        this.spedizione = spedizione;
        this.cliente = cliente;
        this.carico = carico;
        this.prodotto = prodotto;
        this.richiestaProdotto = richiestaProdotto;

    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public RichiestaProdotto getRichiestaProdotto() {
        return richiestaProdotto;
    }

    public void setRichiestaProdotto(RichiestaProdotto richiestaProdotto) {
        this.richiestaProdotto = richiestaProdotto;
    }

    public void setStatoOrdine(StatoOrdine statoOrdine) {
        this.statoOrdine = statoOrdine;
    }

    public StatoOrdine getStatoOrdine() {
        return statoOrdine;
    }

    public List<ProdottoOrdinatoCliente> getProdottoOrdinatoCliente() {
        return prodottoOrdinatoCliente;
    }

    public void setProdottoOrdinatoCliente(List<ProdottoOrdinatoCliente> prodottoOrdinatoCliente) {
        this.prodottoOrdinatoCliente = prodottoOrdinatoCliente;
    }
}


