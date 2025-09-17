package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import paolooliviero.capstone.entities.Prodotto;
import paolooliviero.capstone.entities.SedeCliente;

import java.util.Optional;

public interface SedeClienteRepository extends JpaRepository<SedeCliente, Long> {
    Optional<SedeCliente> findById(long id);
}
