package paolooliviero.capstone.payloads;

import paolooliviero.capstone.enums.TipologiaSegmento;

public record NewSegmentoRespDTO (
        Long id,
        String nome,
        TipologiaSegmento tipologiaSegmento
) {
}
