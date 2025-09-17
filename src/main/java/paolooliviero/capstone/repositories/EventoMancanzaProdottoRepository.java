package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import paolooliviero.capstone.entities.EventoMancanzaProdotto;

import java.util.Optional;

public interface EventoMancanzaProdottoRepository extends JpaRepository<EventoMancanzaProdotto, Long> {
    Optional<EventoMancanzaProdotto> findById(long id);
}
