package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import paolooliviero.capstone.entities.StatoFattura;
import paolooliviero.capstone.entities.StoricoPercorrenze;

import java.util.Optional;

public interface StoricoPercorrenzeRepository extends JpaRepository<StoricoPercorrenze, Long> {
    Optional<StoricoPercorrenze> findById(long id);
}


