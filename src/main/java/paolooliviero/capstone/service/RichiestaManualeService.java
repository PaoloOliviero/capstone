package paolooliviero.capstone.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.*;
import paolooliviero.capstone.payloads.NewRichiestaProdottoDTO;
import paolooliviero.capstone.repositories.*;

import java.time.LocalDate;
import java.util.Collections;

@Service
public class RichiestaManualeService {

    @Autowired
    private EventoMancanzaProdottoRepository eventoMancanzaProdottoRepository;

    @Autowired
    private ProdottoMagazzinoRepository prodottoMagazzinoRepository;

    @Autowired
    private MezzoDiTrasportoRepository mezzoDiTrasportoRepository;

    @Autowired
    private CaricoRepository caricoRepository;

    @Autowired
    private MovimentoMagazzinoRepository movimentoMagazzinoRepository;

    public EventoMancanzaProdotto save(NewRichiestaProdottoDTO payload) {

        ProdottoMagazzino prodottoMagazzino = prodottoMagazzinoRepository.findById(payload.prodottoMagazzinoId())
                .orElseThrow(() -> new EntityNotFoundException("ProdottoMagazzino non trovato"));

        EventoMancanzaProdotto evento = new EventoMancanzaProdotto();
        evento.setPeriodoIniziale(payload.dataRichiesta());
        evento.setPeriodoFinale(payload.dataRichiesta().plusDays(1));
        evento.setProdottoMagazzino(prodottoMagazzino);
        eventoMancanzaProdottoRepository.save(evento);

        Prodotto prodotto = prodottoMagazzino.getProdotto();
        MezzoDiTrasporto mezzo = mezzoDiTrasportoRepository.findById(payload.mezzoDiTrasportoId())
                .orElseThrow(() -> new EntityNotFoundException("Mezzo di trasporto non disponibile"));

        Carico carico = new Carico();
        carico.setDescrizione("Carico da richiesta manuale per prodotto ID " + prodotto.getId());
        carico.setVolume(prodotto.getVolume());
        carico.setProdotti(Collections.singletonList(prodotto));
        carico.setMezzoDiTrasporto(mezzo);
        caricoRepository.save(carico);

        MovimentoMagazzino movimento = new MovimentoMagazzino();
        movimento.setProdottoMagazzino(prodottoMagazzino);
        movimento.setMagazzino(mezzo.getMagazzino());
        movimento.setQuantity(payload.quantitaRichiesta());
        movimento.setRegistratoDa(null);
        movimentoMagazzinoRepository.save(movimento);

        return evento;
    }
}