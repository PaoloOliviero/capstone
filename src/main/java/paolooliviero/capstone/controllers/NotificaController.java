package paolooliviero.capstone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolooliviero.capstone.entities.OrdineCliente;
import paolooliviero.capstone.entities.Utente;
import paolooliviero.capstone.exceptions.NotFoundException;
import paolooliviero.capstone.payloads.NewNotificaRespDTO;
import paolooliviero.capstone.repositories.OrdineClienteRepository;
import paolooliviero.capstone.service.NotificaService;
import paolooliviero.capstone.service.UtenteService;

import java.util.List;

@RestController
@RequestMapping("/notifiche")
public class NotificaController {

    @Autowired
    private NotificaService notificaService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private OrdineClienteRepository ordineClienteRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewNotificaRespDTO crea(@RequestBody NewNotificaRespDTO payload) {
        OrdineCliente ordine = ordineClienteRepository.findById(payload.id())
                .orElseThrow(() -> new NotFoundException("Ordine non trovato"));

        return notificaService.creaNotifica(payload, ordine);
    }

    @PutMapping("/{id}/visualizzata")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void segnaComeVisualizzata(@PathVariable Long id) {
        notificaService.segnaComeVisualizzata(id);
    }

    @GetMapping
    public List<NewNotificaRespDTO> getTutteLeNotifiche() {
        return notificaService.getTutte();
    }


}