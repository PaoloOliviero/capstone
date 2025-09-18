package paolooliviero.capstone.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import paolooliviero.capstone.entities.*;
import paolooliviero.capstone.repositories.*;

import java.time.LocalDate;

@Configuration
public class Runner {

    @Bean
    public CommandLineRunner initData(
            MagazzinoRepository magazzinoRepo,
            ProdottoRepository prodottoRepo,
            ProdottoMagazzinoRepository prodottoMagazzinoRepo,
            MovimentoMagazzinoRepository movimentoRepo,
            SpedizioneRepository spedizioneRepo,
            UtenteRepository utenteRepo
    ) {
        return args -> {

            // Crea un prodotto generico
            Prodotto prodotto = new Prodotto();
            prodotto.setNome("Prodotto Test");
            prodottoRepo.save(prodotto);

            // Crea un utente registratore fittizio
            Utente registratore = utenteRepo.findById(1L).orElse(null); // Assumi che esista

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
                movimento.setProdotto(prodotto);
                movimento.setMagazzino(magazzino);
                movimento.setProdottoMagazzino(pm);
                movimento.setRegistratoDa(registratore);
                movimentoRepo.save(movimento);

                Spedizione spedizione = new Spedizione();
                spedizione.setPartenza("Milano");
                spedizione.setArrivo("Roma");
                spedizione.setTempoPrevisto(5.0 + i);
                spedizione.setTempoEffettivo(4.5 + i);
                spedizione.setMagazzino(magazzino);
                spedizioneRepo.save(spedizione);
            }
        };
    }
}
