package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import paolooliviero.capstone.entities.OrdineCliente;
import paolooliviero.capstone.entities.Prodotto;
import paolooliviero.capstone.entities.ProdottoMagazzino;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProdottoMagazzinoRepository extends JpaRepository<ProdottoMagazzino, Long> {
    Optional<ProdottoMagazzino> findById(long id);
    Optional<ProdottoMagazzino> findByProdotto(Prodotto prodotto);

    @Query("SELECT p FROM ProdottoMagazzino p WHERE p.quantitaDisponibile <= :quantita")
    List<ProdottoMagazzino> filtroQuantitaDisponibile(@Param("quantita") Double quantita);

    @Query("SELECT p FROM ProdottoMagazzino p WHERE p.dataIngresso <= :dataIngresso")
    List<ProdottoMagazzino> filtroDataIngresso(@Param("dataIngresso") LocalDate dataIngresso);

    @Query("SELECT p FROM ProdottoMagazzino p WHERE p.prodotto.id = :prodottoId")
    List<ProdottoMagazzino> filtroProdotto(@Param("prodottoId") Long prodottoId);

    @Query("SELECT p FROM ProdottoMagazzino p WHERE p.magazzino.id = :magazzinoId")
    List<ProdottoMagazzino> filtroMagazzino(@Param("magazzinoId") Long magazzinoId);

    @Query("SELECT p FROM ProdottoMagazzino p JOIN FETCH p.prodotto JOIN FETCH p.magazzino")
    List<ProdottoMagazzino> findAllConJoin();


}


