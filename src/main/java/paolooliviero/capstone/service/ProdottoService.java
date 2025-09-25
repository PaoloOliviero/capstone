package paolooliviero.capstone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.OrdineCliente;
import paolooliviero.capstone.entities.Prodotto;
import paolooliviero.capstone.exceptions.NotFoundException;
import paolooliviero.capstone.payloads.NewProdottoDTO;
import paolooliviero.capstone.payloads.NewProdottoRespDTO;
import paolooliviero.capstone.repositories.OrdineClienteRepository;
import paolooliviero.capstone.repositories.ProdottoRepository;

import java.util.List;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;
    @Autowired
    private OrdineClienteRepository ordineClienteRepository;

    public NewProdottoRespDTO save(NewProdottoDTO payload) {
        OrdineCliente ordine = ordineClienteRepository.findById(payload.ordineClienteId())
                .orElseThrow(() -> new NotFoundException("OrdineCliente non trovato"));

        Prodotto nuovo = new Prodotto();
        nuovo.setNome(payload.nome());
        nuovo.setPrezzoUnitario(payload.prezzoUnitario());
        nuovo.setCategoria(payload.categoria());
        nuovo.setOrdineCliente(ordine);

        Prodotto salvato = prodottoRepository.save(nuovo);

        return new NewProdottoRespDTO(
                salvato.getId(),
                salvato.getNome(),
                salvato.getPrezzoUnitario(),
                salvato.getCategoria(),
                salvato.getOrdineCliente().getId()
        );
    }

    public Page<Prodotto> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, Math.min(size, 50), Sort.by(sortBy).descending());
        return prodottoRepository.findAll(pageable);
    }

    public Prodotto findById(long id) {
        return prodottoRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void delete(long id) {
        Prodotto found = findById(id);
        prodottoRepository.delete(found);
    }

    public Prodotto update(long id, NewProdottoDTO payload) {
        Prodotto found = findById(id);
        found.setNome(payload.nome());
        found.setPrezzoUnitario(payload.prezzoUnitario());
        found.setCategoria(payload.categoria());

        OrdineCliente ordine = ordineClienteRepository.findById(payload.ordineClienteId())
                .orElseThrow(() -> new NotFoundException("OrdineCliente non trovato"));
        found.setOrdineCliente(ordine);

        return prodottoRepository.save(found);
    }

    public List<Prodotto> filtroNome(String nome) {
        return prodottoRepository.filtroNome(nome);
    }

    public List<Prodotto> filtroCategoria(String categoria) {
        return prodottoRepository.filtroCategoria(categoria);
    }

    public List<Prodotto> filtroPrezzo(Double prezzoMax) {
        return prodottoRepository.filtroPrezzo(prezzoMax);
    }

    public List<Prodotto> filtroOrdineCliente(Long ordineId) {
        return prodottoRepository.filtroOrdineCliente(ordineId);
    }

    public List<NewProdottoRespDTO> findAllConRelazioniDTO() {
        List<Prodotto> lista = prodottoRepository.findAllConJoin();
        return lista.stream().map(p -> new NewProdottoRespDTO(
                p.getId(),
                p.getNome(),
                p.getPrezzoUnitario(),
                p.getCategoria(),
                p.getOrdineCliente().getId()
        )).toList();
    }
}