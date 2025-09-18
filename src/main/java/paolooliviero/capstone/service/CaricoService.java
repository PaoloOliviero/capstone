package paolooliviero.capstone.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.Carico;
import paolooliviero.capstone.entities.Cliente;
import paolooliviero.capstone.exceptions.NotFoundException;
import paolooliviero.capstone.payloads.NewCaricoDTO;
import paolooliviero.capstone.payloads.NewClienteDTO;
import paolooliviero.capstone.repositories.CaricoRepository;


@Service
@Slf4j
public class CaricoService {

    @Autowired
    private CaricoRepository caricoRepository;

    public Carico save (NewCaricoDTO payload) {

        Carico newCarico= new Carico();
        newCarico.setCategoria(payload.categoria());
        newCarico.setDescrizione(payload.descrizione());
        return caricoRepository.save(newCarico);
    }

    public Page<Carico> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return this.caricoRepository.findAll(pageable);
    }

    public Carico findById(long Id) {
        return this.caricoRepository.findById(Id).orElseThrow(() -> new NotFoundException(Id));
    }

    public void findByIdAndDelete(long caricoId) {
        Carico found = this.findById(caricoId);
        this.caricoRepository.delete(found);
    }

    public Carico findByIdAndUpdate(long caricoId, NewCaricoDTO payload) {
        Carico found = this.findById(caricoId);

        found.setCategoria(payload.categoria());
        found.setDescrizione(payload.descrizione());
        return caricoRepository.save(found);
    }

}
