package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import paolooliviero.capstone.entities.Carico;
import paolooliviero.capstone.entities.Utente;

import java.util.Optional;

public interface CaricoRepository extends JpaRepository<Carico, Long> {
    Optional<Carico> findById(long id);
}