package paolooliviero.capstone.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import paolooliviero.capstone.entities.Utente;
import paolooliviero.capstone.payloads.NewCaricoRespDTO;
import paolooliviero.capstone.payloads.NewUtenteRespDTO;
import paolooliviero.capstone.payloads.UtenteDTO;
import paolooliviero.capstone.service.UtenteService;


@RestController
@RequestMapping("/utenti")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public Page<Utente> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "20") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        return utenteService.findAll(page, size, sortBy);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewUtenteRespDTO save(@Valid @RequestBody UtenteDTO payload, BindingResult validationResult) {
        Utente newUtente = utenteService.save(payload);
        return new NewUtenteRespDTO(newUtente.getId());
    }

    @GetMapping("/{utenteId}")
    public Utente getById(@PathVariable long utenteId) {
        return utenteService.findById(utenteId);
    }

    @PutMapping("/{utenteId}")
    public Utente getByIdAndUpdate(@PathVariable long utenteId, @RequestBody UtenteDTO payload) {
        return utenteService.findByIdAndUpdate(utenteId, payload);
    }

    @DeleteMapping("/{utenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable long utenteId) {
        utenteService.findByIdAndDelete(utenteId);
    } }
