package paolooliviero.capstone.controllers;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolooliviero.capstone.entities.OrdineCliente;
import paolooliviero.capstone.payloads.*;
import paolooliviero.capstone.service.OrdineClienteService;


@RestController
@RequestMapping("/ordinicliente")
public class OrdineClienteController {
    @Autowired
    private OrdineClienteService ordineClienteService;

    @GetMapping
//    @PreAuthorize("hasAuthority('')")
    public Page<OrdineCliente> findAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String sortBy) {
        return (Page<OrdineCliente>) ordineClienteService.findAll(page, size, sortBy);
    }

    @PostMapping("/creamagazzino")
    @ResponseStatus(HttpStatus.CREATED)
    public NewOrdineClienteRespDTO save(@RequestBody @Validated NewOrdineClienteDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException("Validazione fallita");
        }
        OrdineCliente newOrdineCliente = ordineClienteService.save(payload);
        return new NewOrdineClienteRespDTO(newOrdineCliente.getId());
    }

    @GetMapping("/{ordineClienteId}")
//    @PreAuthorize("hasAuthority('')")
    public OrdineCliente getById(@PathVariable long ordineClienteId) {
        return ordineClienteService.findById(ordineClienteId);
    }

    @PutMapping("/{ordineClienteId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public OrdineCliente getByIdAndUpdate(@PathVariable long ordineClienteId, @RequestBody NewOrdineClienteDTO payload) {
        return this.ordineClienteService.findByIdAndUpdate(ordineClienteId, payload);
    }

    @DeleteMapping("/{ordineClienteId}")
//    @PreAuthorize("hasAuthority('')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable long clienteId) {
        this.ordineClienteService.findByIdAndDelete(clienteId);
    }

}
