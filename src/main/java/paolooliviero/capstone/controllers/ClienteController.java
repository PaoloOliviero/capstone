package paolooliviero.capstone.controllers;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolooliviero.capstone.entities.Cliente;
import paolooliviero.capstone.payloads.NewClienteDTO;
import paolooliviero.capstone.payloads.NewClienteRespDTO;
import paolooliviero.capstone.service.ClienteService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/clienti")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public Page<NewClienteRespDTO> findAll(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size,
                                           @RequestParam(defaultValue = "id") String sortBy) {
        return clienteService.findAll(page, size, sortBy);
    }

    @PostMapping("/creacliente")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public NewClienteRespDTO save(@RequestBody @Validated NewClienteDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException("Validazione fallita");
        }
        Cliente newCliente = clienteService.save(payload);
        return new NewClienteRespDTO(    newCliente.getId(),
                newCliente.getRagioneSociale(),
                newCliente.getPartitaIva(),
                newCliente.getEmail(),
                newCliente.getDataInserimento(),
                newCliente.getDataUltimoContatto(),
                newCliente.getFatturatoAnnuale(),
                newCliente.getTelefonoContatto()
        );
    }

    @GetMapping("/{clienteId}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public Cliente getById(@PathVariable long clienteId) {
        return clienteService.findById(clienteId);
    }

    @PutMapping("/{clienteId}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public Cliente getByIdAndUpdate(@PathVariable long clienteId, @RequestBody NewClienteDTO payload) {
        return this.clienteService.findByIdAndUpdate(clienteId, payload);
    }

    @DeleteMapping("/{clienteId}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable long clienteId) {
        this.clienteService.findByIdAndDelete(clienteId);
    }

    @GetMapping("/filtro/fatturato")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public List<Cliente> filtraPerFatturato(@RequestParam int fatturatoMax) {
        return clienteService.filterToFatturato(fatturatoMax);
    }

    @GetMapping("/filtro/data-inserimento")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public List<Cliente> filtraPerDataInserimento(@RequestParam LocalDate data) {
        return clienteService.filterToDataInserimento(data);
    }

    @GetMapping("/filtro/ultimo-contatto")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public List<Cliente> filtraPerUltimoContatto(@RequestParam LocalDate data) {
        return clienteService.filterToDataUltimoContatto(data);
    }

    @GetMapping("/filtro/nome")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public List<Cliente> filtraPerRagioneSociale(@RequestParam String ragioneSociale) {
        return clienteService.filterToRagioneSociale(ragioneSociale);
    }

}
