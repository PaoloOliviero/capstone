package paolooliviero.capstone.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import paolooliviero.capstone.entities.*;
import paolooliviero.capstone.enums.StatoMezzo;
import paolooliviero.capstone.repositories.*;

import java.time.LocalDate;
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
            PasswordEncoder bcrypt

    ) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) {

                Prodotto prodotto = new Prodotto();
                prodotto.setNome("Prodotto Test");
                prodottoRepo.save(prodotto);

                Ruolo ruoloUser = new Ruolo();
                ruoloUser.setNome("USER");
                ruoloRepo.save(ruoloUser);

                Utente utente = new Utente();
                utente.setEmail("paolo@example.com");
                utente.setNome("Paolo");
                utente.setCognome("Oliviero");
                utente.setPassword(bcrypt.encode("password123")); // usa PasswordEncoder
                utente.setRuoli(List.of(ruoloUser));
                utenteRepo.save(utente);

                Cliente cliente1 = new Cliente();
                cliente1.setRagioneSociale("Azienda Berlusconi");
                cliente1.setEmail("alfa@azienda.com");
                cliente1.setTelefonoContatto("02-1234567");
                cliente1.setPartitaIva("IT12345678901");
                cliente1.setFatturatoAnnuale(1_000_000.0);
                clienteRepo.save(cliente1);

                Cliente cliente2 = new Cliente();
                cliente2.setRagioneSociale("Azienda Salvini");
                cliente2.setEmail("beta@azienda.com");
                cliente2.setTelefonoContatto("02-7654321");
                cliente2.setPartitaIva("IT98765432109");
                cliente2.setFatturatoAnnuale(2_500_000.0);
                clienteRepo.save(cliente2);

                for (int i = 1; i <= 5; i++) {
                    Magazzino magazzino = new Magazzino();
                    magazzino.setCapacitaTotale(1000);
                    magazzino.setCapacitaOccupata(100 * i);
                    magazzinoRepo.save(magazzino);

                    ProdottoMagazzino pm = new ProdottoMagazzino();
                    pm.setQuantita(50 * i);
                    pm.setDataIngresso(LocalDate.now().minusDays(i));
                    pm.setProdotto(prodotto);
                    pm.setMagazzino(magazzino);
                    prodottoMagazzinoRepo.save(pm);

                    MovimentoMagazzino movimento = new MovimentoMagazzino();
                    movimento.setQuantity(10 * i);
                    movimento.setMagazzino(magazzino);
                    movimento.setProdottoMagazzino(pm);
                    movimento.setRegistratoDa(utente);
                    movimentoRepo.save(movimento);

                    Spedizione spedizione = new Spedizione();
                    spedizione.setPartenza("Milano");
                    spedizione.setArrivo("Roma");
                    spedizione.setTempoPrevisto(5.0 + i);
                    spedizione.setTempoEffettivo(4.5 + i);
                    spedizione.setMagazzino(magazzino);
                    spedizioneRepo.save(spedizione);

                    Autista autista1 = new Autista();
                    autista1.setNome("Antonio Tagliani");
                    autista1.setTelefono("3121234567");
                    autistaRepo.save(autista1);

                    Autista autista2 = new Autista();
                    autista2.setNome("Luigi di Maio");
                    autista2.setTelefono("3127654321");
                    autistaRepo.save(autista2);

                    Carico carico1 = new Carico();
                    carico1.setDescrizione("Carico alimentare");
                    carico1.setVolume(500.0);
                    carico1.setCategoria("Alimentari");
                    caricoRepo.save(carico1);

                    Carico carico2 = new Carico();
                    carico2.setDescrizione("Carico elettronica");
                    carico2.setVolume(800.0);
                    carico2.setCategoria("Elettronica");
                    caricoRepo.save(carico2);

                    MezzoDiTrasporto mezzo1 = new MezzoDiTrasporto();
                    mezzo1.setCapienzaMassima(1000);
                    mezzo1.setStatoMezzo(StatoMezzo.IN_SERVIZIO);
                    mezzo1.setAutista(autista1);
                    mezzoDiTrasportoRepo.save(mezzo1);

                    MezzoDiTrasporto mezzo2 = new MezzoDiTrasporto();
                    mezzo2.setCapienzaMassima(1500);
                    mezzo2.setStatoMezzo(StatoMezzo.IN_MANUTENZIONE);
                    mezzo2.setAutista(autista2);
                    mezzoDiTrasportoRepo.save(mezzo2);

                    carico1.setMezzoDiTrasporto(mezzo1);
                    carico2.setMezzoDiTrasporto(mezzo2);
                    caricoRepo.save(carico1);
                    caricoRepo.save(carico2);
                }
            }
        };
    }
}