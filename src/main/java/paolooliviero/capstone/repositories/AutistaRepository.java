package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import paolooliviero.capstone.entities.Autista;
import paolooliviero.capstone.entities.Carico;

import java.util.Optional;

public interface AutistaRepository extends JpaRepository<Autista, Long> {
    Optional<Autista> findById(long id);
}
