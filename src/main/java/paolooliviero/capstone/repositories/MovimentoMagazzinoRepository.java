package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import paolooliviero.capstone.entities.MovimentoMagazzino;

import java.util.Optional;

public interface MovimentoMagazzinoRepository extends JpaRepository<MovimentoMagazzino, Long> {
    Optional<MovimentoMagazzino> findById(long id);
}
