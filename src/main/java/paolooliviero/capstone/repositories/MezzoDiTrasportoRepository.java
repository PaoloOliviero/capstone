package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import paolooliviero.capstone.entities.Cliente;

import java.util.Optional;

public interface MezzoDiTrasportoRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findById(long id);
}
