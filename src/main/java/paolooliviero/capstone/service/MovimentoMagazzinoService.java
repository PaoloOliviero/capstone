package paolooliviero.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.*;
import paolooliviero.capstone.payloads.NewMovimentoMagazzinoDTO;
import paolooliviero.capstone.payloads.NewMovimentoMagazzinoRespDTO;
import paolooliviero.capstone.repositories.*;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovimentoMagazzinoService {

    @Autowired private ProdottoMagazzinoRepository prodottoMagazzinoRepo;
    @Autowired private MovimentoMagazzinoRepository movimentoRepo;
    @Autowired private StoricoPercorrenzeRepository storicoRepo;
    @Autowired private ProdottoRepository prodottoRepo;
    @Autowired private UtenteRepository utenteRepo;
    @Autowired private MagazzinoRepository magazzinoRepo;


    public MovimentoMagazzino save(NewMovimentoMagazzinoDTO payload) {

        if (payload.utenteId() == null || payload.prodottoMagazzinoId() == null || payload.magazzinoId() == null) {
            throw new IllegalArgumentException("ID obbligatori mancanti nel payload");
        }

        Utente utente = utenteRepo.findById(payload.utenteId())
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        Magazzino magazzinoCorrente = magazzinoRepo.findById(payload.magazzinoId())
                .orElseThrow(() -> new RuntimeException("Magazzino non trovato"));

        ProdottoMagazzino prodottoMagazzino = prodottoMagazzinoRepo.findById(payload.prodottoMagazzinoId())
                .orElseThrow(() -> new RuntimeException("ProdottoMagazzino non trovato"));

        Magazzino magazzinoPrecedente = prodottoMagazzino.getMagazzino();
        prodottoMagazzino.setMagazzino(magazzinoCorrente);
        prodottoMagazzinoRepo.save(prodottoMagazzino);

        Carico carico = prodottoMagazzino.getCarico();
        MezzoDiTrasporto mezzo = (carico != null) ? carico.getMezzoDiTrasporto() : null;

        StoricoPercorrenze storico = new StoricoPercorrenze();
        storico.setMezzoDiTrasporto(mezzo);
        storico.setAutista(mezzo != null ? mezzo.getAutista() : null);
        storico.setSpedizione(null);
        storico.setMagazzinoEntrata(magazzinoCorrente);
        storico.setMagazzinoUscita(magazzinoPrecedente);
        storico.setTempoEffettivoTratta(0.0);
        storicoRepo.save(storico);

        MovimentoMagazzino movimento = new MovimentoMagazzino();
        movimento.setProdottoMagazzino(prodottoMagazzino);
        movimento.setMagazzino(magazzinoCorrente);
        movimento.setQuantity(payload.quantity());
        movimento.setRegistratoDa(utente);
        movimento.setDataRegistrazione(payload.dataRegistrazione());
        movimento.setStoricoPercorrenza(storico);

        return movimentoRepo.save(movimento);
    }

    public Page<MovimentoMagazzino> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return this.movimentoRepo.findAll(pageable);
    }

    public List<MovimentoMagazzino> filterByMagazzino(Long magazzinoId) {
        return movimentoRepo.filterByMagazzino(magazzinoId);
    }

    public List<MovimentoMagazzino> filterByMezzo(Long mezzoId) {
        return movimentoRepo.filterByMezzo(mezzoId);
    }

    public List<MovimentoMagazzino> filterByDataRegistrazione(LocalDate data) {
        return movimentoRepo.filterByDataRegistrazione(data);
    }

    public List<MovimentoMagazzino> filterByUtente(Long utenteId) {
        return movimentoRepo.filterByUtente(utenteId);
    }

    public List<MovimentoMagazzino> filterByMinQuantity(Double minQuantity) {
        return movimentoRepo.filterByMinQuantity(minQuantity);
    }

    public List<MovimentoMagazzino> filterByProdottoMagazzino(Long pmId) {
        return movimentoRepo.filterByProdottoMagazzino(pmId);
    }

    public List<MovimentoMagazzino> filterByStoricoPercorrenza(Long storicoId) {
        return movimentoRepo.filterByStoricoPercorrenza(storicoId);
    }


}

