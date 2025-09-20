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
    @ManyToOne
    @JoinColumn(name = "autista_id")
    private Autista autista;
    @OneToMany(mappedBy = "mezzoDiTrasporto")
    private List<MovimentoMagazzino> movimento;
    @ManyToOne
    @JoinColumn(name = "magazzino_partenza_id")
    private Magazzino magazzino;
    @OneToMany(mappedBy = "mezzoDiTrasporto", cascade = CascadeType.ALL)
    private List<Carico> carichi;

    public MezzoDiTrasporto() {}

    public MezzoDiTrasporto(int capienzaMassima, StatoMezzo statoMezzo, List<Carico> carichi, Autista autista) {
        this.capienzaMassima = capienzaMassima;
        this.statoMezzo = statoMezzo;
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


    public Autista getAutista() {
        return autista;
    }

    public void setAutista(Autista autista) {
        this.autista = autista;
    }

    public List<MovimentoMagazzino> getMovimento() {
        return movimento;
    }

    public void setMovimento(List<MovimentoMagazzino> movimento) {
        this.movimento = movimento;
    }

    public Magazzino getMagazzino() {
        return magazzino;
    }

    public void setMagazzino(Magazzino magazzino) {
        this.magazzino = magazzino;
    }

    public List<Carico> getCarichi() {
        return carichi;
    }

    public void setCarichi(List<Carico> carichi) {
        this.carichi = carichi;
    }
}

