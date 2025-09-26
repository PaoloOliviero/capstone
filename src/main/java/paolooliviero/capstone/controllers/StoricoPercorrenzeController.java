package paolooliviero.capstone.controllers;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolooliviero.capstone.entities.StoricoPercorrenze;
import paolooliviero.capstone.payloads.NewStoricoPercorrenzaDTO;
import paolooliviero.capstone.service.StoricoPercorrenzeService;

@RestController
@RequestMapping("/storico-percorrenze")
public class StoricoPercorrenzeController {

    @Autowired
    private StoricoPercorrenzeService storicoService;

    @GetMapping
    public Page<StoricoPercorrenze> findAll(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "id") String sortBy) {
        return storicoService.findAll(page, size, sortBy);
    }

    @PostMapping("/creastorico")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    @ResponseStatus(HttpStatus.CREATED)
    public StoricoPercorrenze save(@RequestBody @Validated NewStoricoPercorrenzaDTO payload,
                                   BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException("Errore di validazione");
        }
        return storicoService.save(payload);
    }

    @GetMapping("/{storicoId}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public StoricoPercorrenze getById(@PathVariable long storicoId) {
        return storicoService.findById(storicoId);
    }

    @PutMapping("/{storicoId}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public StoricoPercorrenze getByIdAndUpdate(@PathVariable long storicoId,
                                               @RequestBody NewStoricoPercorrenzaDTO payload) {
        return storicoService.findByIdAndUpdate(storicoId, payload);
    }

    @DeleteMapping("/{storicoId}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable long storicoId) {
        storicoService.findByIdAndDelete(storicoId);
    }
}