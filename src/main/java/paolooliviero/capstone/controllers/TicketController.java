package paolooliviero.capstone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolooliviero.capstone.entities.Ticket;
import paolooliviero.capstone.payloads.NewTicketDTO;
import paolooliviero.capstone.payloads.NewTicketRespDTO;
import paolooliviero.capstone.service.TicketService;

import jakarta.validation.ValidationException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public Page<Ticket> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        return ticketService.findAll(page, size, sortBy);
    }

    @PostMapping("/crea")
    @ResponseStatus(HttpStatus.CREATED)
    public NewTicketRespDTO save(@RequestBody @Validated NewTicketDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException("Errore di validazione");
        } else {
            Ticket newTicket = ticketService.saveManuale(payload);;
            return new NewTicketRespDTO(
                    newTicket.getId(),
                    newTicket.getTitolo(),
                    newTicket.getDescrizione(),
                    newTicket.getDataCreazione(),
                    newTicket.getOrdineCliente().getId()
            );
        }
    }

    @GetMapping("/{ticketId}")
    public Ticket getById(@PathVariable long ticketId) {
        return ticketService.findById(ticketId);
    }


    @GetMapping("/filtrotitolo")
    public List<Ticket> filtroTitolo(@RequestParam String titolo) {
        return ticketService.filtroTitolo(titolo);
    }

    @GetMapping("/filtrodescrizione")
    public List<Ticket> filtroDescrizione(@RequestParam String descrizione) {
        return ticketService.filtroDescrizione(descrizione);
    }

    @GetMapping("/filtrodata")
    public List<Ticket> filtroDataCreazione(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataCreazione) {
        return ticketService.filtroDataCreazione(dataCreazione);
    }

    @GetMapping("/filtroordine")
    public List<Ticket> filtroOrdineCliente(@RequestParam Long ordineId) {
        return ticketService.filtroOrdineCliente(ordineId);
    }

    @GetMapping("/con-relazioni")
    public List<Ticket> findAllConRelazioni() {
        return ticketService.findAllConJoin();
        }
    }

