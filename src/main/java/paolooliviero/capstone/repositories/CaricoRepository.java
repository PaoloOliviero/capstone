package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import paolooliviero.capstone.entities.Carico;
import paolooliviero.capstone.entities.Utente;

import java.util.List;
import java.util.Optional;

public interface CaricoRepository extends JpaRepository<Carico, Long> {
    Optional<Carico> findById(long id);

    @Query("SELECT c FROM Carico c WHERE c.categoria LIKE %:categoria%")
    List<Carico> filtroCategoria(@Param("categoria") String categoria);

    @Query("SELECT c FROM Carico c WHERE c.descrizione LIKE %:descrizione%")
    List<Carico> filtroDescrizione(@Param("descrizione") String descrizione);

    @Query("SELECT c FROM Carico c WHERE c.mezzoDiTrasporto.id = :mezzoId")
    List<Carico> filtroMezzo(@Param("mezzoId") Long mezzoId);

    @Query("SELECT c FROM Carico c JOIN FETCH c.mezzoDiTrasporto LEFT JOIN FETCH c.prodottoMagazzino")
    List<Carico> findAllConJoin();

}