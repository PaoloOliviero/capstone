package paolooliviero.capstone.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.*;
import paolooliviero.capstone.exceptions.NotFoundException;
import paolooliviero.capstone.payloads.NewRichiestaProdottoDTO;
import paolooliviero.capstone.payloads.NewRichiestaProdottoRespDTO;
import paolooliviero.capstone.repositories.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class RichiestaProdottoService {

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

    @Autowired
    private RichiestaProdottoRepository richiestaProdottoRepository;

    @Autowired
    private MagazzinoRepository magazzinoRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    public NewRichiestaProdottoRespDTO save(NewRichiestaProdottoDTO payload) {

        ProdottoMagazzino prodottoMagazzino = prodottoMagazzinoRepository.findById(payload.prodottoMagazzinoId())
                .orElseThrow(() -> new EntityNotFoundException("ProdottoMagazzino non trovato"));

        Magazzino magazzino = magazzinoRepository.findById(payload.magazzinoId())
                .orElseThrow(() -> new EntityNotFoundException("Magazzino non trovato"));

        Utente richiedente = utenteRepository.findById(payload.richiestoDaId())
                .orElseThrow(() -> new EntityNotFoundException("Utente richiedente non trovato"));

        MezzoDiTrasporto mezzo = mezzoDiTrasportoRepository.findByMagazzino(magazzino)
                .orElseThrow(() -> new EntityNotFoundException("Mezzo di trasporto non disponibile per questo magazzino"));

        Prodotto prodotto = prodottoMagazzino.getProdotto();

        EventoMancanzaProdotto evento = new EventoMancanzaProdotto();
        evento.setPeriodoIniziale(payload.dataRichiesta());
        evento.setPeriodoFinale(payload.dataRichiesta().plusDays(1));
        evento.setProdottoMagazzino(prodottoMagazzino);
        eventoMancanzaProdottoRepository.save(evento);

        MovimentoMagazzino movimento = new MovimentoMagazzino();
        movimento.setProdottoMagazzino(prodottoMagazzino);
        movimento.setMagazzino(magazzino);
        movimento.setQuantity(payload.quantitaRichiesta());
        movimento.setRegistratoDa(richiedente);
        movimento.setMezzoDiTrasporto(mezzo);
        movimentoMagazzinoRepository.save(movimento);

        Carico carico = new Carico();
        carico.setDescrizione("Carico da richiesta manuale per prodotto ID " + prodotto.getId());
        carico.setVolume(prodotto.getVolume());
        carico.setProdottoMagazzino(Collections.singletonList(prodottoMagazzino));
        carico.setMezzoDiTrasporto(mezzo);
        caricoRepository.save(carico);

        RichiestaProdotto richiesta = new RichiestaProdotto();
        richiesta.setQuantitaRichiesta(payload.quantitaRichiesta());
        richiesta.setDataRichiesta(payload.dataRichiesta());
        richiesta.setMotivazione(payload.motivazione());
        richiesta.setProdottoMagazzino(prodottoMagazzino);
        richiesta.setRichiestoDa(richiedente);
        richiesta.setMagazzino(magazzino);
        richiesta.setMovimentoAssociato(movimento);
        richiesta.setEventoMancanzaProdotto(evento);
        richiesta.setOrdineCliente(null);
        richiestaProdottoRepository.save(richiesta);

        return new NewRichiestaProdottoRespDTO(
                richiesta.getId(),
                richiesta.getQuantitaRichiesta(),
                richiesta.getDataRichiesta(),
                richiesta.getMotivazione(),
                prodottoMagazzino.getId(),
                magazzino.getId(),
                richiedente.getId()
        );
    }

    public void findByIdAndDelete(long id) {
        RichiestaProdotto found = this.findById(id);
        richiestaProdottoRepository.delete(found);
    }

    public Page<RichiestaProdotto> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return richiestaProdottoRepository.findAll(pageable);
    }

    public RichiestaProdotto findById(long id) {
        return richiestaProdottoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public List<RichiestaProdotto> filtroQuantitaRichiesta(int quantitaRichiesta) {
        return richiestaProdottoRepository.filtroQuantitaRichiesta(quantitaRichiesta);
    }

    public List<RichiestaProdotto> filtroDataRichiesta(LocalDate dataRichiesta) {
        return richiestaProdottoRepository.filtroDataRichiesta(dataRichiesta);
    }

    public List<RichiestaProdotto> filtroMotivazione(String motivazione) {
        return richiestaProdottoRepository.filtroMotivazione(motivazione);
    }

    public List<RichiestaProdotto> filtroProdottoMagazzino(Long prodottoMagazzinoId) {
        return richiestaProdottoRepository.filtroProdottoMagazzino(prodottoMagazzinoId);
    }

    public List<RichiestaProdotto> filtroMagazzino(Long magazzinoId) {
        return richiestaProdottoRepository.filtroMagazzino(magazzinoId);
    }

    public List<RichiestaProdotto> filtroRichiestoDa(Long richiestoDaId) {
        return richiestaProdottoRepository.filtroRichiestoDa(richiestoDaId);
    }


}