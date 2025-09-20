package paolooliviero.capstone.entities;

import jakarta.persistence.*;

    @Entity
    public class ProdottoOrdinatoCliente {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private Prodotto prodotto;

        @ManyToOne
        private OrdineCliente ordineCliente;

        private double quantita;

        public ProdottoOrdinatoCliente() {}

        public ProdottoOrdinatoCliente(Prodotto prodotto, OrdineCliente ordineCliente, double quantita) {
            this.prodotto = prodotto;
            this.ordineCliente = ordineCliente;
            this.quantita = quantita;
        }

        public Long getId() {
            return id;
        }

        public Prodotto getProdotto() {
            return prodotto;
        }

        public void setProdotto(Prodotto prodotto) {
            this.prodotto = prodotto;
        }

        public OrdineCliente getOrdineCliente() {
            return ordineCliente;
        }

        public void setOrdineCliente(OrdineCliente ordineCliente) {
            this.ordineCliente = ordineCliente;
        }

        public double getQuantita() {
            return quantita;
        }

        public void setQuantita(double quantita) {
            this.quantita = quantita;
        }
    }
