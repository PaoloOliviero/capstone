package paolooliviero.capstone.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import paolooliviero.capstone.entities.*;
import paolooliviero.capstone.enums.StatoMezzo;
import paolooliviero.capstone.enums.StatoOrdine;
import paolooliviero.capstone.enums.TipologiaSegmento;
import paolooliviero.capstone.repositories.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Runner {

    @Bean
    public CommandLineRunner Database(
            MagazzinoRepository magazzinoRepo,
            ProdottoRepository prodottoRepo,
            ProdottoMagazzinoRepository prodottoMagazzinoRepo,
            MovimentoMagazzinoRepository movimentoRepo,
            SpedizioneRepository spedizioneRepo,
            UtenteRepository utenteRepo,
            CaricoRepository caricoRepo,
            MezzoDiTrasportoRepository mezzoDiTrasportoRepo,
            AutistaRepository autistaRepo,
            RuoloRepository ruoloRepo,
            ClienteRepository clienteRepo,
            FatturaRepository fatturaRepo,
            OrdineClienteRepository ordineClienteRepo,
            SegmentoRepository segmentoRepo,
            PasswordEncoder bcrypt,
            StatoFatturaRepository statoFatturaRepo
    ) {
        return args -> {


            Magazzino magazzinoPrincipale = new Magazzino();
            magazzinoPrincipale.setCapacitaTotale(1000);
            magazzinoPrincipale.setCapacitaOccupata(0);
            magazzinoRepo.save(magazzinoPrincipale);

            Ruolo ruoloUser = new Ruolo();
            ruoloUser.setNome("USER");
            ruoloRepo.save(ruoloUser);

            StatoFattura statoEmessa = new StatoFattura();
            statoEmessa.setNome("EMESSA");

            StatoFattura statoPagata = new StatoFattura();
            statoPagata.setNome("PAGATA");

            StatoFattura statoScaduta = new StatoFattura();
            statoScaduta.setNome("SCADUTA");

            statoFatturaRepo.save(statoEmessa);
            statoFatturaRepo.save(statoPagata);
            statoFatturaRepo.save(statoScaduta);


            Utente utente1 = new Utente();
            utente1.setEmail("paolo@example.com");
            utente1.setNome("Paolo");
            utente1.setCognome("Oliviero");
            utente1.setPassword(bcrypt.encode("password123"));
            utente1.setRuoli(List.of(ruoloUser));
            utente1.setMagazzino(magazzinoPrincipale);
            utenteRepo.save(utente1);

            Utente utente2 = new Utente();
            utente2.setEmail("mario@example.com");
            utente2.setNome("Mario");
            utente2.setCognome("Rossi");
            utente2.setPassword(bcrypt.encode("password456"));
            utente2.setRuoli(List.of(ruoloUser));
            utente2.setMagazzino(magazzinoPrincipale);
            utenteRepo.save(utente2);

            Cliente cliente1 = new Cliente();
            cliente1.setRagioneSociale("Azienda Berlusconi");
            cliente1.setEmail("alfa@azienda.com");
            cliente1.setTelefonoContatto("222222222");
            cliente1.setPartitaIva("IT12345678901");
            cliente1.setFatturatoAnnuale(1_000_000.0);
            cliente1.setDataInserimento(LocalDate.of(2023, 1, 1));
            cliente1.setDataUltimoContatto(LocalDate.of(2023, 9, 15));
            clienteRepo.save(cliente1);

            Cliente cliente2 = new Cliente();
            cliente2.setRagioneSociale("Azienda Salvini");
            cliente2.setEmail("beta@azienda.com");
            cliente2.setTelefonoContatto("111111111");
            cliente2.setPartitaIva("IT98765432109");
            cliente2.setFatturatoAnnuale(2_500_000.0);
            cliente2.setDataInserimento(LocalDate.of(2023, 2, 1));
            cliente2.setDataUltimoContatto(LocalDate.of(2023, 9, 20));
            clienteRepo.save(cliente2);

            List<Carico> carichi = new ArrayList<>();
            Spedizione ultimaSpedizione = null;

            Prodotto prodotto = new Prodotto();
            prodotto.setNome("Prodotto Test");
            prodotto.setPrezzoUnitario(99.99);
            prodotto.setCategoria("Elettronica");
            prodottoRepo.save(prodotto);

            for (int i = 1; i <= 5; i++) {
                Magazzino magazzinoOrigine = new Magazzino();
                magazzinoOrigine.setCapacitaTotale(1000);
                magazzinoOrigine.setCapacitaOccupata(100 * i);
                magazzinoRepo.save(magazzinoOrigine);

                Autista autista = new Autista();
                autista.setNome("Autista " + i);
                autista.setTelefono("33300000" + i);
                autistaRepo.save(autista);

                MezzoDiTrasporto mezzo = new MezzoDiTrasporto();
                mezzo.setCapienzaMassima(1000);
                mezzo.setStatoMezzo(StatoMezzo.IN_SERVIZIO);
                mezzo.setAutista(autista);
                mezzo.setMagazzino(magazzinoOrigine);
                mezzoDiTrasportoRepo.save(mezzo);

                Carico carico = new Carico();
                carico.setDescrizione("Carico " + i);
                carico.setVolume(500.0 + i);
                carico.setCategoria(i % 2 == 0 ? "Elettronica" : "Alimentari");
                carico.setMezzoDiTrasporto(mezzo);
                caricoRepo.save(carico);
                carichi.add(carico);

                ProdottoMagazzino pm = new ProdottoMagazzino();
                pm.setQuantitaDisponibile(50.0 * i);
                pm.setDataIngresso(LocalDate.now().minusDays(i));
                pm.setProdotto(prodotto);
                pm.setMagazzino(magazzinoOrigine);
                prodottoMagazzinoRepo.save(pm);

                pm.setMagazzino(magazzinoPrincipale);
                pm.setCarico(carico);
                prodottoMagazzinoRepo.save(pm);

                MovimentoMagazzino movimento = new MovimentoMagazzino();
                movimento.setQuantity(10.0 * i);
                movimento.setMagazzino(magazzinoPrincipale);
                movimento.setProdottoMagazzino(pm);
                movimento.setRegistratoDa(utente1);
                movimento.setDataRegistrazione(LocalDate.now());
                movimentoRepo.save(movimento);

                Spedizione spedizione = new Spedizione();
                spedizione.setPartenza("Milano");
                spedizione.setArrivo("Roma");
                spedizione.setTempoPrevisto(5.0 + i);
                spedizione.setTempoEffettivo(4.5 + i);
                spedizione.setMagazzino(magazzinoOrigine);
                spedizioneRepo.save(spedizione);

                ultimaSpedizione = spedizione;
            }

            Segmento segmento = new Segmento();
            segmento.setNome("Premium");
            segmento.setCriterio("Fatturato > 1M");
            segmento.setTipologiaSegmento(TipologiaSegmento.ALTA_PRIORITA);
            segmentoRepo.save(segmento);

            Fattura fattura = new Fattura();
            fattura.setImporto(1999.99);
            fattura.setDataEmissione(LocalDate.now().minusDays(1));
            fattura.setStatoFattura(statoEmessa);
            fattura.setCliente(cliente1);
            fatturaRepo.save(fattura);
            fatturaRepo.save(fattura);


            OrdineCliente ordineCliente = new OrdineCliente();
            ordineCliente.setDataOrdine(LocalDate.now());
            ordineCliente.setStatoOrdine(StatoOrdine.APPROVATO);
            ordineCliente.setIndirizzoSpedizione("Via delle Industrie 42");
            ordineCliente.setCliente(cliente1);
            ordineCliente.setFattura(fattura);
            ordineCliente.setSpedizione(ultimaSpedizione);
            ordineCliente.setCarico(carichi.get(0));
            ordineCliente.setProdotto(List.of(prodotto));
            ordineClienteRepo.save(ordineCliente);

            prodotto.setOrdineCliente(ordineCliente);
            prodottoRepo.save(prodotto); // ✅ aggiorna il prodotto con l’ordine


            System.out.println("Runner completato con successo.");
        };
    }
}