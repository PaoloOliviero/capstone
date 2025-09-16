package paolooliviero.capstone.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.ToString;

@Entity
@ToString
public class Spedizione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String partenza;
    private String arrivo;
    private String tempoPrevisto;
    private String tempoEffettivo;


    public Spedizione () {}

    public Spedizione(String partenza, String arrivo, String tempoPrevisto, String tempoEffettivo) {
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

    public String getTempoPrevisto() {
        return tempoPrevisto;
    }

    public void setTempoPrevisto(String tempoPrevisto) {
        this.tempoPrevisto = tempoPrevisto;
    }

    public String getTempoEffettivo() {
        return tempoEffettivo;
    }

    public void setTempoEffettivo(String tempoEffettivo) {
        this.tempoEffettivo = tempoEffettivo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
