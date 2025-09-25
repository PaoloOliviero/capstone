package paolooliviero.capstone.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@ToString
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ragioneSociale;
    private String partitaIva;
    private String email;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private Double fatturatoAnnuale;
    private String telefonoContatto;
    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Fattura> fatture;
    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<SedeCliente> sedeCliente;
    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<OrdineCliente> ordineCliente;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "segmento_id")
    private Segmento segmento;


    public Cliente ()
    {}

    public Cliente(String ragioneSociale, String partitaIva, LocalDate dataInserimento, String email, LocalDate dataUltimoContatto, Double fatturatoAnnuale, String telefonoContatto) {
        this.ragioneSociale = ragioneSociale;
        this.partitaIva = partitaIva;
        this.dataInserimento = dataInserimento;
        this.email = email;
        this.dataUltimoContatto = dataUltimoContatto;
        this.fatturatoAnnuale = fatturatoAnnuale;
        this.telefonoContatto = telefonoContatto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataInserimento() {
        return dataInserimento;
    }

    public void setDataInserimento(LocalDate dataInserimento) {
        this.dataInserimento = dataInserimento;
    }

    public Double getFatturatoAnnuale() {
        return fatturatoAnnuale;
    }

    public void setFatturatoAnnuale(Double fatturatoAnnuale) {
        this.fatturatoAnnuale = fatturatoAnnuale;
    }

    public LocalDate getDataUltimoContatto() {
        return dataUltimoContatto;
    }

    public void setDataUltimoContatto(LocalDate dataUltimoContatto) {
        this.dataUltimoContatto = dataUltimoContatto;
    }

    public String getTelefonoContatto() {
        return telefonoContatto;
    }

    public void setTelefonoContatto(String telefonoContatto) {
        this.telefonoContatto = telefonoContatto;
    }

    public List<Fattura> getFatture() {
        return fatture;
    }

    public void setFatture(List<Fattura> fatture) {
        this.fatture = fatture;
    }

    public List<SedeCliente> getSedeCliente() {
        return sedeCliente;
    }

    public void setSedeCliente(List<SedeCliente> sedeCliente) {
        this.sedeCliente = sedeCliente;
    }

    public List<OrdineCliente> getOrdineCliente() {
        return ordineCliente;
    }

    public void setOrdineCliente(List<OrdineCliente> ordineCliente) {
        this.ordineCliente = ordineCliente;
    }

    public Segmento getSegmento() {
        return segmento;
    }

    public void setSegmento(Segmento segmento) {
        this.segmento = segmento;
    }

}



