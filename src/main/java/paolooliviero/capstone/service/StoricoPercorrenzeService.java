package paolooliviero.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.*;
import paolooliviero.capstone.exceptions.NotFoundException;
import paolooliviero.capstone.payloads.NewFatturaDTO;
import paolooliviero.capstone.payloads.NewStoricoPercorrenzaDTO;
import paolooliviero.capstone.repositories.*;

@Service
public class StoricoPercorrenzeService {

    @Autowired
    private StoricoPercorrenzeRepository storicoRepo;

    @Autowired
    private MezzoDiTrasportoRepository mezzoRepo;

    @Autowired
    private AutistaRepository autistaRepo;

    @Autowired
    private MagazzinoRepository magazzinoRepo;

    private MezzoDiTrasporto getMezzo(Long id) {
        return (id != null) ? mezzoRepo.findById(id).orElse(null) : null;
    }

    private Autista getAutista(Long id) {
        return (id != null) ? autistaRepo.findById(id).orElse(null) : null;
    }

    private Magazzino getMagazzino(Long id) {
        return (id != null) ? magazzinoRepo.findById(id).orElse(null) : null;
    }



    public StoricoPercorrenze save(NewStoricoPercorrenzaDTO payload) {
        StoricoPercorrenze storico = new StoricoPercorrenze();

        storico.setTempoEffettivoTratta(payload.tempoEffettivoTratta());
        storico.setSpedizione(null);

        storico.setMezzoDiTrasporto(getMezzo(payload.mezzoId()));
        storico.setMagazzinoEntrata(getMagazzino(payload.magazzinoEntrataId()));
        storico.setMagazzinoUscita(getMagazzino(payload.magazzinoUscitaId()));

        return storicoRepo.save(storico);
    }


    public Page<StoricoPercorrenze> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        return storicoRepo.findAll(pageable);
    }

    public StoricoPercorrenze findById(long id) {
        return storicoRepo.findById(id).orElse(null);
    }

    public void findByIdAndDelete(long id) {
        StoricoPercorrenze found = findById(id);
        if (found != null) storicoRepo.delete(found);
    }

    public StoricoPercorrenze findByIdAndUpdate(long id, NewStoricoPercorrenzaDTO payload) {
        StoricoPercorrenze found = findById(id);
        if (found == null) return null;

        found.setTempoEffettivoTratta(payload.tempoEffettivoTratta());
        found.setMezzoDiTrasporto(mezzoRepo.findById(payload.mezzoId()).orElse(null));
        found.setMagazzinoEntrata(magazzinoRepo.findById(payload.magazzinoEntrataId()).orElse(null));
        found.setMagazzinoUscita(magazzinoRepo.findById(payload.magazzinoUscitaId()).orElse(null));

        return storicoRepo.save(found);
    }
}
