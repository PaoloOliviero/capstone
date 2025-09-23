package paolooliviero.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import paolooliviero.capstone.entities.Fattura;
import paolooliviero.capstone.entities.StoricoPercorrenze;
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

    public StoricoPercorrenze save(NewStoricoPercorrenzaDTO payload) {
        StoricoPercorrenze storico = new StoricoPercorrenze();

        storico.setTempoEffettivoTratta(payload.tempoEffettivoTratta());
        storico.setSpedizione(null);
        storico.setMagazzinoEntrata(payload.magazzinoEntrataId());
        storico.setMagazzinoUscita(payload.magazzinoUscitaId());
        storico.setMezzoDiTrasporto(payload.mezzoId());
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
        found.setMezzoDiTrasporto(mezzoRepo.findById(payload.idMezzo()).orElse(null));
        found.setAutista(autistaRepo.findById(payload.idAutista()).orElse(null));
        found.setMagazzinoEntrata(magazzinoRepo.findById(payload.idMagazzinoEntrata()).orElse(null));
        found.setMagazzinoUscita(magazzinoRepo.findById(payload.idMagazzinoUscita()).orElse(null));

        return storicoRepo.save(found);
    }
}
