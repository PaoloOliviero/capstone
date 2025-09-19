package paolooliviero.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.EventoMancanzaProdotto;
import paolooliviero.capstone.entities.ProdottoMagazzino;
import paolooliviero.capstone.entities.Utente;
import paolooliviero.capstone.exceptions.NotFoundException;
import paolooliviero.capstone.payloads.NewEventoMancanzaProdottoDTO;
import paolooliviero.capstone.repositories.EventoMancanzaProdottoRepository;
import paolooliviero.capstone.repositories.ProdottoMagazzinoRepository;
import paolooliviero.capstone.repositories.UtenteRepository;

@Service
public class EventoMancanzaProdottoService {

    @Autowired
    private EventoMancanzaProdottoRepository eventoMancanzaProdottoRepository;
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private ProdottoMagazzinoRepository prodottoMagazzinoRepository;



    public EventoMancanzaProdotto save (NewEventoMancanzaProdottoDTO payload) {

        EventoMancanzaProdotto newEventoMancanzaProdotto= new EventoMancanzaProdotto();
        newEventoMancanzaProdotto.setPeriodoIniziale(payload.periodoIniziale());
        newEventoMancanzaProdotto.setPeriodoFinale(payload.periodoFinale());
        Utente scopertoda = utenteRepository.findById(payload.scopertodaId())
                .orElseThrow(() -> new NotFoundException("Utente con ID " + payload.scopertodaId() + " non trovato"));
        newEventoMancanzaProdotto.setScopertoda(scopertoda);
        ProdottoMagazzino prodottoMagazzino = prodottoMagazzinoRepository.findById(payload.prodottoMagazzinoId())
                .orElseThrow(() -> new NotFoundException("ProdottoMagazzino con ID " + payload.prodottoMagazzinoId() + " non trovato"));
        newEventoMancanzaProdotto.setProdottoMagazzino(prodottoMagazzino);


        return eventoMancanzaProdottoRepository.save(newEventoMancanzaProdotto);
    }

    public Page<EventoMancanzaProdotto> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return this.eventoMancanzaProdottoRepository.findAll(pageable);
    }

    public EventoMancanzaProdotto findById(long Id) {
        return this.eventoMancanzaProdottoRepository.findById(Id).orElseThrow(() -> new NotFoundException(Id));
    }

    public void findByIdAndDelete(long eventoMancanzaProdottoId) {
        EventoMancanzaProdotto found = this.findById(eventoMancanzaProdottoId);
        this.eventoMancanzaProdottoRepository.delete(found);
    }

    public EventoMancanzaProdotto findByIdAndUpdate(long eventoMancanzaProdottoId, NewEventoMancanzaProdottoDTO payload) {
        EventoMancanzaProdotto found = this.findById(eventoMancanzaProdottoId);

        found.setPeriodoIniziale(payload.periodoIniziale());
        found.setPeriodoFinale(payload.periodoFinale());
        Utente scopertoda = utenteRepository.findById(payload.scopertodaId())
                .orElseThrow(() -> new NotFoundException("Utente con ID " + payload.scopertodaId() + " non trovato"));
        found.setScopertoda(scopertoda);
        ProdottoMagazzino prodottoMagazzino = prodottoMagazzinoRepository.findById(payload.prodottoMagazzinoId())
                .orElseThrow(() -> new NotFoundException("ProdottoMagazzino con ID " + payload.prodottoMagazzinoId() + " non trovato"));
        found.setProdottoMagazzino(prodottoMagazzino);
        return eventoMancanzaProdottoRepository.save(found);
    }

}

