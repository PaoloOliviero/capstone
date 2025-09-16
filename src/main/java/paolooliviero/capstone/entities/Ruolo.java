package paolooliviero.capstone.entities;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Table(name = "ruoli")
public class Ruolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToMany(mappedBy = "ruoli")
    private List<Utente> utenti = new ArrayList<>();

    public Ruolo() {
    }

    public Ruolo(String nome, List<Utente> utenti) {
        this.nome = nome;
        this.utenti = utenti;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Utente> getUtenti() {
        return utenti;
    }

    public void setUtenti(List<Utente> utenti) {
        this.utenti = utenti;
    }

    @Override
    public String toString() {
        return "Ruolo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", utenti=" + utenti +
                '}';
    }
}
