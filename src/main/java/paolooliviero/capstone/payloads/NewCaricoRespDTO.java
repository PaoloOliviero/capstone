package paolooliviero.capstone.payloads;

import java.util.List;

public record NewCaricoRespDTO (
        Long id,
        String categoria,
        String descrizione,
        Double volume,
        Long mezzoId,
        List<Long> prodottiMagazzinoIds
) {

}