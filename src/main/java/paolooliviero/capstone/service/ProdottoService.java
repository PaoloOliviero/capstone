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
        Prodotto prodotto = new Prodotto();
        prodotto.setNome(payload.nome());
        prodotto.setPrezzoUnitario(payload.prezzoUnitario());
        prodotto.setCategoria(payload.categoria());

        if (payload.ordineClienteId() != null) {
            OrdineCliente ordine = ordineClienteRepository.findById(payload.ordineClienteId())
                    .orElseThrow(() -> new NotFoundException(payload.ordineClienteId()));
            prodotto.setOrdineCliente(ordine);
        }

        Prodotto nuovoProdotto = prodottoRepository.save(prodotto);

        return new NewProdottoRespDTO(
                nuovoProdotto.getId(),
                nuovoProdotto.getNome(),
                nuovoProdotto.getPrezzoUnitario(),
                nuovoProdotto.getCategoria(),
                nuovoProdotto.getOrdineCliente() != null ? nuovoProdotto.getOrdineCliente().getId() : null
        );
    }

    public Page<NewProdottoRespDTO> findAll(int page, int size, String sortBy) {
        if (size > 50) size = 50; // limite di sicurezza

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        Page<Prodotto> prodotti = prodottoRepository.findAll(pageable);

        return prodotti.map(p -> new NewProdottoRespDTO(
                p.getId(),
                p.getNome(),
                p.getPrezzoUnitario(),
                p.getCategoria(),
                p.getOrdineCliente() != null ? p.getOrdineCliente().getId() : null
        ));
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