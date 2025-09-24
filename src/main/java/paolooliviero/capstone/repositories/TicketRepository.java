package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import paolooliviero.capstone.entities.Ticket;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {


        @Query("SELECT t FROM Ticket t WHERE LOWER(t.titolo) LIKE LOWER(CONCAT('%', :titolo, '%'))")
        List<Ticket> filtroTitolo(@Param("titolo") String titolo);

        @Query("SELECT t FROM Ticket t WHERE LOWER(t.descrizione) LIKE LOWER(CONCAT('%', :descrizione, '%'))")
        List<Ticket> filtroDescrizione(@Param("descrizione") String descrizione);

        @Query("SELECT t FROM Ticket t WHERE t.dataCreazione <= :data")
        List<Ticket> filtroDataCreazione(@Param("data") LocalDateTime data);

        @Query("SELECT t FROM Ticket t WHERE t.ordineCliente.id = :ordineId")
        List<Ticket> filtroOrdineCliente(@Param("ordineId") Long ordineId);

        @Query("SELECT t FROM Ticket t JOIN FETCH t.ordineCliente")
        List<Ticket> findAllConJoin();
    }




