package paolooliviero.capstone.payloads;

import paolooliviero.capstone.entities.OrdineCliente;

public record OrdineClassificatoDTO (
        Long id,
        String statoOrdine,
        Long segmentoId,
        String tipologiaSegmentoId // oppure Integer se usi ordinal
) {
    public OrdineClassificatoDTO(OrdineCliente ordine) {
        this(
                ordine.getId(),
                ordine.getStatoOrdine().name(),
                ordine.getSegmento().getId(),
                String.valueOf(ordine.getSegmento().getTipologiaSegmento().ordinal())
        );
    }
}

