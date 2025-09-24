package paolooliviero.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.OrdineCliente;
import paolooliviero.capstone.entities.ProdottoMagazzino;
import paolooliviero.capstone.entities.Ticket;
import paolooliviero.capstone.entities.Utente;
import paolooliviero.capstone.exceptions.NotFoundException;
import paolooliviero.capstone.payloads.NewTicketDTO;
import paolooliviero.capstone.repositories.TicketRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UtenteService utenteService;

    public void creaTicketPerTutti(OrdineCliente ordine) {
        List<Utente> utenti = utenteService.findAll();

        for (Utente utente : utenti) {
            Ticket ticket = new Ticket();
            ticket.setTitolo("Ticket per ordine #" + ordine.getId());
            ticket.setDescrizione("Segmentato come " + ordine.getSegmento().getNome());
            ticket.setDataCreazione(LocalDateTime.now());
            ticket.setOrdineCliente(ordine);

            ticketRepository.save(ticket);
        }
    }

    public Ticket findById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public Ticket saveManuale(NewTicketDTO payload) {
        Ticket ticket = new Ticket();
        ticket.setTitolo(payload.titolo());
        ticket.setDescrizione(payload.descrizione());
        ticket.setDataCreazione(LocalDateTime.now());
        return ticketRepository.save(ticket);
    }

    public Page<Ticket> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        return this.ticketRepository.findAll(pageable);
    }
}
