package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import paolooliviero.capstone.entities.RichiestaProdotto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RichiestaProdottoRepository extends JpaRepository<RichiestaProdotto, Long> {
    Optional<RichiestaProdotto> findById(long id);

    @Query("SELECT r FROM RichiestaProdotto r WHERE r.quantitaRichiesta <= :quantitaRichiesta")
    List<RichiestaProdotto> filtroQuantitaRichiesta(@Param("quantitaRichiesta") int quantitaRichiesta);

    @Query("SELECT r FROM RichiestaProdotto r WHERE r.dataRichiesta <= :dataRichiesta")
    List<RichiestaProdotto> filtroDataRichiesta(@Param("dataRichiesta") LocalDate dataRichiesta);

    @Query("SELECT r FROM RichiestaProdotto r WHERE r.motivazione LIKE CONCAT('%', :motivazione, '%')")
    List<RichiestaProdotto> filtroMotivazione(@Param("motivazione") String motivazione);

    @Query("SELECT r FROM RichiestaProdotto r WHERE r.prodottoMagazzino.id = :prodottoMagazzinoId")
    List<RichiestaProdotto> filtroProdottoMagazzino(@Param("prodottoMagazzinoId") Long prodottoMagazzinoId);

    @Query("SELECT r FROM RichiestaProdotto r WHERE r.magazzino.id = :magazzinoId")
    List<RichiestaProdotto> filtroMagazzino(@Param("magazzinoId") Long magazzinoId);

    @Query("SELECT r FROM RichiestaProdotto r WHERE r.richiestoDa.id = :richiestoDaId")
    List<RichiestaProdotto> filtroRichiestoDa(@Param("richiestoDaId") Long richiestoDaId);
}
