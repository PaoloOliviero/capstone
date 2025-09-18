package paolooliviero.capstone.controllers;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolooliviero.capstone.entities.Cliente;
import paolooliviero.capstone.entities.Magazzino;
import paolooliviero.capstone.payloads.NewClienteDTO;
import paolooliviero.capstone.payloads.NewClienteRespDTO;
import paolooliviero.capstone.payloads.NewMagazzinoDTO;
import paolooliviero.capstone.payloads.NewMagazzinoRespDTO;
import paolooliviero.capstone.service.ClienteService;
import paolooliviero.capstone.service.MagazzinoService;

@RestController
@RequestMapping("/magazzini")
public class MagazzinoController {

    @Autowired
    private MagazzinoService magazzinoService;

    @GetMapping
//    @PreAuthorize("hasAuthority('')")
    public Page<Magazzino> findAll(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String sortBy) {
        return magazzinoService.findAll(page, size, sortBy);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewMagazzinoRespDTO save(@RequestBody @Validated NewMagazzinoDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException("Validazione fallita");
        }
        Magazzino newMagazzino = magazzinoService.save(payload);
        return new NewMagazzinoRespDTO(newMagazzino.getId());
    }

    @GetMapping("/{magazzinoId}")
//    @PreAuthorize("hasAuthority('')")
    public Magazzino getById(@PathVariable long magazzinoId) {
        return magazzinoService.findById(magazzinoId);
    }

    @PutMapping("/{magazzinoId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public Magazzino getByIdAndUpdate(@PathVariable long magazzinoId, @RequestBody NewMagazzinoDTO payload) {
        return this.magazzinoService.findByIdAndUpdate(magazzinoId, payload);
    }

    @DeleteMapping("/{magazzinoId}")
//    @PreAuthorize("hasAuthority('')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable long magazzinoId) {
        this.magazzinoService.findByIdAndDelete(magazzinoId);
    }
}