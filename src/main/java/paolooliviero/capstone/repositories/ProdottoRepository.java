package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import paolooliviero.capstone.entities.Prodotto;

import java.util.List;
import java.util.Optional;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {

    Optional<Prodotto> findById(long id);

    @Query("SELECT p FROM Prodotto p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Prodotto> filtroNome(@Param("nome") String nome);

    @Query("SELECT p FROM Prodotto p WHERE LOWER(p.categoria) LIKE LOWER(CONCAT('%', :categoria, '%'))")
    List<Prodotto> filtroCategoria(@Param("categoria") String categoria);

    @Query("SELECT p FROM Prodotto p WHERE p.prezzoUnitario <= :prezzoMax")
    List<Prodotto> filtroPrezzo(@Param("prezzoMax") Double prezzoMax);

    @Query("SELECT p FROM Prodotto p WHERE p.ordineCliente.id = :ordineId")
    List<Prodotto> filtroOrdineCliente(@Param("ordineId") Long ordineId);

    @Query("SELECT p FROM Prodotto p JOIN FETCH p.ordineCliente")
    List<Prodotto> findAllConJoin();
}

