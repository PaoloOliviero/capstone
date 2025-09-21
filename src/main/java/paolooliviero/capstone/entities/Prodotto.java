package paolooliviero.capstone.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;

@Entity
@ToString
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private double prezzoUnitario;
    private double volume;
    private String categoria;
    @OneToMany(mappedBy = "prodotto")
    @JsonIgnore
    private List<ProdottoMagazzino> magazziniAssociati;
    @OneToMany(mappedBy = "prodotto")
    @JsonIgnore
    private List<MovimentoMagazzino> movimentoMagazzino;
    @ManyToOne
    @JoinColumn(name="ordineCliente_id")
    private OrdineCliente ordineCliente;



    public Prodotto() {}

    public Prodotto(double prezzoUnitario, String nome, double volume, String categoria, List<ProdottoMagazzino> magazziniAssociati, List<MovimentoMagazzino> movimentoMagazzino, OrdineCliente ordineCliente) {
        this.prezzoUnitario = prezzoUnitario;
        this.nome = nome;
        this.volume = volume;
        this.categoria = categoria;
        this.magazziniAssociati = magazziniAssociati;
        this.movimentoMagazzino = movimentoMagazzino;
        this.ordineCliente = ordineCliente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrezzoUnitario() {
        return prezzoUnitario;
    }

    public void setPrezzoUnitario(double prezzoUnitario) {
        this.prezzoUnitario = prezzoUnitario;
    }

    public Double getVolume() {
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

    public List<ProdottoMagazzino> getMagazziniAssociati() {
        return magazziniAssociati;
    }

    public void setMagazziniAssociati(List<ProdottoMagazzino> magazziniAssociati) {
        this.magazziniAssociati = magazziniAssociati;
    }

    public List<MovimentoMagazzino> getMovimentoMagazzino() {
        return movimentoMagazzino;
    }

    public void setMovimentoMagazzino(List<MovimentoMagazzino> movimentoMagazzino) {
        this.movimentoMagazzino = movimentoMagazzino;
    }

    public OrdineCliente getOrdineCliente() {
        return ordineCliente;
    }

    public void setOrdineCliente(OrdineCliente ordineCliente) {
        this.ordineCliente = ordineCliente;
    }




}