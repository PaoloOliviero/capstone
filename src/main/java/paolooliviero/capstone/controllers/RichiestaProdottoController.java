package paolooliviero.capstone.controllers;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import paolooliviero.capstone.entities.RichiestaProdotto;
import paolooliviero.capstone.payloads.NewRichiestaProdottoDTO;
import paolooliviero.capstone.payloads.NewRichiestaProdottoRespDTO;
import paolooliviero.capstone.service.RichiestaProdottoService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/richieste-prodotti")
public class RichiestaProdottoController {

    @Autowired
    private RichiestaProdottoService richiestaProdottoService;

    @GetMapping
//    @PreAuthorize("hasAuthority('')")
    public Page<RichiestaProdotto> findAll(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size,
                                           @RequestParam(defaultValue = "id") String sortBy) {
        return (Page<RichiestaProdotto>) richiestaProdottoService.findAll(page, size, sortBy);
    }


    @GetMapping("/{richiestaProdottoId}")
    public NewRichiestaProdottoRespDTO getById(@PathVariable long richiestaProdottoId) {
        RichiestaProdotto richiesta = richiestaProdottoService.findById(richiestaProdottoId);

        return new NewRichiestaProdottoRespDTO(
                richiesta.getId(),
                richiesta.getQuantitaRichiesta(),
                richiesta.getDataRichiesta(),
                richiesta.getMotivazione(),
                richiesta.getProdottoMagazzino().getId(),
                richiesta.getMagazzino().getId(),
                richiesta.getRichiestoDa().getId()
        );
    }



    @PostMapping("/creamanuale")
    public NewRichiestaProdottoRespDTO creaRichiesta(@RequestBody NewRichiestaProdottoDTO payload) {
        return richiestaProdottoService.save(payload);
    }


    @DeleteMapping("/{richiestaId}")
//    @PreAuthorize("hasAuthority('')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable("richiestaId") long RichiestaProdottoId) {
        this.richiestaProdottoService.findByIdAndDelete(RichiestaProdottoId);
    }

    @GetMapping("/filtroquantita")
    public List<RichiestaProdotto> filtroQuantita(@RequestParam int quantitaRichiesta) {
        return richiestaProdottoService.filtroQuantitaRichiesta(quantitaRichiesta);
    }

    @GetMapping("/filtrodata")
    public List<RichiestaProdotto> filtroData(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataRichiesta) {
        return richiestaProdottoService.filtroDataRichiesta(dataRichiesta);
    }

    @GetMapping("/filtromotivazione")
    public List<RichiestaProdotto> filtroMotivazione(@RequestParam String motivazione) {
        return richiestaProdottoService.filtroMotivazione(motivazione);
    }

    @GetMapping("/filtroprodotto")
    public List<RichiestaProdotto> filtroProdottoMagazzino(@RequestParam Long prodottoMagazzinoId) {
        return richiestaProdottoService.filtroProdottoMagazzino(prodottoMagazzinoId);
    }

    @GetMapping("/filtromagazzino")
    public List<RichiestaProdotto> filtroMagazzino(@RequestParam Long magazzinoId) {
        return richiestaProdottoService.filtroMagazzino(magazzinoId);
    }

    @GetMapping("/filtroutente")
    public List<RichiestaProdotto> filtroRichiestoDa(@RequestParam Long richiestoDaId) {
        return richiestaProdottoService.filtroRichiestoDa(richiestoDaId);
    }
}


