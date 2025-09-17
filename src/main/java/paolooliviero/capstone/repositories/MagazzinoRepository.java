package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import paolooliviero.capstone.entities.Magazzino;

import java.util.Optional;

public interface MagazzinoRepository extends JpaRepository<Magazzino, Long> {
    Optional<Magazzino> findById(long id);
}
