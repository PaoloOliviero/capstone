package paolooliviero.capstone.controllers;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolooliviero.capstone.entities.ProdottoMagazzino;
import paolooliviero.capstone.payloads.*;
import paolooliviero.capstone.service.ProdottoMagazzinoService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ProdottoMagazzino")
public class ProdottoMagazzinoController {

    @Autowired
    private ProdottoMagazzinoService prodottoMagazzinoService;

    @GetMapping
//    @PreAuthorize("hasAuthority('')")
    public Page<ProdottoMagazzino> findAll(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size,
                                           @RequestParam(defaultValue = "id") String sortBy
    ) {
        return (Page<ProdottoMagazzino>) this.prodottoMagazzinoService.findAll(page, size, sortBy);
    }

    @PostMapping("/creaProdottoMagazzino")
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasAuthority('')")
    public NewProdottoMagazzinoRespDTO save(@RequestBody @Validated NewProdottoMagazzinoDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException("Errore");
        } else {
            ProdottoMagazzino newProdottoMagazzino = prodottoMagazzinoService.save(payload);
            return new NewProdottoMagazzinoRespDTO(
                    newProdottoMagazzino.getId(),
                    newProdottoMagazzino.getQuantitaDisponibile(),
                    newProdottoMagazzino.getDataIngresso(),
                    newProdottoMagazzino.getProdotto().getId(),
                    newProdottoMagazzino.getMagazzino().getId()
            );
        }

    }

    @GetMapping("/{prodottoMagazzinoId}")
//    @PreAuthorize("hasAuthority('')")
    public ProdottoMagazzino getById(@PathVariable long prodottoMagazzinoId) {
        return this.prodottoMagazzinoService.findById(prodottoMagazzinoId);
    }

    @PutMapping("/{prodottoMagazzinoId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ProdottoMagazzino getByIdAndUpdate(@PathVariable long prodottoMagazzinoId, @RequestBody NewProdottoMagazzinoDTO payload) {
        return this.prodottoMagazzinoService.findByIdAndUpdate(prodottoMagazzinoId, payload);
    }

    @DeleteMapping("/{prodottoMagazzinoId}")
//    @PreAuthorize("hasAuthority('')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable long prodottoMagazzinoId) {
        this.prodottoMagazzinoService.findByIdAndDelete(prodottoMagazzinoId);
    }

    public ProdottoMagazzinoController(ProdottoMagazzinoService prodottoMagazzinoService) {
        this.prodottoMagazzinoService = prodottoMagazzinoService;
    }

    @GetMapping("/filtroquantita")
    public List<ProdottoMagazzino> filtroQuantita(@RequestParam Double quantita) {
        return prodottoMagazzinoService.filtroQuantitaDisponibile(quantita);
    }

    @GetMapping("/filtrodata")
    public List<ProdottoMagazzino> filtroData(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataIngresso) {
        return prodottoMagazzinoService.filtroDataIngresso(dataIngresso);
    }

    @GetMapping("/filtroprodotto")
    public List<ProdottoMagazzino> filtroProdotto(@RequestParam Long prodottoId) {
        return prodottoMagazzinoService.filtroProdotto(prodottoId);
    }

    @GetMapping("/filtromagazzino")
    public List<ProdottoMagazzino> filtroMagazzino(@RequestParam Long magazzinoId) {
        return prodottoMagazzinoService.filtroMagazzino(magazzinoId);
    }

    @GetMapping("/con-relazioni")
    public List<NewProdottoMagazzinoRespDTO> findAllConRelazioniDTO() {
        return prodottoMagazzinoService.findAllConRelazioniDTO();
    }

    }
