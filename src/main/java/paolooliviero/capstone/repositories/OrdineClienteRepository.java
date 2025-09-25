package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import paolooliviero.capstone.entities.Cliente;
import paolooliviero.capstone.entities.OrdineCliente;

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


}