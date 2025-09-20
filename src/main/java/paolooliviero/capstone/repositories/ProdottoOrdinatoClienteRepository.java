package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import paolooliviero.capstone.entities.ProdottoOrdinatoCliente;

import java.util.List;

public interface ProdottoOrdinatoClienteRepository extends JpaRepository<ProdottoOrdinatoCliente, Long> {
    List<ProdottoOrdinatoCliente> findByOrdineClienteId(Long ordineId);
    List<ProdottoOrdinatoCliente> findByProdottoId(Long prodottoId);
}
