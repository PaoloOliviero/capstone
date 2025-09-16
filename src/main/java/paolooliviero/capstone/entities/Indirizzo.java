package paolooliviero.capstone.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.ToString;

@Entity
@ToString
@Table(name = "indirizzi")
public class Indirizzo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String via;
    private String civico;
    private String località;
    private String cap;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "comune_id")
    private Comune nome_comune;

    public Indirizzo(String via, String civico, String località, String cap, Comune nome_comune) {
        this.via = via;
        this.civico = civico;
        this.località = località;
        this.cap = cap;
        this.nome_comune = nome_comune;
    }

    public Indirizzo() {
    }

    public long getId() {
        return id;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCivico() {
        return civico;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }

    public String getLocalità() {
        return località;
    }

    public void setLocalità(String località) {
        this.località = località;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public Comune getNome_comune() {
        return nome_comune;
    }

    public void setNome_comune(Comune nome_comune) {
        this.nome_comune = nome_comune;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", via='" + via + '\'' +
                ", civico='" + civico + '\'' +
                ", località='" + località + '\'' +
                ", cap='" + cap + '\'' +
                ", nome_comune=" + nome_comune +
                '}';
    }
}
