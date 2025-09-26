package paolooliviero.capstone.controllers;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolooliviero.capstone.entities.OrdineCliente;
import paolooliviero.capstone.entities.Utente;
import paolooliviero.capstone.enums.StatoOrdine;
import paolooliviero.capstone.payloads.NewOrdineClienteDTO;
import paolooliviero.capstone.payloads.NewOrdineClienteRespDTO;
import paolooliviero.capstone.payloads.OrdineClassificatoDTO;
import paolooliviero.capstone.repositories.OrdineClienteRepository;
import paolooliviero.capstone.service.OrdineClienteService;
import paolooliviero.capstone.service.UtenteService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ordinicliente")
public class OrdineClienteController {

    @Autowired
    private OrdineClienteService ordineClienteService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private OrdineClienteRepository ordineClienteRepository;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public Page<OrdineCliente> findAll(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String sortBy) {
        return ordineClienteService.findAll(page, size, sortBy);
    }


    @PostMapping("/creamagazzino")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    @ResponseStatus(HttpStatus.CREATED)
    public NewOrdineClienteRespDTO save(@RequestBody @Validated NewOrdineClienteDTO payload,
                                        BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException("Validazione fallita");
        }
        OrdineCliente newOrdineCliente = ordineClienteService.save(payload);
        return new NewOrdineClienteRespDTO(newOrdineCliente.getId(),
                newOrdineCliente.getDataOrdine(),
                newOrdineCliente.getStatoOrdine(),
                newOrdineCliente.getCliente() != null ? newOrdineCliente.getCliente().getRagioneSociale() : null,
                newOrdineCliente.getFattura() != null ? newOrdineCliente.getFattura().getImporto() : null,
                newOrdineCliente.getSegmento() != null ? newOrdineCliente.getSegmento().getId() : null

        );
    }


    @GetMapping("/{ordineClienteId}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public OrdineCliente getById(@PathVariable long ordineClienteId) {
        return ordineClienteService.findById(ordineClienteId);
    }

    @PutMapping("/{ordineClienteId}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public OrdineCliente getByIdAndUpdate(@PathVariable long ordineClienteId,
                                          @RequestBody NewOrdineClienteDTO payload) {
        return ordineClienteService.findByIdAndUpdate(ordineClienteId, payload);
    }

    @DeleteMapping("/{ordineClienteId}")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable long ordineClienteId) {
        ordineClienteService.findByIdAndDelete(ordineClienteId);
    }

    @PostMapping("/{ordineId}/classifica")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public OrdineClassificatoDTO ordineClassificatoDTO(@PathVariable Long ordineId,
                                            @RequestParam Long segmentoId) {
        return new OrdineClassificatoDTO(ordineClienteService.classificaOrdine(ordineId, segmentoId));
    }

    @GetMapping("/con-relazioni")
    @PreAuthorize("hasAnyRole('USER_ADMIN', 'USER_OPERATORE', 'USER_COMMERCIALE')")
    public List<NewOrdineClienteRespDTO> findAllConRelazioniDTO() {
        return ordineClienteService.findAllConRelazioniDTO();
    }

    @GetMapping("/filtro/data-ordine")
    public List<OrdineCliente> filtraPerDataOrdine(@RequestParam LocalDate dataOrdine) {
        return ordineClienteService.filterByDataOrdine(dataOrdine);
    }

    @GetMapping("/filtro/stato")
    public List<OrdineCliente> filtraPerStatoOrdine(@RequestParam StatoOrdine statoOrdine) {
        return ordineClienteService.filterByStatoOrdine(statoOrdine);
    }

    @GetMapping("/filtro/ragione-sociale")
    public List<OrdineCliente> filtraPerRagioneSociale(@RequestParam String ragioneSociale) {
        return ordineClienteService.filterByRagioneSociale(ragioneSociale);
    }

    @GetMapping("/filtro/importo")
    public List<OrdineCliente> filtraPerImportoFattura(@RequestParam Double importo) {
        return ordineClienteService.filterByImportoFattura(importo);
    }

    @GetMapping("/filtro/segmento")
    public List<OrdineCliente> filtraPerSegmento(@RequestParam Long segmentoId) {
        return ordineClienteService.filterBySegmento(segmentoId);
    }


}


