package paolooliviero.capstone.controllers;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolooliviero.capstone.entities.Carico;
import paolooliviero.capstone.payloads.NewCaricoDTO;
import paolooliviero.capstone.payloads.NewCaricoRespDTO;
import paolooliviero.capstone.service.CaricoService;


@RestController
@RequestMapping("/carichi")
public class CaricoController {

    @Autowired
    private CaricoService caricoService;

    @GetMapping
//    @PreAuthorize("hasAuthority('')")
    public Page<Carico> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        return caricoService.findAll(page, size, sortBy);
    }

    @PostMapping("/creacarico")
    @ResponseStatus(HttpStatus.CREATED)
    public NewCaricoRespDTO save(@RequestBody @Validated NewCaricoDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException("Validazione fallita");
        }
        Carico newCarico = caricoService.save(payload);
        return new NewCaricoRespDTO(newCarico.getId());
    }

    @GetMapping("/{caricoId}")
//    @PreAuthorize("hasAuthority('')")
    public Carico getById(@PathVariable long caricoId) {
        return caricoService.findById(caricoId);
    }

    @PutMapping("/{caricoId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public Carico getByIdAndUpdate(@PathVariable long caricoId, @RequestBody NewCaricoDTO payload) {
        return this.caricoService.findByIdAndUpdate(caricoId, payload);
    }

    @DeleteMapping("/{caricoId}")
//    @PreAuthorize("hasAuthority('')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable long caricoId) {
        this.caricoService.findByIdAndDelete(caricoId);
    }
}
