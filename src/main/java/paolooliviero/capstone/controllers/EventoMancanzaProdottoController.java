package paolooliviero.capstone.controllers;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolooliviero.capstone.entities.EventoMancanzaProdotto;
import paolooliviero.capstone.payloads.NewEventoMancanzaProdottoDTO;
import paolooliviero.capstone.payloads.NewEventoMancanzaProdottoRespDTO;
import paolooliviero.capstone.service.EventoMancanzaProdottoService;

@RestController
@RequestMapping("/eventomancanzaprodotto")
public class EventoMancanzaProdottoController {

    @Autowired
    private EventoMancanzaProdottoService eventoMancanzaProdottoService;

    @GetMapping
//    @PreAuthorize("hasAuthority('')")
    public Page<EventoMancanzaProdotto> findAll(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(defaultValue = "id") String sortBy) {
        return eventoMancanzaProdottoService.findAll(page, size, sortBy);
    }

    @PostMapping("/creaeventomancanzaprodotto")
    @ResponseStatus(HttpStatus.CREATED)
    public NewEventoMancanzaProdottoRespDTO save(@RequestBody @Validated NewEventoMancanzaProdottoDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException("Validazione fallita");
        }
        EventoMancanzaProdotto newEventoMancanzaProdotto = eventoMancanzaProdottoService.save(payload);
        return new NewEventoMancanzaProdottoRespDTO(newEventoMancanzaProdotto.getId());
    }

    @GetMapping("/{eventoMancanzaProdottoId}")
//    @PreAuthorize("hasAuthority('')")
    public EventoMancanzaProdotto getById(@PathVariable long eventoMancanzaProdottoId) {
        return eventoMancanzaProdottoService.findById(eventoMancanzaProdottoId);
    }

    @PutMapping("/{eventoMancanzaProdottoId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public EventoMancanzaProdotto getByIdAndUpdate(@PathVariable long eventoMancanzaProdottoId, @RequestBody NewEventoMancanzaProdottoDTO payload) {
        return this.eventoMancanzaProdottoService.findByIdAndUpdate(eventoMancanzaProdottoId, payload);
    }

    @DeleteMapping("/{EventoMancanzaProdottoId}")
//    @PreAuthorize("hasAuthority('')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable long eventoMancanzaProdottoId) {
        this.eventoMancanzaProdottoService.findByIdAndDelete(eventoMancanzaProdottoId);
    }
}
