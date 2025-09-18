package paolooliviero.capstone.controllers;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolooliviero.capstone.entities.Fattura;
import paolooliviero.capstone.payloads.NewFatturaDTO;
import paolooliviero.capstone.payloads.NewFatturaRespDTO;
import paolooliviero.capstone.service.FatturaService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/fatture")
public class FatturaController {

    @Autowired
    private FatturaService fatturaService;

    @GetMapping
//    @PreAuthorize("hasAuthority('')")
    public Page<Fattura> findAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String sortBy
    ) {
        return (Page<Fattura>) this.fatturaService.findAll(page, size, sortBy);
    }

    @PostMapping("/creafattura")
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasAuthority('')")
    public NewFatturaRespDTO save(@RequestBody @Validated NewFatturaDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException("Errore");
        } else {
            Fattura newFattura = this.fatturaService.save(payload);
            return new NewFatturaRespDTO(newFattura.getId(), newFattura.getData(),
                    newFattura.getImporto()
            );
        }
    }

    @GetMapping("/{fatturaId}")
//    @PreAuthorize("hasAuthority('')")
    public Fattura getById(@PathVariable long fatturaId) {
        return this.fatturaService.findById(fatturaId);
    }

    @PutMapping("/{fatturaId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public Fattura getByIdAndUpdate(@PathVariable long userId, @RequestBody NewFatturaDTO payload) {
        return this.fatturaService.findByIdAndUpdate(userId, payload);
    }

    @DeleteMapping("/{fatturaId}")
//    @PreAuthorize("hasAuthority('')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable long fatturaId) {
        this.fatturaService.findByIdAndDelete(fatturaId);
    } }

