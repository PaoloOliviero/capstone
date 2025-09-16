package paolooliviero.capstone.entities;

import jakarta.persistence.*;
import lombok.ToString;

@Entity
@ToString
public class StoricoPercorrenze {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Spedizione spedizione;
    @ManyToOne
    private MezzoDiTrasporto mezzoDiTrasporto;
    @ManyToOne
    private Autista autista;
    private double tempoEffettivoTratta;

    public StoricoPercorrenze() {
    }

    public StoricoPercorrenze(double tempoEffettivoTratta) {
        this.tempoEffettivoTratta = tempoEffettivoTratta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Spedizione getSpedizione() {
        return spedizione;
    }

    public void setSpedizione(Spedizione spedizione) {
        this.spedizione = spedizione;
    }

    public double getTempoEffettivoTratta() {
        return tempoEffettivoTratta;
    }

    public void setTempoEffettivoTratta(double tempoEffettivoTratta) {
        this.tempoEffettivoTratta = tempoEffettivoTratta;
    }
}
