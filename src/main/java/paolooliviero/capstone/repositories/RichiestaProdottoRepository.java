package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import paolooliviero.capstone.entities.RichiestaProdotto;

import java.util.Optional;

public interface RichiestaProdottoRepository extends JpaRepository<RichiestaProdotto, Long> {
    Optional<RichiestaProdotto> findById(long id);
}
