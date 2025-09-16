package paolooliviero.capstone.entities;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;

@Entity
@ToString
public class Autista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cognome;
    private boolean disponibile;
    private String telefono;
    @OneToMany(mappedBy = "autista")
    private List<MezzoDiTrasporto> mezzoDiTrasporto;

    public Autista ()
    {}

    public Autista(String nome, String cognome, boolean disponibile, String telefono) {
        this.nome = nome;
        this.cognome = cognome;
        this.disponibile = disponibile;
        this.telefono = telefono;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public boolean isDisponibile() {
        return disponibile;
    }

    public void setDisponibile(boolean disponibile) {
        this.disponibile = disponibile;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

