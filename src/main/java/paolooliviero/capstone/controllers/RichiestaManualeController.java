package paolooliviero.capstone.controllers;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import paolooliviero.capstone.payloads.NewRichiestaProdottoDTO;
import paolooliviero.capstone.payloads.NewRichiestaProdottoRespDTO;
import paolooliviero.capstone.entities.EventoMancanzaProdotto;
import paolooliviero.capstone.service.RichiestaManualeService;

@RestController
@RequestMapping("/richieste-prodotti")
public class RichiestaManualeController {

    @Autowired
    private RichiestaManualeService richiestaManualeService;

    @PostMapping("/creamanuale")
    @ResponseStatus(HttpStatus.CREATED)
    public NewRichiestaProdottoRespDTO save(@RequestBody @Validated NewRichiestaProdottoDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException("Validazione fallita");
        }
        EventoMancanzaProdotto eventoCreato = richiestaManualeService.save(payload);
        return new NewRichiestaProdottoRespDTO(eventoCreato.getId());
    }
}


