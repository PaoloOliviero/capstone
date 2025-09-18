package paolooliviero.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.Ruolo;
import paolooliviero.capstone.exceptions.NotFoundException;
import paolooliviero.capstone.repositories.RuoloRepository;

@Service
public class RuoloService {
    @Autowired
    private RuoloRepository ruoloRepository;

    public Ruolo save(Ruolo ruolo) {
        return ruoloRepository.save(ruolo);
    }

    public Ruolo findById(Long ruoloId) {
        return ruoloRepository.findById(ruoloId).orElseThrow(() -> new NotFoundException(ruoloId));
    }

    public Ruolo findByNome(String nome) {
        return ruoloRepository.findByNome(nome)
                .orElseThrow(() -> new NotFoundException("Ruolo '" + nome + "' non trovato"));
    }



}
