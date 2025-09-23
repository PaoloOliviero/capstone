package paolooliviero.capstone.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.Magazzino;
import paolooliviero.capstone.entities.MovimentoMagazzino;
import paolooliviero.capstone.entities.Prodotto;
import paolooliviero.capstone.entities.ProdottoMagazzino;
import paolooliviero.capstone.exceptions.NotFoundException;
import paolooliviero.capstone.payloads.NewProdottoMagazzinoDTO;
import paolooliviero.capstone.payloads.NewProdottoMagazzinoRespDTO;
import paolooliviero.capstone.repositories.*;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class ProdottoMagazzinoService {

    @Autowired
    private ProdottoMagazzinoRepository prodottoMagazzinoRepository;
    @Autowired
    private ProdottoRepository prodottoRepository;
    @Autowired
    private MagazzinoRepository magazzinoRepository;
    @Autowired
    private MovimentoMagazzinoRepository movimentoRepo;



    public ProdottoMagazzino save(NewProdottoMagazzinoDTO payload) {
        ProdottoMagazzino nuovo = new ProdottoMagazzino();

        nuovo.setQuantitaDisponibile(payload.quantitaDisponibile().doubleValue());
        nuovo.setDataIngresso(payload.dataIngresso());

        Prodotto prodotto = prodottoRepository.findById(payload.prodottoId())
                .orElseThrow(() -> new NotFoundException("Prodotto non trovato: " + payload.prodottoId()));
        nuovo.setProdotto(prodotto);

        Magazzino magazzino = magazzinoRepository.findById(payload.magazzinoId())
                .orElseThrow(() -> new NotFoundException("Magazzino non trovato: " + payload.magazzinoId()));
        nuovo.setMagazzino(magazzino);

        return prodottoMagazzinoRepository.save(nuovo);
    }

    public Page<ProdottoMagazzino> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        return this.prodottoMagazzinoRepository.findAll(pageable);
    }

    public ProdottoMagazzino findById(long fatturaId) {
        return this.prodottoMagazzinoRepository.findById(fatturaId).orElseThrow(() -> new NotFoundException(fatturaId));
    }

    public void findByIdAndDelete(long fatturaId) {
        ProdottoMagazzino found = this.findById(fatturaId);
        this.prodottoMagazzinoRepository.delete(found);
    }

    public ProdottoMagazzino findByIdAndUpdate(long prodottoMagazzinoId, NewProdottoMagazzinoDTO payload) {
        ProdottoMagazzino found = prodottoMagazzinoRepository.findById(prodottoMagazzinoId)
                .orElseThrow(() -> new NotFoundException("ProdottoMagazzino non trovato: " + prodottoMagazzinoId));

        found.setQuantitaDisponibile(payload.quantitaDisponibile().doubleValue());
        found.setDataIngresso(payload.dataIngresso());

        Prodotto prodotto = prodottoRepository.findById(payload.prodottoId())
                .orElseThrow(() -> new NotFoundException("Prodotto non trovato: " + payload.prodottoId()));
        found.setProdotto(prodotto);

        Magazzino magazzino = magazzinoRepository.findById(payload.magazzinoId())
                .orElseThrow(() -> new NotFoundException("Magazzino non trovato: " + payload.magazzinoId()));
        found.setMagazzino(magazzino);

        return prodottoMagazzinoRepository.save(found);
    }

        public List<ProdottoMagazzino> filtroQuantitaDisponibile(Double quantita) {
            return prodottoMagazzinoRepository.filtroQuantitaDisponibile(quantita);
        }

        public List<ProdottoMagazzino> filtroDataIngresso(LocalDate dataIngresso) {
            return prodottoMagazzinoRepository.filtroDataIngresso(dataIngresso);
        }

        public List<ProdottoMagazzino> filtroProdotto(Long prodottoId) {
            return prodottoMagazzinoRepository.filtroProdotto(prodottoId);
        }

        public List<ProdottoMagazzino> filtroMagazzino(Long magazzinoId) {
            return prodottoMagazzinoRepository.filtroMagazzino(magazzinoId);
        }

    public List<ProdottoMagazzino> findAllConRelazioni() {
        return prodottoMagazzinoRepository.findAllConJoin();
    }


    public List<NewProdottoMagazzinoRespDTO> findAllConRelazioniDTO() {
        return prodottoMagazzinoRepository.findAllConJoin().stream()
                .map(p -> new NewProdottoMagazzinoRespDTO(
                        p.getId(),
                        p.getQuantitaDisponibile(),
                        p.getDataIngresso(),
                        p.getProdotto() != null ? p.getProdotto().getId() : null,
                        p.getMagazzino() != null ? p.getMagazzino().getId() : null
                ))
                .toList();
    }
}


