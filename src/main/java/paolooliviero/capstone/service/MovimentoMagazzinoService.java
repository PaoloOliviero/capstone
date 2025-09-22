package paolooliviero.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.*;
import paolooliviero.capstone.payloads.NewMovimentoMagazzinoDTO;
import paolooliviero.capstone.repositories.*;

import java.time.LocalDate;

@Service
public class MovimentoMagazzinoService {

    @Autowired private ProdottoMagazzinoRepository prodottoMagazzinoRepo;
    @Autowired private MovimentoMagazzinoRepository movimentoRepo;
    @Autowired private StoricoPercorrenzeRepository storicoRepo;
    @Autowired private ProdottoRepository prodottoRepo;
    @Autowired private UtenteRepository utenteRepo;



    public MovimentoMagazzino save(NewMovimentoMagazzinoDTO payload) {
        Utente utente = utenteRepo.findById(payload.utenteId())
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));
        Magazzino magazzinoCorrente = utente.getMagazzino();
        ProdottoMagazzino prodottoMagazzino;

        if (payload.prodottoMagazzinoId() != null) {
            prodottoMagazzino = prodottoMagazzinoRepo.findById(payload.prodottoMagazzinoId())
                    .orElseThrow(() -> new RuntimeException("ProdottoMagazzino non trovato"));

            Magazzino magazzinoPrecedente = prodottoMagazzino.getMagazzino();
            prodottoMagazzino.setMagazzino(magazzinoCorrente);
            prodottoMagazzinoRepo.save(prodottoMagazzino);

            Carico carico = prodottoMagazzino.getCarico();
            if (carico != null) {
                MezzoDiTrasporto mezzo = carico.getMezzoDiTrasporto();
                if (mezzo != null && magazzinoPrecedente != null && !magazzinoPrecedente.equals(magazzinoCorrente)) {
                    StoricoPercorrenze storico = new StoricoPercorrenze();
                    storico.setMezzoDiTrasporto(mezzo);
                    storico.setAutista(mezzo.getAutista());
                    storico.setSpedizione(null);
                    storico.setMagazzinoEntrata(magazzinoCorrente);
                    storico.setMagazzinoUscita(magazzinoPrecedente);
                    storico.setTempoEffettivoTratta(0.0);
                    storicoRepo.save(storico);
                }
            }

        } else {
            prodottoMagazzino = new ProdottoMagazzino();
            Prodotto prodotto = prodottoRepo.findById(payload.prodottoId())
                    .orElseThrow(() -> new RuntimeException("Prodotto non trovato"));

            prodottoMagazzino.setMagazzino(magazzinoCorrente);
            prodottoMagazzino.setDataIngresso(LocalDate.now());
            prodottoMagazzino.setQuantitaDisponibile(payload.prodottoMagazzino().quantitaDisponibile());
            prodottoMagazzinoRepo.save(prodottoMagazzino);
        }

        MovimentoMagazzino movimento = new MovimentoMagazzino();
        movimento.setProdottoMagazzino(prodottoMagazzino);
        movimento.setMagazzino(magazzinoCorrente);
        movimento.setQuantity(payload.prodottoMagazzino().quantitaDisponibile());
        movimento.setRegistratoDa(utente);
        movimento.setDataRegistrazione(LocalDate.now());

        return movimentoRepo.save(movimento);
    }

    public Page<MovimentoMagazzino> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return this.movimentoRepo.findAll(pageable);
    }
}
