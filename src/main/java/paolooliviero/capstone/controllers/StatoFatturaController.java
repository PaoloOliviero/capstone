package paolooliviero.capstone.controllers;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolooliviero.capstone.entities.StatoFattura;
import paolooliviero.capstone.payloads.NewStatoFatturaDTO;
import paolooliviero.capstone.payloads.NewStatoFatturaRespDTO;
import paolooliviero.capstone.service.StatoFatturaService;

@RestController
@RequestMapping("/statofatture")
public class StatoFatturaController {
    @Autowired
    private StatoFatturaService statoFatturaService;

    @GetMapping
    public Page<StatoFattura> findAll(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "20") int size,
                                      @RequestParam(defaultValue = "id") String sortBy) {
        return statoFatturaService.findAll(page, size, sortBy);
    }

    @PostMapping("/creastatofattura")
    @ResponseStatus(HttpStatus.CREATED)
    public NewStatoFatturaRespDTO save(@RequestBody @Validated NewStatoFatturaDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException("Validazione fallita");
        }
        StatoFattura newStatoFattura = statoFatturaService.save(payload);;
        return new NewStatoFatturaRespDTO(newStatoFattura.getId());
    }

    @GetMapping("/{id}")
    public StatoFattura getById(@PathVariable long id) {
        return statoFatturaService.findById(id);
    }

    @PutMapping("/{id}")
    public StatoFattura getByIdAndUpdate(@PathVariable long id, @RequestBody NewStatoFatturaDTO payload) {

        return statoFatturaService.findByIdAndUpdate(id, payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable long id) {
        statoFatturaService.findByIdAndDelete(id);
    }
}
