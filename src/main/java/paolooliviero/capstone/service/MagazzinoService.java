package paolooliviero.capstone.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import paolooliviero.capstone.entities.Magazzino;
import paolooliviero.capstone.exceptions.NotFoundException;
import paolooliviero.capstone.payloads.NewMagazzinoDTO;
import paolooliviero.capstone.repositories.MagazzinoRepository;

@Service
@Slf4j
public class MagazzinoService {

    @Autowired
    private MagazzinoRepository magazzinoRepository;

    public Magazzino save (NewMagazzinoDTO payload) {

        Magazzino newMagazzino= new Magazzino();
        newMagazzino.setCapacitaTotale(payload.capacitaTotale());
        newMagazzino.setCapacitaOccupata(payload.capacitaOccupata());
        return magazzinoRepository.save(newMagazzino);
    }

    public Page<Magazzino> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        return this.magazzinoRepository.findAll(pageable);
    }

    public Magazzino findById(long Id) {
        return this.magazzinoRepository.findById(Id).orElseThrow(() -> new NotFoundException(Id));
    }

    public void findByIdAndDelete(long magazzinoId) {
        Magazzino found = this.findById(magazzinoId);
        this.magazzinoRepository.delete(found);
    }

    public Magazzino findByIdAndUpdate(long magazzinoId, NewMagazzinoDTO payload) {
        Magazzino found = this.findById(magazzinoId);

        found.setCapacitaTotale(payload.capacitaTotale());
        found.setCapacitaOccupata(payload.capacitaOccupata());
        return magazzinoRepository.save(found);
    }

}
