package paolooliviero.capstone.entities;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@ToString
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    private String nome;
    private String cognome;
    private String password;
    private String avatar;

    @ManyToMany
    @JoinTable(
            name = "utente_ruolo",
            joinColumns = @JoinColumn(name = "utente_id"),
            inverseJoinColumns = @JoinColumn(name = "ruolo_id")
    )
    private List<Ruolo> ruoli = new ArrayList<>();

    public Utente(String username, String email, String nome, String cognome, String password, String avatar, List<Ruolo> ruoli) {
        this.username = username;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
        this.avatar = avatar;
        this.ruoli = ruoli;
    }

    public Utente() {

    }

    public Long getId() {
        return id;
    }

    public List<Ruolo> getRuoli() {
        return ruoli;
    }

    public void setRuoli(List<Ruolo> ruoli) {
        this.ruoli = ruoli;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", ruoli=" + ruoli +
                '}';
    }
}


