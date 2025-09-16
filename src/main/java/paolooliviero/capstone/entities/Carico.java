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
    @JoinColumn(name = "mezzoDiTrasporto_id")
    private MezzoDiTrasporto mezzoDiTrasporto;

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
}


