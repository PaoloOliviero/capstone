package paolooliviero.capstone.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.*;
import paolooliviero.capstone.repositories.*;

import java.util.List;

@Service
public class LogisticaService {

    @Autowired private CaricoRepository caricoRepository;
    @Autowired private MezzoDiTrasportoRepository mezzoDiTrasportoRepository;
    @Autowired private MovimentoMagazzinoRepository movimentoMagazzinoRepository;
    @Autowired private ProdottoMagazzinoRepository prodottoMagazzinoRepository;
    @Autowired private OrdineClienteRepository ordineClienteRepository;
    @Autowired private ProdottoOrdinatoClienteRepository prodottoOrdinatoClienteRepository;

    public void processaOrdineCliente(OrdineCliente ordine, Utente operatore, Long mezzoDiTrasportoId) {
        List<ProdottoOrdinatoCliente> prodottiOrdinati = prodottoOrdinatoClienteRepository.findByOrdineClienteId(ordine.getId());

        MezzoDiTrasporto mezzo = mezzoDiTrasportoRepository.findById(mezzoDiTrasportoId)
                .orElseThrow(() -> new EntityNotFoundException("Mezzo di trasporto non disponibile"));


        Carico carico = new Carico();
        carico.setDescrizione("Carico per ordine cliente ID " + ordine.getId());
        carico.setVolume(calcolaVolume(prodottiOrdinati));
        carico.setProdottiOrdinati(prodottiOrdinati);
        carico.setMezzoDiTrasporto(mezzo);
        caricoRepository.save(carico);

        ordine.setCarico(carico);
        ordineClienteRepository.save(ordine);

        for (ProdottoOrdinatoCliente riga : prodottiOrdinati) {
            Prodotto prodotto = riga.getProdotto();
            double quantitaDaScaricare = riga.getQuantita();

            ProdottoMagazzino prodottoMagazzino = prodottoMagazzinoRepository.findByProdotto(prodotto)
                    .orElseThrow(() -> new EntityNotFoundException("ProdottoMagazzino non trovato per prodotto ID " + prodotto.getId()));

            double disponibile = prodottoMagazzino.getQuantitaDisponibile() != null
                    ? prodottoMagazzino.getQuantitaDisponibile()
                    : 0.0;

            double nuovaQuantita = Math.max(disponibile - quantitaDaScaricare, 0.0);
            prodottoMagazzino.setQuantitaDisponibile(nuovaQuantita);
            prodottoMagazzinoRepository.save(prodottoMagazzino);

            MovimentoMagazzino movimento = new MovimentoMagazzino();
            movimento.setProdottoMagazzino(prodottoMagazzino);
            movimento.setMagazzino(mezzo.getMagazzino());
            movimento.setQuantity(quantitaDaScaricare);
            movimento.setRegistratoDa(operatore);
            movimento.setMezzoDiTrasporto(mezzo);
            movimento.setProdottoMagazzino(prodottoMagazzino);
            movimentoMagazzinoRepository.save(movimento);
        }
    }

    private double calcolaVolume(List<ProdottoOrdinatoCliente> prodottiOrdinati) {
        return prodottiOrdinati.stream()
                .map(ProdottoOrdinatoCliente::getProdotto)
                .filter(p -> p.getVolume() != null)
                .mapToDouble(Prodotto::getVolume)
                .sum();
    }
}