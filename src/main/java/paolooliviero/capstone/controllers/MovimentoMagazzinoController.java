package paolooliviero.capstone.controllers;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @PostMapping
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
                utente.getNome(),
                movimento.getDataRegistrazione(),
                null, null, null, null
        );
    }
}