package paolooliviero.capstone.entities;

import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@ToString
public class Fattura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate data;
    private double importo;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private StatoFattura statoFattura;

    public Fattura () {

    }

    public Fattura(LocalDate data, double importo) {
        this.data = data;
        this.importo = importo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
