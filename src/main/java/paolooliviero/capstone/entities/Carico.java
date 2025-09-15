package paolooliviero.capstone.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.ToString;

@Entity
@ToString
public class Carico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String descrizione;
    private double volume;
    private String tipoMerce;

    public Carico ()
    {}

    public Carico(String descrizione, double volume, String tipoMerce) {
        this.descrizione = descrizione;
        this.volume = volume;
        this.tipoMerce = tipoMerce;
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
        return tipoMerce;
    }

    public void setTipoMerce(String tipoMerce) {
        this.tipoMerce = tipoMerce;
    }
}


