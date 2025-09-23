package paolooliviero.capstone.controllers;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolooliviero.capstone.entities.MezzoDiTrasporto;
import paolooliviero.capstone.entities.MovimentoMagazzino;
import paolooliviero.capstone.entities.Utente;
import paolooliviero.capstone.payloads.NewMezzoDiTrasportoDTO;
import paolooliviero.capstone.payloads.NewMezzoDiTrasportoRespDTO;
import paolooliviero.capstone.payloads.NewMovimentoMagazzinoDTO;
import paolooliviero.capstone.payloads.NewMovimentoMagazzinoRespDTO;
import paolooliviero.capstone.service.MezzoDiTrasportoService;
import paolooliviero.capstone.service.MovimentoMagazzinoService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/movimenti")
public class MovimentoMagazzinoController {

    @Autowired
    private MovimentoMagazzinoService movimentoMagazzinoService;

    @GetMapping
//    @PreAuthorize("hasAuthority('')")
    public Page<MovimentoMagazzino> findAll(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "id") String sortBy) {
        return movimentoMagazzinoService.findAll(page, size, sortBy);
    }

    @PostMapping ("/creamovimento")
    @ResponseStatus(HttpStatus.CREATED)
    public NewMovimentoMagazzinoRespDTO save(@RequestBody @Validated NewMovimentoMagazzinoDTO payload,
                                             BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException("Validazione fallita");
        }

        MovimentoMagazzino movimento = movimentoMagazzinoService.save(payload);

        return new NewMovimentoMagazzinoRespDTO(
                movimento.getId(),
                movimento.getProdottoMagazzino().getId(),
                movimento.getMagazzino().getId(),
                movimento.getQuantity(),
                movimento.getRegistratoDa().getNome(),
                movimento.getDataRegistrazione(),
                movimento.getStoricoPercorrenza() != null ? movimento.getStoricoPercorrenza().getId() : null,
                movimento.getProdottoMagazzino().getCarico() != null ? movimento.getProdottoMagazzino().getCarico().getMezzoDiTrasporto().getId() : null,
                movimento.getStoricoPercorrenza() != null ? movimento.getStoricoPercorrenza().getMagazzinoEntrata().getId() : null,
                movimento.getStoricoPercorrenza() != null ? movimento.getStoricoPercorrenza().getMagazzinoUscita().getId() : null,
                movimento.getRegistratoDa().getId()
        );
    }
    @GetMapping("/filtra/magazzino/{magazzinoId}")
    public List<MovimentoMagazzino> filterByMagazzino(@PathVariable Long magazzinoId) {
        return movimentoMagazzinoService.filterByMagazzino(magazzinoId);
    }

    @GetMapping("/filtra/mezzo/{mezzoId}")
    public List<MovimentoMagazzino> filterByMezzo(@PathVariable Long mezzoId) {
        return movimentoMagazzinoService.filterByMezzo(mezzoId);
    }

    @GetMapping("/filtra/data-registrazione")
    public List<MovimentoMagazzino> filterByDataRegistrazione(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return movimentoMagazzinoService.filterByDataRegistrazione(data);
    }

    @GetMapping("/filtra/utente/{utenteId}")
    public List<MovimentoMagazzino> filterByUtente(@PathVariable Long utenteId) {
        return movimentoMagazzinoService.filterByUtente(utenteId);
    }

    @GetMapping("/filtra/quantita")
    public List<MovimentoMagazzino> filterByMinQuantity(@RequestParam Double minQuantity) {
        return movimentoMagazzinoService.filterByMinQuantity(minQuantity);
    }

    @GetMapping("/filtra/prodotto-magazzino/{pmId}")
    public List<MovimentoMagazzino> filterByProdottoMagazzino(@PathVariable Long pmId) {
        return movimentoMagazzinoService.filterByProdottoMagazzino(pmId);
    }

    @GetMapping("/filtra/storico/{storicoId}")
    public List<MovimentoMagazzino> filterByStoricoPercorrenza(@PathVariable Long storicoId) {
        return movimentoMagazzinoService.filterByStoricoPercorrenza(storicoId);
    }


}