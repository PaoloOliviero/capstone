package paolooliviero.capstone.entities;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Table(name = "province")
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String sigla;
    private String provincia;
    private String regione;

    @OneToMany(mappedBy = "provinciaRef")
    private List<Comune> tuttiComuni = new ArrayList<>();


    public Provincia(String sigla, String provincia, String regione) {
        this.sigla = sigla;
        this.provincia = provincia;
        this.regione = regione;
    }

    public Provincia() {
    }

    public long getId() {
        return id;
    }


    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    @Override
    public String toString() {
        return "Provincia{" +
                "id=" + id +
                ", sigla='" + sigla + '\'' +
                ", provincia='" + provincia + '\'' +
                ", regione='" + regione + '\'' +
                '}';
    }
}