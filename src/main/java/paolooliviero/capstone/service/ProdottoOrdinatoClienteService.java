package paolooliviero.capstone.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.Prodotto;
import paolooliviero.capstone.entities.OrdineCliente;
import paolooliviero.capstone.entities.ProdottoOrdinatoCliente;
import paolooliviero.capstone.exceptions.NotFoundException;
import paolooliviero.capstone.payloads.NewProdottoOrdinatoClienteDTO;
import paolooliviero.capstone.repositories.ProdottoOrdinatoClienteRepository;
import paolooliviero.capstone.repositories.ProdottoRepository;
import paolooliviero.capstone.repositories.OrdineClienteRepository;

import java.util.List;

@Service
@Slf4j
public class ProdottoOrdinatoClienteService {

    @Autowired
    private ProdottoOrdinatoClienteRepository prodottoOrdinatoClienteRepository;

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Autowired
    private OrdineClienteRepository ordineClienteRepository;

    public ProdottoOrdinatoCliente save(NewProdottoOrdinatoClienteDTO payload) {
        Prodotto prodotto = prodottoRepository.findById(payload.prodottoId())
                .orElseThrow(() -> new NotFoundException(payload.prodottoId()));

        OrdineCliente ordine = ordineClienteRepository.findById(payload.ordineClienteId())
                .orElseThrow(() -> new NotFoundException(payload.ordineClienteId()));

        ProdottoOrdinatoCliente nuovo = new ProdottoOrdinatoCliente();
        nuovo.setProdotto(prodotto);
        nuovo.setOrdineCliente(ordine);
        nuovo.setQuantita(payload.quantita());

        return prodottoOrdinatoClienteRepository.save(nuovo);
    }

    public Page<ProdottoOrdinatoCliente> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return prodottoOrdinatoClienteRepository.findAll(pageable);
    }

    public ProdottoOrdinatoCliente findById(long id) {
        return prodottoOrdinatoClienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id) {
        ProdottoOrdinatoCliente found = this.findById(id);
        prodottoOrdinatoClienteRepository.delete(found);
    }

    public ProdottoOrdinatoCliente findByIdAndUpdate(long id, NewProdottoOrdinatoClienteDTO payload) {
        ProdottoOrdinatoCliente found = this.findById(id);

        Prodotto prodotto = prodottoRepository.findById(payload.prodottoId())
                .orElseThrow(() -> new NotFoundException(payload.prodottoId()));

        OrdineCliente ordine = ordineClienteRepository.findById(payload.ordineClienteId())
                .orElseThrow(() -> new NotFoundException(payload.ordineClienteId()));

        found.setProdotto(prodotto);
        found.setOrdineCliente(ordine);
        found.setQuantita(payload.quantita());

        return prodottoOrdinatoClienteRepository.save(found);
    }

    public List<ProdottoOrdinatoCliente> filterToOrdine(long ordineId) {
        return prodottoOrdinatoClienteRepository.findByOrdineClienteId(ordineId);
    }

    public List<ProdottoOrdinatoCliente> filterToProdotto(long prodottoId) {
        return prodottoOrdinatoClienteRepository.findByProdottoId(prodottoId);
    }
}