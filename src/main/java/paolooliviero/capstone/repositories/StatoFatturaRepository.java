package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import paolooliviero.capstone.entities.StatoFattura;

import java.util.Optional;

public interface StatoFatturaRepository extends JpaRepository<StatoFattura, Long> {
    Optional<StatoFattura> findById(long id);
}


