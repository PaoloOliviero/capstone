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
    private double categoria;
    @OneToMany(mappedBy = "prodotto")
    private List<ProdottoMagazzino> magazziniAssociati;
    @OneToMany(mappedBy = "prodotto")
    private List<MovimentoMagazzino> movimentoMagazzino;

    public Prodotto() {}

    public Prodotto(String nome, double prezzoUnitario, double weight, double categoria, List<ProdottoMagazzino> magazziniAssociati, List<MovimentoMagazzino> movimentoMagazzino) {
        this.nome = nome;
        this.prezzoUnitario = prezzoUnitario;
        this.weight = weight;
        this.categoria = categoria;
        this.magazziniAssociati = magazziniAssociati;
        this.movimentoMagazzino = movimentoMagazzino;
    }
}