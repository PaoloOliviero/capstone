package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import paolooliviero.capstone.entities.Cliente;
import paolooliviero.capstone.entities.OrdineCliente;
import paolooliviero.capstone.enums.StatoOrdine;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrdineClienteRepository extends JpaRepository<OrdineCliente, Long> {
    Optional<OrdineCliente> findById(long id);
    @Query("SELECT o FROM OrdineCliente o JOIN FETCH o.cliente WHERE o.id = :id")
    Optional<OrdineCliente> findByIdWithCliente(@Param("id") Long id);

    @Query("""
    SELECT o FROM OrdineCliente o
    LEFT JOIN FETCH o.cliente
    LEFT JOIN FETCH o.fattura
    LEFT JOIN FETCH o.segmento
    LEFT JOIN FETCH o.spedizione
    LEFT JOIN FETCH o.carico
""")
    List<OrdineCliente> findAllWithJoin();

    @Query("SELECT o FROM OrdineCliente o WHERE o.dataOrdine <= :dataOrdine")
    List<OrdineCliente> filterByDataOrdine(@Param("dataOrdine") LocalDate dataOrdine);

    @Query("SELECT o FROM OrdineCliente o WHERE o.statoOrdine = :statoOrdine")
    List<OrdineCliente> filterByStatoOrdine(@Param("statoOrdine") StatoOrdine statoOrdine);

    @Query("SELECT o FROM OrdineCliente o WHERE o.cliente.ragioneSociale LIKE CONCAT('%', :ragioneSociale, '%')")
    List<OrdineCliente> filterByRagioneSociale(@Param("ragioneSociale") String ragioneSociale);

    @Query("SELECT o FROM OrdineCliente o WHERE o.fattura.importo <= :importo")
    List<OrdineCliente> filterByImportoFattura(@Param("importo") Double importo);

    @Query("SELECT o FROM OrdineCliente o WHERE o.segmento.id = :segmentoId")
    List<OrdineCliente> filterBySegmento(@Param("segmentoId") Long segmentoId);




}