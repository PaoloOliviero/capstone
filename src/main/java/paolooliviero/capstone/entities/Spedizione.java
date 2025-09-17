package paolooliviero.capstone.entities;

import jakarta.persistence.*;
import lombok.ToString;

@Entity
@ToString
public class Spedizione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String partenza;
    private String arrivo;
    private Double tempoPrevisto;
    private Double tempoEffettivo;
    @ManyToOne
    private Magazzino magazzinoPartenza;


    public Spedizione () {}

    public Spedizione(String partenza, String arrivo, Double tempoPrevisto, Double tempoEffettivo) {
        this.partenza = partenza;
        this.arrivo = arrivo;
        this.tempoPrevisto = tempoPrevisto;
        this.tempoEffettivo = tempoEffettivo;
    }

    public String getPartenza() {
        return partenza;
    }

    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }

    public String getArrivo() {
        return arrivo;
    }

    public void setArrivo(String arrivo) {
        this.arrivo = arrivo;
    }

    public Double getTempoPrevisto() {
        return tempoPrevisto;
    }

    public void setTempoPrevisto(Double tempoPrevisto) {
        this.tempoPrevisto = tempoPrevisto;
    }

    public Double getTempoEffettivo() {
        return tempoEffettivo;
    }

    public void setTempoEffettivo(Double tempoEffettivo) {
        this.tempoEffettivo = tempoEffettivo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
