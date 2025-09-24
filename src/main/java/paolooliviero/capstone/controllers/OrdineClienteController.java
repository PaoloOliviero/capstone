package paolooliviero.capstone.controllers;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolooliviero.capstone.entities.OrdineCliente;
import paolooliviero.capstone.entities.Utente;
import paolooliviero.capstone.payloads.NewOrdineClienteDTO;
import paolooliviero.capstone.payloads.NewOrdineClienteRespDTO;
import paolooliviero.capstone.payloads.OrdineClassificatoDTO;
import paolooliviero.capstone.service.OrdineClienteService;
import paolooliviero.capstone.service.UtenteService;

@RestController
@RequestMapping("/ordinicliente")
public class OrdineClienteController {

    @Autowired
    private OrdineClienteService ordineClienteService;

    @Autowired
    private UtenteService utenteService;

    // 🔍 Recupera tutti gli ordini cliente con paginazione
    @GetMapping
    public Page<OrdineCliente> findAll(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String sortBy) {
        return ordineClienteService.findAll(page, size, sortBy);
    }

    // 🧾 Crea un nuovo ordine cliente
    @PostMapping("/creamagazzino")
    @ResponseStatus(HttpStatus.CREATED)
    public NewOrdineClienteRespDTO save(@RequestBody @Validated NewOrdineClienteDTO payload,
                                        BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException("Validazione fallita");
        }
        OrdineCliente newOrdineCliente = ordineClienteService.save(payload);
        return new NewOrdineClienteRespDTO(newOrdineCliente.getId());
    }

    // 🔍 Recupera un ordine cliente per ID
    @GetMapping("/{ordineClienteId}")
    public OrdineCliente getById(@PathVariable long ordineClienteId) {
        return ordineClienteService.findById(ordineClienteId);
    }

    // ✏️ Aggiorna un ordine cliente
    @PutMapping("/{ordineClienteId}")
    public OrdineCliente getByIdAndUpdate(@PathVariable long ordineClienteId,
                                          @RequestBody NewOrdineClienteDTO payload) {
        return ordineClienteService.findByIdAndUpdate(ordineClienteId, payload);
    }

    // 🗑️ Elimina un ordine cliente
    @DeleteMapping("/{ordineClienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable long ordineClienteId) {
        ordineClienteService.findByIdAndDelete(ordineClienteId);
    }

    @PostMapping("/{ordineId}/classifica")
    public OrdineClassificatoDTO ordineClassificatoDTO(@PathVariable Long ordineId,
                                            @RequestParam Long segmentoId) {
        return new OrdineClassificatoDTO(ordineClienteService.classificaOrdine(ordineId, segmentoId));
    }
}


