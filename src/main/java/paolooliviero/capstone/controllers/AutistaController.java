package paolooliviero.capstone.controllers;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolooliviero.capstone.entities.Autista;
import paolooliviero.capstone.payloads.NewAutistaDTO;
import paolooliviero.capstone.payloads.NewAutistaRespDTO;
import paolooliviero.capstone.service.AutistaService;

public class AutistaController {

    @Autowired
    private AutistaService autistaService;

    @GetMapping
    @PreAuthorize("hasRole('USER_ADMIN')")
    public Page<Autista> findAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String sortBy) {
        return autistaService.findAll(page, size, sortBy);
    }

    @PostMapping("/creaautista")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    @ResponseStatus(HttpStatus.CREATED)
    public NewAutistaRespDTO save(@RequestBody @Validated NewAutistaDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException("Validazione fallita");
        }
        Autista newAutista = autistaService.save(payload);
        return new NewAutistaRespDTO(newAutista.getId());
    }

    @GetMapping("/{autistaId}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public Autista getById(@PathVariable long autistaId) {
        return autistaService.findById(autistaId);
    }

    @PutMapping("/{autistaId}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public Autista getByIdAndUpdate(@PathVariable long autistaId, @RequestBody NewAutistaDTO payload) {
        return this.autistaService.findByIdAndUpdate(autistaId, payload);
    }

    @DeleteMapping("/{autistaId}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable long autistaId) {
        this.autistaService.findByIdAndDelete(autistaId);
    }
}

