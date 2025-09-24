package paolooliviero.capstone.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.Segmento;
import paolooliviero.capstone.enums.TipologiaSegmento;
import paolooliviero.capstone.exceptions.NotFoundException;
import paolooliviero.capstone.payloads.NewSegmentoDTO;
import paolooliviero.capstone.repositories.SegmentoRepository;

import java.util.List;

@Service
@Slf4j
public class SegmentoService {

    @Autowired
    private SegmentoRepository segmentoRepository;

    public Segmento save(NewSegmentoDTO payload) {
        Segmento newSegmento = new Segmento();
        newSegmento.setNome(payload.nome());
        newSegmento.setCriterio(payload.criterio());
        newSegmento.setTipologiaSegmento(payload.tipoSegmento());

        return segmentoRepository.save(newSegmento);
    }

    public Page<Segmento> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return segmentoRepository.findAll(pageable);
    }

    public Segmento findById(long segmentoId) {
        return segmentoRepository.findById(segmentoId)
                .orElseThrow(() -> new NotFoundException(segmentoId));
    }

    public void findByIdAndDelete(long segmentoId) {
        Segmento found = this.findById(segmentoId);
        segmentoRepository.delete(found);
    }

    public Segmento findByIdAndUpdate(long segmentoId, NewSegmentoDTO payload) {
        Segmento found = this.findById(segmentoId);
        found.setNome(payload.nome());
        found.setCriterio(payload.criterio());
        found.setTipologiaSegmento(payload.tipoSegmento());
        return segmentoRepository.save(found);
    }

    public List<Segmento> filterByTipologia(TipologiaSegmento tipologiaSegmento) {
        return segmentoRepository.findByTipologiaSegmento(tipologiaSegmento);
    }
}