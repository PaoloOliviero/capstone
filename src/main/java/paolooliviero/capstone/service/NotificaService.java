package paolooliviero.capstone.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.Notifica;
import paolooliviero.capstone.entities.OrdineCliente;
import paolooliviero.capstone.entities.Utente;
import paolooliviero.capstone.exceptions.NotFoundException;
import paolooliviero.capstone.payloads.NewNotificaRespDTO;
import paolooliviero.capstone.repositories.NotificaRepository;
import paolooliviero.capstone.repositories.OrdineClienteRepository;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class NotificaService {

    @Autowired
    private NotificaRepository notificaRepository;

    @Autowired
    private UtenteService utenteService;
    private OrdineClienteRepository ordineClienteRepository;

    public NewNotificaRespDTO creaNotifica(NewNotificaRespDTO payload, OrdineCliente ordine) {

        Notifica notifica = new Notifica();
        notifica.setTitolo(payload.titolo());
        notifica.setDescrizione(payload.descrizione());
        notifica.setVisualizzata(false);
        notifica.setData(LocalDateTime.now());
        notifica.setOrdineCliente(ordine);

        Notifica salvata = notificaRepository.save(notifica);

        return new NewNotificaRespDTO(
                salvata.getId(),
                salvata.getTitolo(),
                salvata.getDescrizione(),
                salvata.isVisualizzata(),
                salvata.getData(),
                salvata.getOrdineCliente() != null && salvata.getOrdineCliente().getCliente() != null
                        ? salvata.getOrdineCliente().getCliente().getRagioneSociale()
                        : "",
                ""
        );
    }

    public List<NewNotificaRespDTO> getNonVisualizzate(Long utenteId) {
        Utente utente = utenteService.findById(utenteId);
        return notificaRepository.findByUtenteDestinatarioAndVisualizzataFalse(utente)
                .stream()
                .map(n -> new NewNotificaRespDTO(
                        n.getId(),
                        n.getTitolo(),
                        n.getDescrizione(),
                        n.isVisualizzata(),
                        n.getData(),
                        n.getOrdineCliente() != null && n.getOrdineCliente().getCliente() != null
                                ? n.getOrdineCliente().getCliente().getRagioneSociale()
                                : null,
                        n.getUtenteDestinatario() != null
                                ? n.getUtenteDestinatario().getEmail()
                                : null
                ))
                .toList();
    }

    public void segnaComeVisualizzata(Long id) {
        Notifica notifica = notificaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        notifica.setVisualizzata(true);
        notificaRepository.save(notifica);
    }

    public void creaNotificaPerTutti(NewNotificaRespDTO payload,  OrdineCliente ordine) {
        List<Utente> utenti = utenteService.findAll();

        for (Utente utente : utenti) {
            Notifica notifica = new Notifica();
            notifica.setTitolo(payload.titolo());
            notifica.setDescrizione(payload.descrizione());
            notifica.setVisualizzata(false);
            notifica.setData(LocalDateTime.now());
            notifica.setUtenteDestinatario(utente);
            notifica.setOrdineCliente(ordine);

            notificaRepository.save(notifica);
        }

        log.info("Notifica inviata a {} utenti", utenti.size());
    }

    public List<NewNotificaRespDTO> getTutte() {
        List<Notifica> notifiche = notificaRepository.findAll();
        return notifiche.stream()
                .map(n -> new NewNotificaRespDTO(
                        n.getId(),
                        n.getTitolo(),
                        n.getDescrizione(),
                        n.isVisualizzata(),
                        n.getData(),
                        n.getOrdineCliente() != null && n.getOrdineCliente().getCliente() != null
                                ? n.getOrdineCliente().getCliente().getRagioneSociale()
                                : null,
                        n.getUtenteDestinatario() != null
                                ? n.getUtenteDestinatario().getEmail()
                                : null
                ))
                .toList();
    }
}