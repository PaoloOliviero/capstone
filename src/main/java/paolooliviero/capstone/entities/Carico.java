package paolooliviero.capstone.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private MezzoDiTrasporto mezzoDiTrasporto;
    @OneToMany
    private List<ProdottoOrdinatoCliente> prodottiOrdinati;
    @OneToMany (mappedBy = "carico", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProdottoMagazzino> prodottoMagazzino;



    public Carico ()
    {}

    public Carico(String descrizione, double volume, String tipoMerce) {
        this.descrizione = descrizione;
        this.volume = volume;
        this.categoria = tipoMerce;
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

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<OrdineCliente> getOrdineCliente() {
        return ordineCliente;
    }

    public void setOrdineCliente(List<OrdineCliente> ordineCliente) {
        this.ordineCliente = ordineCliente;
    }

    public MezzoDiTrasporto getMezzoDiTrasporto() {
        return mezzoDiTrasporto;
    }

    public void setMezzoDiTrasporto(MezzoDiTrasporto mezzoDiTrasporto) {
        this.mezzoDiTrasporto = mezzoDiTrasporto;
    }

    public List<ProdottoOrdinatoCliente> getProdottiOrdinati() {
        return prodottiOrdinati;
    }

    public void setProdottiOrdinati(List<ProdottoOrdinatoCliente> prodottiOrdinati) {
        this.prodottiOrdinati = prodottiOrdinati;
    }

    public List<ProdottoMagazzino> getProdottoMagazzino() {
        return prodottoMagazzino;
    }

    public void setProdottoMagazzino(List<ProdottoMagazzino> prodottoMagazzino) {
        this.prodottoMagazzino = prodottoMagazzino;
    }
}


