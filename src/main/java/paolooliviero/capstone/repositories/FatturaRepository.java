package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import paolooliviero.capstone.entities.Cliente;
import paolooliviero.capstone.entities.Fattura;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FatturaRepository extends JpaRepository<Fattura, Long> {
    Optional<Fattura> findById(long id);

    @Query("SELECT f FROM Fattura f WHERE f.importo <= :importoMax")
    List<Fattura> findByImportoLessThanEqual(@Param("importoMax") double importoMax);

    @Query("SELECT f FROM Fattura f WHERE f.dataEmissione <= :dataEmissioneMax")
    List<Fattura> findByDataEmissioneBeforeOrEqual(@Param("dataEmissioneMax") LocalDate dataEmissioneMax);

    @Query("SELECT f FROM Fattura f WHERE f.statoFattura.nome LIKE CONCAT('%', :statoNome, '%')")
    List<Fattura> findByStatoFatturaNomeLike(@Param("statoNome") String statoNome);

    @Query("SELECT f FROM Fattura f WHERE f.cliente.id = :clienteId")
    List<Fattura> findByClienteId(@Param("clienteId") long clienteId);
}


