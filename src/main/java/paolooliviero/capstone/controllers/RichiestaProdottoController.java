package paolooliviero.capstone.controllers;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
public class RichiestaProdottoController {

    @Autowired
    private RichiestaProdottoService richiestaProdottoService;

    @GetMapping
    public Page<NewRichiestaProdottoRespDTO> findAll(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size,
                                                        @RequestParam(defaultValue = "id") String sortBy) {
        return richiestaProdottoService.findAll(page, size, sortBy);
    }


    @GetMapping("/{richiestaProdottoId}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
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
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public NewRichiestaProdottoRespDTO creaRichiesta(@RequestBody NewRichiestaProdottoDTO payload) {
        return richiestaProdottoService.save(payload);
    }


    @DeleteMapping("/{richiestaId}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
//    @PreAuthorize("hasAuthority('')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable("richiestaId") long RichiestaProdottoId) {
        this.richiestaProdottoService.findByIdAndDelete(RichiestaProdottoId);
    }

    @GetMapping("/filtroquantita")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public List<RichiestaProdotto> filtroQuantita(@RequestParam int quantitaRichiesta) {
        return richiestaProdottoService.filtroQuantitaRichiesta(quantitaRichiesta);
    }

    @GetMapping("/filtrodata")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public List<RichiestaProdotto> filtroData(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataRichiesta) {
        return richiestaProdottoService.filtroDataRichiesta(dataRichiesta);
    }

    @GetMapping("/filtromotivazione")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public List<RichiestaProdotto> filtroMotivazione(@RequestParam String motivazione) {
        return richiestaProdottoService.filtroMotivazione(motivazione);
    }

    @GetMapping("/filtroprodotto")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public List<RichiestaProdotto> filtroProdottoMagazzino(@RequestParam Long prodottoMagazzinoId) {
        return richiestaProdottoService.filtroProdottoMagazzino(prodottoMagazzinoId);
    }

    @GetMapping("/filtromagazzino")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public List<RichiestaProdotto> filtroMagazzino(@RequestParam Long magazzinoId) {
        return richiestaProdottoService.filtroMagazzino(magazzinoId);
    }

    @GetMapping("/filtroutente")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public List<RichiestaProdotto> filtroRichiestoDa(@RequestParam Long richiestoDaId) {
        return richiestaProdottoService.filtroRichiestoDa(richiestoDaId);
    }
}


