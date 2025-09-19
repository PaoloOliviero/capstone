package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import paolooliviero.capstone.entities.Cliente;
import paolooliviero.capstone.entities.Utente;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findById(long id);


    @Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale<=:fatturatoAnnuale")
    List<Cliente> filterToFatturatoAnnuale(@Param("fatturatoAnnuale") int fatturatoAnnuale);

    @Query("SELECT c FROM Cliente c WHERE c.dataInserimento<=:dataInserimento")
    List<Cliente> filterToDataInserimento(@Param("dataInserimento") LocalDate dataInserimento);

    @Query("SELECT c FROM Cliente c WHERE c.dataUltimoContatto<=:dataUltimoContatto")
    List<Cliente> filterToDataUltimoContatto(@Param("dataUltimoContatto")LocalDate dataUltimoContatto);

    @Query("Select c FROM Cliente c Where c.ragioneSociale LIKE CONCAT('%', :ragioneSociale, '%')")
    List<Cliente> filterToRagioneSociale(@Param("ragioneSociale") String RagioneSociale);
}


