package paolooliviero.capstone.entities;


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
    private double weight;
    private String categoria;
    @OneToMany(mappedBy = "prodotto")
    private List<ProdottoMagazzino> magazziniAssociati;
    @OneToMany(mappedBy = "prodotto")
    private List<MovimentoMagazzino> movimentoMagazzino;
    @ManyToOne
    @JoinColumn(name="ordineCliente_id")
    private OrdineCliente ordineCliente;


    public Prodotto() {}

    public Prodotto(double prezzoUnitario, String nome, double weight, String categoria, List<ProdottoMagazzino> magazziniAssociati, List<MovimentoMagazzino> movimentoMagazzino, OrdineCliente ordineCliente) {
        this.prezzoUnitario = prezzoUnitario;
        this.nome = nome;
        this.weight = weight;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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