package paolooliviero.capstone.controllers;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolooliviero.capstone.entities.Magazzino;
import paolooliviero.capstone.payloads.NewMagazzinoDTO;
import paolooliviero.capstone.payloads.NewMagazzinoRespDTO;
import paolooliviero.capstone.service.MagazzinoService;

@RestController
@RequestMapping("/magazzini")
@PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
public class MagazzinoController {

    @Autowired
    private MagazzinoService magazzinoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public Page<Magazzino> findAll(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String sortBy) {
        return magazzinoService.findAll(page, size, sortBy);
    }

    @PostMapping("/creamagazzino")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public NewMagazzinoRespDTO save(@RequestBody @Validated NewMagazzinoDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException("Validazione fallita");
        }
        Magazzino newMagazzino = magazzinoService.save(payload);
        return new NewMagazzinoRespDTO(newMagazzino.getId());
    }

    @GetMapping("/{magazzinoId}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public Magazzino getById(@PathVariable long magazzinoId) {
        return magazzinoService.findById(magazzinoId);
    }

    @PutMapping("/{magazzinoId}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public Magazzino getByIdAndUpdate(@PathVariable long magazzinoId, @RequestBody NewMagazzinoDTO payload) {
        return this.magazzinoService.findByIdAndUpdate(magazzinoId, payload);
    }

    @DeleteMapping("/{magazzinoId}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable long magazzinoId) {
        this.magazzinoService.findByIdAndDelete(magazzinoId);
    }
}