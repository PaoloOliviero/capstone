package paolooliviero.capstone.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.*;
import paolooliviero.capstone.enums.StatoOrdine;
import paolooliviero.capstone.exceptions.NotFoundException;
import paolooliviero.capstone.payloads.NewMezzoDiTrasportoDTO;
import paolooliviero.capstone.payloads.NewOrdineClienteDTO;
import paolooliviero.capstone.payloads.NewProdottoDTO;
import paolooliviero.capstone.repositories.*;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrdineClienteService {

    @Autowired
    private OrdineClienteRepository ordineClienteRepository;

    @Autowired
    private RichiestaProdottoRepository richiestaProdottoRepository;

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FatturaRepository fatturaRepository;

    @Autowired
    private SpedizioneRepository spedizioneRepository;

    @Autowired
    private CaricoRepository caricoRepository;


    public OrdineCliente save (NewOrdineClienteDTO payload) {
        OrdineCliente newOrdineCliente = new OrdineCliente();
        newOrdineCliente.setDataOrdine(payload.dataOrdine());
        newOrdineCliente.setStatoOrdine(payload.statoOrdine());
        newOrdineCliente.setIndirizzoSpedizione(payload.indirizzoSpedizione());

        Cliente cliente = new Cliente();
        cliente.setRagioneSociale(payload.cliente().ragioneSociale());
        cliente.setPartitaIva(payload.cliente().partitaIva());
        cliente.setEmail(payload.cliente().email());
        cliente.setTelefonoContatto(payload.cliente().telefonoContatto());
        clienteRepository.save(cliente);
        newOrdineCliente.setCliente(cliente);

        if (payload.statoOrdine() == StatoOrdine.APPROVATO) {
            Fattura fattura = new Fattura();
            fattura.setImporto(payload.fattura().importo());
            fattura.setDataEmissione(payload.fattura().dataEmissione());
            fatturaRepository.save(fattura);
            newOrdineCliente.setFattura(fattura);
        }

        Spedizione spedizione = new Spedizione();
        spedizione.setPartenza(payload.spedizione().partenza());
        spedizione.setArrivo(payload.spedizione().arrivo());
        spedizione.setTempoPrevisto(payload.spedizione().tempoPrevisto());
        spedizione.setTempoEffettivo(payload.spedizione().tempoEffettivo());
        spedizioneRepository.save(spedizione);
        newOrdineCliente.setSpedizione(spedizione);

        Carico carico = new Carico();
        carico.setDescrizione(payload.carico().descrizione());
        carico.setVolume(payload.carico().volume());
        carico.setCategoria(payload.carico().categoria());
        caricoRepository.save(carico);
        newOrdineCliente.setCarico(carico);

        List<Prodotto> prodotti = new ArrayList<>();
        for (Long id : payload.prodottoIds()) {
            Prodotto prodotto = prodottoRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Prodotto con ID " + id + " non trovato"));
            prodotti.add(prodotto);
        }
        newOrdineCliente.setProdotto(prodotti);

        if (payload.richiestaProdotto() != null) {
            RichiestaProdotto richiesta = new RichiestaProdotto();
            richiesta.setQuantitaRichiesta(payload.richiestaProdotto().quantitaRichiesta());
            richiesta.setOrdineCliente(newOrdineCliente);
            richiestaProdottoRepository.save(richiesta);
            newOrdineCliente.setRichiestaProdotto(richiesta);
        }

        return ordineClienteRepository.save(newOrdineCliente);
    }


    public Page<OrdineCliente> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return this.ordineClienteRepository.findAll(pageable);
    }

    public OrdineCliente findById(long Id) {
        return this.ordineClienteRepository.findById(Id).orElseThrow(() -> new NotFoundException(Id));
    }

    public void findByIdAndDelete(long ordineClienteId) {
        OrdineCliente found = this.findById(ordineClienteId);
        this.ordineClienteRepository.delete(found);
    }

    public OrdineCliente findByIdAndUpdate(long ordineClienteId, NewOrdineClienteDTO payload) {
        OrdineCliente found = this.findById(ordineClienteId);
        found.setStatoOrdine(payload.statoOrdine());
        found.setDataOrdine(payload.dataOrdine());
        found.setIndirizzoSpedizione(payload.indirizzoSpedizione());
        return ordineClienteRepository.save(found);

    }
}
