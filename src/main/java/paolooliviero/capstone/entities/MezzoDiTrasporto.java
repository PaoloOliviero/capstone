package paolooliviero.capstone.entities;

import jakarta.persistence.*;
import lombok.ToString;
import paolooliviero.capstone.enums.StatoMezzo;

import java.util.List;

@Entity
@ToString
public class MezzoDiTrasporto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int capienzaMassima;
    private StatoMezzo statoMezzo;
    @OneToMany(mappedBy = "mezzoDiTrasporto")
    private List<Carico> carichi;
    @ManyToOne
    @JoinColumn(name = "autista_id")
    private Autista autista;

    public MezzoDiTrasporto() {}

    public MezzoDiTrasporto(int capienzaMassima, StatoMezzo statoMezzo, List<Carico> carichi, Autista autista) {
        this.capienzaMassima = capienzaMassima;
        this.statoMezzo = statoMezzo;
        this.carichi = carichi;
        this.autista = autista;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCapienzaMassima() {
        return capienzaMassima;
    }

    public void setCapienzaMassima(int capienzaMassima) {
        this.capienzaMassima = capienzaMassima;
    }

    public StatoMezzo getStatoMezzo() {
        return statoMezzo;
    }

    public void setStatoMezzo(StatoMezzo statoMezzo) {
        this.statoMezzo = statoMezzo;
    }

    public List<Carico> getCarichi() {
        return carichi;
    }

    public void setCarichi(List<Carico> carichi) {
        this.carichi = carichi;
    }

    public Autista getAutista() {
        return autista;
    }

    public void setAutista(Autista autista) {
        this.autista = autista;
    }
}

