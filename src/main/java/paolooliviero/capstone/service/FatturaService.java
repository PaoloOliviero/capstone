package paolooliviero.capstone.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.Fattura;
import paolooliviero.capstone.exceptions.NotFoundException;
import paolooliviero.capstone.payloads.NewFatturaDTO;
import paolooliviero.capstone.repositories.ClienteRepository;
import paolooliviero.capstone.repositories.FatturaRepository;
import paolooliviero.capstone.repositories.StatoFatturaRepository;
import java.util.Optional;


import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class FatturaService {

    @Autowired
    private FatturaRepository fatturaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private StatoFatturaRepository statoFatturaRepository;


    public Fattura save(NewFatturaDTO payload) {

        Fattura newFattura = new Fattura();
        newFattura.setImporto(payload.importo());
        newFattura.setDataEmissione(payload.dataEmissione());
        newFattura.setCliente(clienteRepository.findById(payload.id_cliente()).orElseThrow(() -> new NotFoundException(payload.id_cliente())));
        return fatturaRepository.save(newFattura);

    }

    public Page<Fattura> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        return this.fatturaRepository.findAll(pageable);
    }

    public Fattura findById(long fatturaId) {
        return this.fatturaRepository.findById(fatturaId).orElseThrow(() -> new NotFoundException(fatturaId));
    }

    public void findByIdAndDelete(long fatturaId) {
        Fattura found = this.findById(fatturaId);
        this.fatturaRepository.delete(found);
    }

    public Fattura findByIdAndUpdate(long fatturaId, NewFatturaDTO payload) {
        Fattura found = this.findById(fatturaId);

        found.setDataEmissione(payload.dataEmissione());
        found.setImporto(payload.importo());

        return fatturaRepository.save(found);
    }
}