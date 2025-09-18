package paolooliviero.capstone.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.Ruolo;
import paolooliviero.capstone.entities.Utente;
import paolooliviero.capstone.exceptions.BadRequestException;
import paolooliviero.capstone.exceptions.NotFoundException;
import paolooliviero.capstone.payloads.UtenteDTO;
import paolooliviero.capstone.repositories.UtenteRepository;
import paolooliviero.capstone.security.MailgunSender;



import java.util.List;

@Service
@Slf4j
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private MailgunSender mailgunSender;

    @Autowired
    private RuoloService ruoloService;

    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }

    public Utente findById(Long id) {
        return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Page<Utente> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return utenteRepository.findAll(pageable);
    }

    public Utente findByIdOrThrow(Long id) {
        return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException("Utente non trovato"));
    }

    public Utente save(UtenteDTO payload) {
        // 1. Verifico che l'email non sia già in uso
        this.utenteRepository.findByEmail(payload.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
        });

        List<Ruolo> ruoliEntity = payload.ruoli().stream()
                .map(ruoloService::findByNome)
                .toList();

        // 2. Aggiungo valori server-generated
        Utente newUtente = new Utente();
        newUtente.setNome(payload.nome());
        newUtente.setCognome(payload.cognome());
        newUtente.setEmail(payload.email());
        newUtente.setPassword(bcrypt.encode(payload.password()));
        newUtente.setRuoli(ruoliEntity);

        // 3. Salvo
        Utente savedUtente = this.utenteRepository.save(newUtente);

        // 4. Log
        log.info("L'utente con id: " + savedUtente.getId() + " è stato salvato correttamente!");

        // 5. Invio email di avvenuta registrazione
        mailgunSender.sendRegistrationEmail(savedUtente);

        // 6. Ritorno l'utente salvato
        return savedUtente;
    }


    public Utente findByIdAndUpdate(long id, UtenteDTO dto) {
        Utente found = utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        found.setEmail(dto.email());
        found.setNome(dto.nome());
        found.setCognome(dto.cognome());
        return utenteRepository.save(found);
    }

    public void findByIdAndDelete(Long id) {
        Utente utente = findByIdOrThrow(id);
        utenteRepository.delete(utente);
    }

    public void deleteById(Long id) {
        utenteRepository.deleteById(id);
    }

    public Utente findByEmail(String email) {
        return this.utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("L'utente con l'email " + email + " non è stato trovato!"));

    }
}