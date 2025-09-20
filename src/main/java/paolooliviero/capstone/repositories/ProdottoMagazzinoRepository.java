package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import paolooliviero.capstone.entities.OrdineCliente;
import paolooliviero.capstone.entities.Prodotto;
import paolooliviero.capstone.entities.ProdottoMagazzino;

import java.util.Optional;

public interface ProdottoMagazzinoRepository extends JpaRepository<ProdottoMagazzino, Long> {
    Optional<ProdottoMagazzino> findById(long id);
    Optional<ProdottoMagazzino> findByProdotto(Prodotto prodotto);
}
