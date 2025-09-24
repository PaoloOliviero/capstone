package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import paolooliviero.capstone.entities.Notifica;
import paolooliviero.capstone.entities.Utente;

import java.util.List;

@Repository
public interface NotificaRepository extends JpaRepository<Notifica, Long> {

    List<Notifica> findByUtenteDestinatarioAndVisualizzataFalse(Utente utente);
}

