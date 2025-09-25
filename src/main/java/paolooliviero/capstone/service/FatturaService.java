package paolooliviero.capstone.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.Cliente;
import paolooliviero.capstone.entities.Fattura;
import paolooliviero.capstone.entities.StatoFattura;
import paolooliviero.capstone.exceptions.NotFoundException;
import paolooliviero.capstone.payloads.NewFatturaDTO;
import paolooliviero.capstone.payloads.NewFatturaRespDTO;
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

        Cliente cliente = clienteRepository.findById(payload.id_cliente())
                .orElseThrow(() -> new NotFoundException(payload.id_cliente()));
        newFattura.setCliente(cliente);

        StatoFattura stato = statoFatturaRepository.findById(payload.id_statoFattura())
                .orElseThrow(() -> new NotFoundException(payload.id_statoFattura()));
        newFattura.setStatoFattura(stato);

        return fatturaRepository.save(newFattura);
    }

    public Page<NewFatturaRespDTO> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        Page<Fattura> fatture = fatturaRepository.findAll(pageable);

        return fatture.map(f -> new NewFatturaRespDTO(
                f.getId(),
                f.getDataEmissione(),
                f.getImporto(),
                f.getStatoFattura() != null ? f.getStatoFattura().getNome() : "N/D",
                f.getCliente() != null ? f.getCliente().getId() : null
        ));
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

    public List<Fattura> filtroImporto(double importoMax) {
        return fatturaRepository.findByImportoLessThanEqual(importoMax);
    }

    public List<Fattura> filtroEmissione(LocalDate dataEmissioneMax) {
        return fatturaRepository.findByDataEmissioneBeforeOrEqual(dataEmissioneMax);
    }

    public List<Fattura> filtroFattura(String statoNome) {
        return fatturaRepository.findByStatoFatturaNomeLike(statoNome);
    }

    public List<Fattura> filtroClienteId(long clienteId) {
        return fatturaRepository.findByClienteId(clienteId);
    }


}