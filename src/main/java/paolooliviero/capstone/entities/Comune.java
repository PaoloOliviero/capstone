package paolooliviero.capstone.entities;

import jakarta.persistence.*;
import lombok.ToString;

@Entity
@ToString
@Table(name = "comuni")
public class Comune {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String denominazioneInItaliano;
    private String nomeComune;

    @ManyToOne
    private Provincia provinciaRef;

    public Comune() {
    }

    public Comune(String denominazioneInItaliano, String nomeComune, Provincia provinciaRef) {
        this.denominazioneInItaliano = denominazioneInItaliano;
        this.nomeComune = nomeComune;
        this.provinciaRef = provinciaRef;
    }

    @Override
    public String toString() {
        return "Comune{" +
                "id=" + id +
                ", denominazioneInItaliano='" + denominazioneInItaliano + '\'' +
                ", nomeComune='" + nomeComune + '\'' +
                ", provincia= " + provinciaRef.getProvincia() +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getDenominazioneInItaliano() {
        return denominazioneInItaliano;
    }

    public void setDenominazioneInItaliano(String denominazioneInItaliano) {
        this.denominazioneInItaliano = denominazioneInItaliano;
    }

    public String getNomeComune() {
        return nomeComune;
    }

    public void setNomeComune(String nomeComune) {
        this.nomeComune = nomeComune;
    }

    public Provincia getProvinciaRef() {
        return provinciaRef;
    }

    public void setProvinciaRef(Provincia provinciaRef) {
        this.provinciaRef = provinciaRef;
    }
}