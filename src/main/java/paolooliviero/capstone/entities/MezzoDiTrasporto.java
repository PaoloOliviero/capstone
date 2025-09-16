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
    @OneToMany(mappedBy = "mezzoditrasporto")
    private List<Carico> carichi;
    @ManyToOne
    @JoinColumn(name = "autista_id")
    private Autista autista;

}

