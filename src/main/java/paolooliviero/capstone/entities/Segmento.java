package paolooliviero.capstone.entities;

import jakarta.persistence.*;
import paolooliviero.capstone.enums.TipologiaSegmento;

import java.util.List;

@Entity
public class Segmento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String criterio;

    @OneToMany(mappedBy = "segmento")
    private List<Cliente> clienti;

    @Enumerated(EnumType.STRING)
    private TipologiaSegmento tipologiaSegmento;

    public Segmento() {
    }

    public Segmento(String nome, String criterio, List<Cliente> clienti, TipologiaSegmento tipologiaSegmento) {
        this.nome = nome;
        this.criterio = criterio;
        this.clienti = clienti;
        this.tipologiaSegmento = tipologiaSegmento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Cliente> getClienti() {
        return clienti;
    }

    public void setClienti(List<Cliente> clienti) {
        this.clienti = clienti;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public TipologiaSegmento getTipologiaSegmento() {
        return tipologiaSegmento;
    }

    public void setTipologiaSegmento(TipologiaSegmento tipologiaSegmento) {
        this.tipologiaSegmento = tipologiaSegmento;
    }
}


