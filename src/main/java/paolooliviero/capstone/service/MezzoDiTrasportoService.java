package paolooliviero.capstone.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import paolooliviero.capstone.entities.Autista;
import paolooliviero.capstone.entities.Carico;
import paolooliviero.capstone.entities.Cliente;
import paolooliviero.capstone.entities.MezzoDiTrasporto;
import paolooliviero.capstone.exceptions.NotFoundException;
import paolooliviero.capstone.payloads.NewClienteDTO;
import paolooliviero.capstone.payloads.NewMezzoDiTrasportoDTO;
import paolooliviero.capstone.repositories.AutistaRepository;
import paolooliviero.capstone.repositories.CaricoRepository;
import paolooliviero.capstone.repositories.ClienteRepository;
import paolooliviero.capstone.repositories.MezzoDiTrasportoRepository;

public class MezzoDiTrasportoService {

    @Autowired
    private MezzoDiTrasportoRepository mezzoDiTrasportoRepository;

    @Autowired
    private AutistaRepository autistaRepository;

    @Autowired
    private CaricoRepository caricoRepository;


    public MezzoDiTrasporto save (NewMezzoDiTrasportoDTO payload) {

        MezzoDiTrasporto newMezzoDiTrasporto= new MezzoDiTrasporto();
        newMezzoDiTrasporto.setCapienzaMassima(payload.capienzaMassima());
        newMezzoDiTrasporto.setStatoMezzo(payload.statoMezzo());
        Autista autista = autistaRepository.findById(payload.autistaId())
                .orElseThrow(() -> new EntityNotFoundException("Autista con ID " + payload.autistaId() + " non trovato"));
        newMezzoDiTrasporto.setAutista(autista);

        Carico carico = caricoRepository.findById(payload.caricoId())
                .orElseThrow(() -> new EntityNotFoundException("Carico con ID " + payload.caricoId() + " non trovato"));

        carico.setMezzoDiTrasporto(newMezzoDiTrasporto);

        return mezzoDiTrasportoRepository.save(newMezzoDiTrasporto);
    }

    public Page<MezzoDiTrasporto> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return this.mezzoDiTrasportoRepository.findAll(pageable);
    }

    public MezzoDiTrasporto findById(long Id) {
        return this.mezzoDiTrasportoRepository.findById(Id).orElseThrow(() -> new NotFoundException(Id));
    }

    public void findByIdAndDelete(long mezzoDiTrasportoId) {
        MezzoDiTrasporto found = this.findById(mezzoDiTrasportoId);
        this.mezzoDiTrasportoRepository.delete(found);
    }

    public MezzoDiTrasporto findByIdAndUpdate(long MezzoDiTrasportoId, NewMezzoDiTrasportoDTO payload) {
        MezzoDiTrasporto found = this.findById(MezzoDiTrasportoId);

        found.setCapienzaMassima(payload.capienzaMassima());
        found.setStatoMezzo(payload.statoMezzo());
        Autista autista = autistaRepository.findById(payload.autistaId())
                .orElseThrow(() -> new EntityNotFoundException("Autista con ID " + payload.autistaId() + " non trovato"));
        found.setAutista(autista);
        Carico carico = caricoRepository.findById(payload.caricoId())
                .orElseThrow(() -> new EntityNotFoundException("Carico con ID " + payload.caricoId() + " non trovato"));
        carico.setMezzoDiTrasporto(found);
        MezzoDiTrasporto updated = mezzoDiTrasportoRepository.save(found);
        caricoRepository.save(carico);

        return updated;

    }

}
