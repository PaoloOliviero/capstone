package paolooliviero.capstone.controllers;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolooliviero.capstone.entities.Prodotto;
import paolooliviero.capstone.payloads.NewProdottoDTO;
import paolooliviero.capstone.payloads.NewProdottoRespDTO;
import paolooliviero.capstone.services.ProdottoService;

import java.util.List;

@RestController
@RequestMapping("/Prodotto")
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;

    @GetMapping
    public Page<NewProdottoRespDTO> findAll(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "id") String sortBy) {
        return prodottoService.findAll(page, size, sortBy);
    }

    @PostMapping("/creaProdotto")
    @ResponseStatus(HttpStatus.CREATED)
    public NewProdottoRespDTO save(@RequestBody @Validated NewProdottoDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException("Errore di validazione");
        }

        return prodottoService.save(payload);
    }


    @GetMapping("/{prodottoId}")
    public Prodotto getById(@PathVariable long prodottoId) {
        return prodottoService.findById(prodottoId);
    }

    @PutMapping("/{prodottoId}")
    public Prodotto update(@PathVariable long prodottoId, @RequestBody NewProdottoDTO payload) {
        return prodottoService.update(prodottoId, payload);
    }

    @DeleteMapping("/{prodottoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long prodottoId) {
        prodottoService.delete(prodottoId);
    }

    @GetMapping("/filtronome")
    public List<Prodotto> filtroNome(@RequestParam String nome) {
        return prodottoService.filtroNome(nome);
    }

    @GetMapping("/filtrocategoria")
    public List<Prodotto> filtroCategoria(@RequestParam String categoria) {
        return prodottoService.filtroCategoria(categoria);
    }

    @GetMapping("/filtroprezzo")
    public List<Prodotto> filtroPrezzo(@RequestParam Double prezzoMax) {
        return prodottoService.filtroPrezzo(prezzoMax);
    }

    @GetMapping("/filtroordine")
    public List<Prodotto> filtroOrdine(@RequestParam Long ordineClienteId) {
        return prodottoService.filtroOrdineCliente(ordineClienteId);
    }

    @GetMapping("/con-relazioni")
    public List<NewProdottoRespDTO> findAllConRelazioniDTO() {
        return prodottoService.findAllConRelazioniDTO();
    }
    }