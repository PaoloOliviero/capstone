package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import paolooliviero.capstone.entities.Cliente;
import paolooliviero.capstone.entities.Prodotto;

import java.util.Optional;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
    Optional<Prodotto> findById(long id);
}
