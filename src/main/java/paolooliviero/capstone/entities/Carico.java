package paolooliviero.capstone.entities;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;

@Entity
@ToString
public class Carico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String descrizione;
    private double volume;
    private String categoria;
    @OneToMany(mappedBy = "carico")
    private List<OrdineCliente> ordineCliente;
    @ManyToOne
    @JoinColumn(name = "mezzo_di_trasporto_id")
    private MezzoDiTrasporto mezzoDiTrasporto;
    @OneToMany
    private List<ProdottoOrdinatoCliente> prodottiOrdinati;
    @OneToMany
    private List<Prodotto> prodotti;



    public Carico ()
    {}

    public Carico(String descrizione, double volume, String tipoMerce) {
        this.descrizione = descrizione;
        this.volume = volume;
        this.categoria = categoria;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getTipoMerce() {
        return categoria;
    }

    public void setTipoMerce(String tipoMerce) {
        this.categoria = tipoMerce;
    }

    public MezzoDiTrasporto getMezzoDiTrasporto() {
        return mezzoDiTrasporto;
    }

    public void setMezzoDiTrasporto(MezzoDiTrasporto mezzoDiTrasporto) {
        this.mezzoDiTrasporto = mezzoDiTrasporto;
    }

    public List<OrdineCliente> getOrdineCliente() {
        return ordineCliente;
    }

    public void setOrdineCliente(List<OrdineCliente> ordineCliente) {
        this.ordineCliente = ordineCliente;
    }

    public String getCategoria() {
        return categoria;
    }

    public List<ProdottoOrdinatoCliente> getProdottiOrdinati() {
        return prodottiOrdinati;
    }

    public void setProdottiOrdinati(List<ProdottoOrdinatoCliente> prodottiOrdinati) {
        this.prodottiOrdinati = prodottiOrdinati;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }
}


