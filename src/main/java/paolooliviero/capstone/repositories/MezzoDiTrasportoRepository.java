package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import paolooliviero.capstone.entities.Cliente;
import paolooliviero.capstone.entities.Magazzino;
import paolooliviero.capstone.entities.MezzoDiTrasporto;

import java.util.Optional;

public interface MezzoDiTrasportoRepository extends JpaRepository<MezzoDiTrasporto, Long> {
    Optional<MezzoDiTrasporto> findById(long id);
    Optional<MezzoDiTrasporto> findByMagazzino(Magazzino magazzino);

}
