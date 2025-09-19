package paolooliviero.capstone.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.Autista;
import paolooliviero.capstone.entities.Carico;
import paolooliviero.capstone.exceptions.NotFoundException;
import paolooliviero.capstone.payloads.NewAutistaDTO;
import paolooliviero.capstone.payloads.NewCaricoDTO;
import paolooliviero.capstone.repositories.AutistaRepository;

@Service
@Slf4j
public class AutistaService {

    @Autowired
    private AutistaRepository autistaRepository;

    public Autista save (NewAutistaDTO payload) {

        Autista newAutista= new Autista();
        newAutista.setNome(payload.nome());
        newAutista.setCognome(payload.cognome());
        newAutista.setTelefono(payload.telefono());

        return autistaRepository.save(newAutista);
    }

    public Page<Autista> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return this.autistaRepository.findAll(pageable);
    }

    public Autista findById(long Id) {
        return this.autistaRepository.findById(Id).orElseThrow(() -> new NotFoundException(Id));
    }

    public void findByIdAndDelete(long caricoId) {
        Autista found = this.findById(caricoId);
        this.autistaRepository.delete(found);
    }

    public Autista findByIdAndUpdate(long autistaId, NewAutistaDTO payload) {
        Autista found = this.findById (autistaId);

        found.setNome(payload.nome());
        found.setCognome(payload.cognome());
        found.setTelefono(payload.telefono());

        return autistaRepository.save(found);
    }

}
