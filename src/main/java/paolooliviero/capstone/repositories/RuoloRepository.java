package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import paolooliviero.capstone.entities.Prodotto;
import paolooliviero.capstone.entities.Ruolo;

import java.util.Optional;

public interface RuoloRepository extends JpaRepository<Ruolo, Long> {
    Optional<Ruolo> findById(long id);
    Optional<Ruolo> findByNome(String nome);
}

