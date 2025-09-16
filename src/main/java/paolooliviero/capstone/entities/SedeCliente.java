package paolooliviero.capstone.entities;

import jakarta.persistence.*;
import lombok.ToString;
import paolooliviero.capstone.enums.TipoSede;

@Entity
@ToString
@Table(name = "sedi")
public class SedeCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private TipoSede type;

    @OneToOne
    @JoinColumn(name = "id_address")
    private Indirizzo indirizzo;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public SedeCliente() {
    }

    public SedeCliente(TipoSede type, Indirizzo indirizzo, Cliente cliente) {
        this.type = type;
        this.indirizzo = indirizzo;
        this.cliente = cliente;
    }

    public long getId() {
        return id;
    }

    public TipoSede getType() {
        return type;
    }

    public void setType(TipoSede type) {
        this.type = type;
    }

    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Sede{" +
                "id=" + id +
                ", type=" + type +
                ", indirizzo=" + indirizzo +
                ", cliente=" + cliente +
                '}';
    }
}