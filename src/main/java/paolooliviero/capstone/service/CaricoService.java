package paolooliviero.capstone.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.Carico;
import paolooliviero.capstone.entities.MezzoDiTrasporto;
import paolooliviero.capstone.entities.ProdottoMagazzino;
import paolooliviero.capstone.exceptions.NotFoundException;
import paolooliviero.capstone.payloads.NewCaricoDTO;
import paolooliviero.capstone.payloads.NewCaricoRespDTO;
import paolooliviero.capstone.repositories.CaricoRepository;
import paolooliviero.capstone.repositories.MezzoDiTrasportoRepository;
import paolooliviero.capstone.repositories.ProdottoMagazzinoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CaricoService {

    @Autowired
    private CaricoRepository caricoRepository;

    @Autowired
    private MezzoDiTrasportoRepository mezzoRepo;

    @Autowired
    private ProdottoMagazzinoRepository prodottoMagazzinoRepo;

    public Carico save(NewCaricoDTO payload) {
        Carico newCarico = new Carico();
        newCarico.setCategoria(payload.categoria());
        newCarico.setDescrizione(payload.descrizione());

        MezzoDiTrasporto mezzo = mezzoRepo.findById(payload.mezzoId())
                .orElseThrow(() -> new NotFoundException("Mezzo non trovato"));
        newCarico.setMezzoDiTrasporto(mezzo);

        ProdottoMagazzino prodottoMagazzino = prodottoMagazzinoRepo.findById(payload.prodottoMagazzinoId())
                .orElseThrow(() -> new NotFoundException("ProdottoMagazzino non trovato"));
        prodottoMagazzino.setCarico(newCarico);
        newCarico.setProdottoMagazzino(new ArrayList<>(List.of(prodottoMagazzino)));

        return caricoRepository.save(newCarico);
    }

    public Page<Carico> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return caricoRepository.findAll(pageable);
    }

    public Carico findById(long id) {
        return caricoRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(long caricoId) {
        Carico found = findById(caricoId);
        caricoRepository.delete(found);
    }

    public Carico findByIdAndUpdate(long caricoId, NewCaricoDTO payload) {
        Carico found = findById(caricoId);
        found.setCategoria(payload.categoria());
        found.setDescrizione(payload.descrizione());
        return caricoRepository.save(found);
    }

    public List<Carico> filtroCategoria(String categoria) {
        return caricoRepository.filtroCategoria(categoria);
    }

    public List<Carico> filtroDescrizione(String descrizione) {
        return caricoRepository.filtroDescrizione(descrizione);
    }

    public List<Carico> filtroMezzo(Long mezzoId) {
        return caricoRepository.filtroMezzo(mezzoId);
    }

    public List<Carico> findAllConRelazioni() {
        return caricoRepository.findAllConJoin();
    }

    public List<NewCaricoRespDTO> findAllConRelazioniDTO() {
        return caricoRepository.findAllConJoin().stream().map(carico -> {
            List<Long> prodottiIds = carico.getProdottoMagazzino() == null
                    ? List.of()
                    : carico.getProdottoMagazzino().stream()
                    .map(ProdottoMagazzino::getId)
                    .toList();

            return new NewCaricoRespDTO(
                    carico.getId(),
                    carico.getCategoria(),
                    carico.getDescrizione(),
                    carico.getVolume(),
                    carico.getMezzoDiTrasporto() != null ? carico.getMezzoDiTrasporto().getId() : null,
                    prodottiIds
            );
        }).toList();
    }
}