package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import paolooliviero.capstone.entities.MovimentoMagazzino;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovimentoMagazzinoRepository extends JpaRepository<MovimentoMagazzino, Long> {
    Optional<MovimentoMagazzino> findById(long id);

    @Query("SELECT m FROM MovimentoMagazzino m WHERE m.magazzino.id = :magazzinoId")
    List<MovimentoMagazzino> filterByMagazzino(@Param("magazzinoId") Long magazzinoId);

    @Query("SELECT m FROM MovimentoMagazzino m WHERE m.mezzoDiTrasporto.id = :mezzoId")
    List<MovimentoMagazzino> filterByMezzo(@Param("mezzoId") Long mezzoId);

    @Query("SELECT m FROM MovimentoMagazzino m WHERE m.dataRegistrazione <= :data")
    List<MovimentoMagazzino> filterByDataRegistrazione(@Param("data") LocalDate data);

    @Query("SELECT m FROM MovimentoMagazzino m WHERE m.registratoDa.id = :utenteId")
    List<MovimentoMagazzino> filterByUtente(@Param("utenteId") Long utenteId);

    @Query("SELECT m FROM MovimentoMagazzino m WHERE m.quantity >= :minQuantity")
    List<MovimentoMagazzino> filterByMinQuantity(@Param("minQuantity") Double minQuantity);

    @Query("SELECT m FROM MovimentoMagazzino m WHERE m.prodottoMagazzino.id = :pmId")
    List<MovimentoMagazzino> filterByProdottoMagazzino(@Param("pmId") Long pmId);

    @Query("SELECT m FROM MovimentoMagazzino m WHERE m.storicoPercorrenza.id = :storicoId")
    List<MovimentoMagazzino> filterByStoricoPercorrenza(@Param("storicoId") Long storicoId);

}
