package paolooliviero.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.StatoFattura;
import paolooliviero.capstone.exceptions.NotFoundException;
import paolooliviero.capstone.payloads.NewStatoFatturaDTO;
import paolooliviero.capstone.repositories.StatoFatturaRepository;

@Service
public class StatoFatturaService {
    @Autowired
    private StatoFatturaRepository statoFatturaRepository;


    public Page<StatoFattura> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return statoFatturaRepository.findAll(pageable);
    }

    public StatoFattura findById(Long id) {

        return statoFatturaRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public StatoFattura save(NewStatoFatturaDTO statoFatturaDTO) {
        StatoFattura statoFattura = new StatoFattura();
        statoFattura.setNome(statoFatturaDTO.nome());
        return statoFatturaRepository.save(statoFattura);
    }

    public StatoFattura findByIdAndUpdate(Long id, NewStatoFatturaDTO dto) {
        StatoFattura stato = findById(id);
        stato.setNome(dto.nome());
        return statoFatturaRepository.save(stato);
    }

    public void findByIdAndDelete(Long id) {
        StatoFattura stato = findById(id);
        statoFatturaRepository.delete(stato);
    }
}