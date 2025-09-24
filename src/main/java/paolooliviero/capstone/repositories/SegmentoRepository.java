package paolooliviero.capstone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import paolooliviero.capstone.entities.Segmento;
import paolooliviero.capstone.enums.TipologiaSegmento;

import java.util.List;

@Repository
public interface SegmentoRepository extends JpaRepository<Segmento, Long> {

    List<Segmento> findByTipologiaSegmento(TipologiaSegmento tipologiaSegmento);
}

